package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import data_struct.Student;

public class StudentTable {
  private static final String student_table = Database.database_name + ".`student`";
  
  public static final String col_label_id = "id";
  public static final String col_label_name = "name";
  public static final String col_label_group = "group_num";
  public static final String col_label_password = "password";
  public static final String col_label_remote_ctrl_num = "remote_ctrl_num";
  
  private static final String col_id = "`" + col_label_id + "`";
  private static final String col_name = "`" + col_label_name + "`";
  private static final String col_group = "`" + col_label_group + "`";
  private static final String col_password = "`" + col_label_password + "`";
  private static final String col_remote_ctrl_num = "`" + col_label_remote_ctrl_num + "`";
  
  private static final String selectSql = String.format( "SELECT * FROM %s ", student_table );
  
  private static final String insertSql = String.format( "INSERT INTO %s (%s,%s,%s,%s,%s) values ",
                                                         student_table,
                                                         col_id,
                                                         col_name,
                                                         col_group,
                                                         col_password,
                                                         col_remote_ctrl_num );
  
  private static final String updateSql = String.format( "UPDATE %s set %%s where %%s ", student_table );
  
  
  private static final String selectAllStudentsSql = String.format( selectSql + "order by %s ASC;", col_remote_ctrl_num );
  public static void SelectStudent( Connection conn, Vector<Student> students ) throws SQLException {
    Statement stmt = null;
    ResultSet result = null;
    try {
      stmt = conn.createStatement();
      result = stmt.executeQuery( selectAllStudentsSql );
      while ( result.next() ) {
        students.add( new Student( result.getString( col_label_id ),
                                   result.getString( col_label_name ),
                                   result.getInt( col_label_group ),
                                   result.getString( col_label_password ),
                                   result.getInt( col_label_remote_ctrl_num ) ) );
      } // while

      String selectLog = "SelectStudent: table='" + student_table + "'\r\n";
      int student_counter = 0;
      int student_number = students.size();
      for ( Student student : students ) {
        selectLog += String.format( "( %s='%s', %s='%s', %s='%d', %s='%s', %s='%d' )",
                                    StudentTable.col_label_id, student.id,
                                    StudentTable.col_label_name, student.name,
                                    StudentTable.col_label_group, student.group,
                                    StudentTable.col_label_password, student.password,
                                    StudentTable.col_label_remote_ctrl_num, student.remote_ctrl_num );
        student_counter++;
        if ( student_counter <= student_number )
          selectLog += "\r\n";
      } // for
      selectLog += "successful.";
    
      System.out.println( selectLog );
    } // try
    finally {
      if ( result != null ) result.close();
      result = null;
      if ( stmt != null ) stmt.close();
      stmt = null;
    } // finally
  } // SelectStudent()
  
  
  private static final String selectStudentByIdSql = String.format( selectSql + "where %s=?;", col_id );
  public static void SelectStudentById( Connection conn, String id, Student student ) throws SQLException {
    PreparedStatement selectPStmt = null;
    ResultSet result = null;
    try {
      selectPStmt = conn.prepareStatement( selectStudentByIdSql );
      selectPStmt.setString( 1, id );
      result = selectPStmt.executeQuery();
      
      if ( result.next() ) {
        student.id = result.getString( col_label_id );
        student.name = result.getString( col_label_name );
        student.group = result.getInt( col_label_group );
        student.password = result.getString( col_label_password );
        student.remote_ctrl_num = result.getInt( col_label_remote_ctrl_num );
      } // if
      
      String selectLog = String.format( "SelectStudentById: table='%s'\r\n" +
                                        "( %s='%s', %s='%s', %s='%d', %s='%s', %s='%d' )\r\n" +
                                        "successful.",
                                        student_table,
                                        StudentTable.col_label_id, student.id,
                                        StudentTable.col_label_name, student.name,
                                        StudentTable.col_label_group, student.group,
                                        StudentTable.col_label_password, student.password,
                                        StudentTable.col_label_remote_ctrl_num, student.remote_ctrl_num );
      System.out.println( selectLog );
    } // try
    finally {
      if ( selectPStmt != null ) selectPStmt.close();
      selectPStmt = null;
    } // finally
  } // SelectStudentById()
  
  
  private static final String selectStudentByGroupSql = String.format( selectSql + "where %s=? order by %s ASC;", col_group, col_remote_ctrl_num );
  public static void SelectStudentByGroup( Connection conn, int group, Vector<Student> students ) throws SQLException {
    PreparedStatement selectPStmt = null;
    ResultSet result = null;
    try {
      selectPStmt = conn.prepareStatement( selectStudentByGroupSql );
      selectPStmt.setInt( 1, group );
      result = selectPStmt.executeQuery();
      
      while ( result.next() ) {
        students.add( new Student( result.getString( col_label_id ),
                                   result.getString( col_label_name ),
                                   result.getInt( col_label_group ),
                                   result.getString( col_label_password ),
                                   result.getInt( col_label_remote_ctrl_num ) ) );
      } // while
      
      String selectLog = "SelectStudentByGroup: table='" + student_table + "'\r\n";
      int student_number = students.size();
      int student_counter = 0;
      for ( Student student : students ) {
        selectLog += String.format( "( %s='%s', %s='%s', %s='%d', %s='%s', %s='%d' )",
                                    StudentTable.col_label_id, student.id,
                                    StudentTable.col_label_name, student.name,
                                    StudentTable.col_label_group, student.group,
                                    StudentTable.col_label_password, student.password,
                                    StudentTable.col_label_remote_ctrl_num, student.remote_ctrl_num );
        student_counter++;
        if ( student_counter <= student_number )
          selectLog += "\r\n";
      } // for
      selectLog += "successful.";
      System.out.println( selectLog );
    } // try
    finally {
      if ( selectPStmt != null ) selectPStmt.close();
      selectPStmt = null;
    } // finally
  } // SelectStudentByGroup()
  
  
  private static final String insertOneStudentSql = insertSql + "(?,?,?,?,?);";
  public static void InsertStudent( Connection conn,
                                    String id,
                                    String name,
                                    int group,
                                    String password,
                                    int remote_ctrl_num ) throws SQLException {
    PreparedStatement insertPStmt = null;
    try {
      insertPStmt = conn.prepareStatement( insertOneStudentSql );
      insertPStmt.setString( 1, id );
      insertPStmt.setString( 2, name );
      insertPStmt.setInt( 3, group );
      insertPStmt.setString( 4, password );
      insertPStmt.setInt( 5, remote_ctrl_num );
      insertPStmt.executeUpdate();

      String insertLog = String.format( "InsertStudent: table='%s'\r\n" +
                                        "( %s='%s', %s='%s', %s='%d', %s='%s', %s='%d' )\r\n" +
                                        "successful.",
                                         student_table,
                                         StudentTable.col_label_id, id,
                                         StudentTable.col_label_name, name,
                                         StudentTable.col_label_group, group,
                                         StudentTable.col_label_password, password,
                                         StudentTable.col_label_remote_ctrl_num, remote_ctrl_num );
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertPStmt != null ) insertPStmt.close();
      insertPStmt = null;
    } // finally
  } // InsertStudent()

  public static void InsertStudent( Connection conn, Vector<Student> students ) throws SQLException {
    Statement insertStmt = null;
    try {
      int student_number = students.size();
      if ( student_number <= 0 ) return ;
    
      String insertStudentsSql = insertSql;
      int student_counter = 0;
      for ( Student student : students ) {
        insertStudentsSql += String.format( "('%s','%s',%d,%s,%d)",
                                            student.id,
                                            student.name,
                                            student.group,
                                            student.password,
                                            student.remote_ctrl_num );
        student_counter++;
        if ( student_counter < student_number )
          insertStudentsSql += ",";
        else if ( student_counter == student_number )
          insertStudentsSql += ";";
      } // for
    
      insertStmt = conn.createStatement();
      insertStmt.executeUpdate( insertStudentsSql );

    
      String insertLog = "InsertStudent: table='" + student_table + "'\r\n";
      student_counter = 0;
      for ( Student student : students ) {
        insertLog += String.format( "( %s='%s', %s='%s', %s='%d', %s='%s', %s='%d' )",
                                    StudentTable.col_label_id, student.id,
                                    StudentTable.col_label_name, student.name,
                                    StudentTable.col_label_group, student.group,
                                    StudentTable.col_label_password, student.password,
                                    StudentTable.col_label_remote_ctrl_num, student.remote_ctrl_num );
        student_counter++;
        if ( student_counter <= student_number )
          insertLog += "\r\n";
      } // for
      insertLog += "successful.";
    
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertStmt != null ) insertStmt.close();
      insertStmt.close();
    } // finally
  } // InsertStudent()

  
  private static final String updatePasswordSql = String.format( updateSql + ";", col_password + "=?", col_id + "=?" );
  public static void UpdatePassword( Connection conn, String id, String password ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updatePasswordSql );
      updatePStmt.setString( 1, password );
      updatePStmt.setString( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdatePassword: table='%s'\r\n" +
                                        "( %s='%s', %s='%s' )\r\n" +
                                        "successful.",
                                        student_table,
                                        StudentTable.col_label_id, id,
                                        StudentTable.col_label_password, password );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdatePassword()
  
  
  private static final String updateRemote_ctrl_numSql = String.format( updateSql + ";", col_remote_ctrl_num + "=?", col_id + "=?" );
  public static void UpdateRemote_ctrl_num( Connection conn, String id, int remote_ctrl_num ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateRemote_ctrl_numSql );
      updatePStmt.setInt( 1, remote_ctrl_num );
      updatePStmt.setString( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateRemote_ctrl_num: table='%s'\r\n" +
                                        "( %s='%s', %s='%d' )\r\n" +
                                        "successful.",
                                        student_table,
                                        StudentTable.col_label_id, id,
                                        StudentTable.col_label_remote_ctrl_num, remote_ctrl_num );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateRemote_ctrl_num()
} // class StudentTable
