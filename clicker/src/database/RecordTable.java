package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import data_struct.Record;

public class RecordTable {
  private static final String record_table = Database.database_name + ".`record`";
  
  public static final String col_label_id = "id";
  public static final String col_label_student_id = "student_id";
  public static final String col_label_question_id = "question_id";
  public static final String col_label_option_id = "option_id";
  
  private static final String col_id = "`" + col_label_id + "`";
  private static final String col_student_id = "`" + col_label_student_id + "`";
  private static final String col_question_id = "`" + col_label_question_id + "`";
  private static final String col_option_id = "`" + col_label_option_id + "`";
  
  
  private static final String selectSql = String.format( "SELECT * FROM %s ", record_table );
  
  private static final String insertSql = String.format( "INSERT INTO %s (%s,%s,%s) values ",
                                                         record_table, col_student_id, col_question_id, col_option_id );
  
  private static final String updateSql = String.format( "UPDATE %s set %%s where %%s ", record_table );

  private static final String selectAllRecordsSql = selectSql + ";";
  public static void SelectRecord( Connection conn, Vector<Record> records ) throws SQLException {
    Statement stmt = null;
    ResultSet result = null;
    try {
      stmt = conn.createStatement();
      result = stmt.executeQuery( selectAllRecordsSql );
      while ( result.next() ) {
        records.add( new Record( result.getInt( col_label_id ),
                                 result.getString( col_label_student_id ),
                                 result.getInt( col_label_question_id ),
                                 result.getInt( col_label_option_id ) ) );
      } // while

      String selectLog = "SelectRecord: table='" + record_table + "'\r\n";
      int record_counter = 0;
      int record_number = records.size();
      for ( Record record : records ) {
        selectLog += String.format( "( %s='%d', %s='%s', %s='%d', %s='%d' )",
                                    RecordTable.col_label_id, record.id,
                                    RecordTable.col_label_student_id, record.student_id,
                                    RecordTable.col_label_question_id, record.question_id,
                                    RecordTable.col_label_option_id, record.option_id );
        record_counter++;
        if ( record_counter <= record_number )
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
  } // SelectRecord()
  
  
  private static final String selectByStudent_idAndQuestion_idSql = String.format( selectSql + "where %s=? and %s=?;",
                                                                                   col_label_student_id, col_question_id );
  public static void SelectRecordByStudent_idAndQuestion_id( Connection conn, String student_id, int question_id, Record record ) throws SQLException {
    PreparedStatement selectPStmt = null;
    ResultSet result = null;
    try {
      selectPStmt = conn.prepareStatement( selectByStudent_idAndQuestion_idSql );
      selectPStmt.setString( 1, student_id );
      selectPStmt.setInt( 2, question_id );
      result = selectPStmt.executeQuery();
      
      if ( result.next() ) {
        record.id = result.getInt( col_label_id );
        record.student_id = result.getString( col_label_student_id );
        record.question_id = result.getInt( col_label_question_id );
        record.option_id = result.getInt( col_label_option_id );
      } // if
      
      String selectLog = String.format( "SelectRecordByStudent_idAndQuestion_id: table='%s'\r\n" +
                                        "( %s='%s', %s='%d' )\r\n" +
                                        "successful.",
                                        record_table,
                                        RecordTable.col_label_student_id, record.student_id,
                                        RecordTable.col_label_question_id, record.question_id );
      System.out.println( selectLog );
    } // try
    finally {
      if ( selectPStmt != null ) selectPStmt.close();
      selectPStmt = null;
    } // finally
  } // SelectOptionByQuestion_id()
  
  private static final String insertOneRecordSql = insertSql + "(?,?,?);";
  public static void InsertRecord( Connection conn, String student_id, int question_id, int option_id ) throws SQLException {
    PreparedStatement insertPStmt = null;
    try {
      insertPStmt = conn.prepareStatement( insertOneRecordSql );
      insertPStmt.setString( 1, student_id );
      insertPStmt.setInt( 2, question_id );
      insertPStmt.setInt( 3, option_id );
      insertPStmt.executeUpdate();

      String insertLog = String.format( "InsertRecord: table='%s'\r\n" +
                                        "( %s='%s', %s='%d', %s='%d' )\r\n" +
                                        "successful.",
                                        record_table,
                                        RecordTable.col_label_student_id, student_id,
                                        RecordTable.col_label_question_id, question_id,
                                        RecordTable.col_label_option_id, option_id );
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertPStmt != null ) insertPStmt.close();
      insertPStmt = null;
    } // finally
  } // InsertRecord()
  
  
  public static void InsertRecord( Connection conn, Vector<Record> records ) throws SQLException {
    Statement insertStmt = null;
    try {
      int record_number = records.size();
      if ( record_number <= 0 ) return ;
    
      String insertRecordsSql = insertSql;
      int record_counter = 0;
      for ( Record record : records ) {
        insertRecordsSql += String.format( "('%s',%d,%d)",
                                           record.student_id,
                                           record.question_id,
                                           record.option_id );
        record_counter++;
        if ( record_counter < record_number )
          insertRecordsSql += ",";
        else if ( record_counter == record_number )
          insertRecordsSql += ";";
      } // for
    
      insertStmt = conn.createStatement();
      insertStmt.executeUpdate( insertRecordsSql );

    
      String insertLog = "InsertRecord: table='" + record_table + "'\r\n";
      record_counter = 0;
      for ( Record record : records ) {
        insertLog += String.format( "( %s='%s', %s='%d', %s='%d' )",
                                    RecordTable.col_label_student_id, record.student_id,
                                    RecordTable.col_label_question_id, record.question_id,
                                    RecordTable.col_label_option_id, record.option_id );
        record_counter++;
        if ( record_counter <= record_number )
          insertLog += "\r\n";
      } // for
      insertLog += "successful.";
    
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertStmt != null ) insertStmt.close();
      insertStmt.close();
    } // finally
  } // InsertRecord()
  
  private static final String updateStudent_idSql = String.format( updateSql + ";", col_student_id + "=?", col_id + "=?" );
  public static void UpdateStudent_id( Connection conn, int id, String student_id ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateStudent_idSql );
      updatePStmt.setString( 1, student_id );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateStudent_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%s' )\r\n" +
                                        "successful.",
                                        record_table,
                                        RecordTable.col_label_id, id,
                                        RecordTable.col_label_student_id, student_id );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateStudent_id()
  
  private static final String updateQuestion_idSql = String.format( updateSql + ";", col_question_id + "=?", col_id + "=?" );
  public static void UpdateQuestion_id( Connection conn, int id, int question_id ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateQuestion_idSql );
      updatePStmt.setInt( 1, question_id );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateQuestion_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%d' )\r\n" +
                                        "successful.",
                                        record_table,
                                        RecordTable.col_label_id, id,
                                        RecordTable.col_label_question_id, question_id );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateQuestion_id()
  
  
  private static final String updateOption_idSql = String.format( updateSql + ";", col_option_id + "=?", col_id + "=?" );
  public static void UpdateOption_id( Connection conn, int id, int option_id ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateOption_idSql );
      updatePStmt.setInt( 1, option_id );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateOption_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%d' )\r\n" +
                                        "successful.",
                                        record_table,
                                        RecordTable.col_label_id, id,
                                        RecordTable.col_label_option_id, option_id );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateOption_id()
} // class RecordTable
