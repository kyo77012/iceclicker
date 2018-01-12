package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import data_struct.Option;

public class OptionTable {
  private static final String option_table = Database.database_name + ".`option`";
  
  public static final String col_label_id = "id";
  public static final String col_label_question_id = "question_id";
  public static final String col_label_content = "content";
  
  private static final String col_id = "`" + col_label_id + "`";
  private static final String col_question_id = "`" + col_label_question_id + "`";
  private static final String col_content = "`" + col_label_content + "`";
  
  private static final String selectSql = String.format( "SELECT * FROM %s ", option_table );
  
  private static final String insertSql = String.format( "INSERT INTO %s (%s,%s) values ",
                                                         option_table, col_question_id, col_content );
  
  private static final String updateSql = String.format( "UPDATE %s set %%s where %%s ", option_table );
  
  private static final String selectAllOptionsSql = selectSql + ";";
  public static void SelectOption( Connection conn, Vector<Option> options ) throws SQLException {
    Statement stmt = null;
    ResultSet result = null;
    try {
      stmt = conn.createStatement();
      result = stmt.executeQuery( selectAllOptionsSql );
      while ( result.next() ) {
        options.add( new Option( result.getInt( col_label_id ),
                                 result.getInt( col_label_question_id ),
                                 result.getString( col_label_content ) ) );
      } // while

      String selectLog = "SelectOption: table='" + option_table + "'\r\n";
      int option_counter = 0;
      int option_number = options.size();
      for ( Option option : options ) {
        selectLog += String.format( "( %s='%d', %s='%d', %s='%s' )",
                                    OptionTable.col_label_id, option.getId(),
                                    OptionTable.col_label_question_id, option.getQuestion_id(),
                                    OptionTable.col_label_content, option.getContent() );
        option_counter++;
        if ( option_counter <= option_number )
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
  } // SelectOption()
  
  
  private static final String selectOptionByQuestion_idSql = String.format( selectSql + "where %s=?;", col_question_id );
  public static void SelectOptionByQuestion_id( Connection conn, int question_id, Vector<Option> options ) throws SQLException {
    PreparedStatement selectPStmt = null;
    ResultSet result = null;
    try {
      selectPStmt = conn.prepareStatement( selectOptionByQuestion_idSql );
      selectPStmt.setInt( 1, question_id );
      result = selectPStmt.executeQuery();
      
      while ( result.next() ) {
        options.add( new Option( result.getInt( col_label_id ),
                                 result.getInt( col_label_question_id ),
                                 result.getString( col_label_content ) ) );
      } // while
      
      String selectLog = "selectOptionByQuestion_id: table='" + option_table + "'\r\n";
      int option_number = options.size();
      int option_counter = 0;
      for ( Option option : options ) {
        selectLog += String.format( "( %s='%d', %s='%d', %s='%s' )",
                                    OptionTable.col_label_id, option.getId(),
                                    OptionTable.col_label_question_id, option.getQuestion_id(),
                                    OptionTable.col_label_content, option.getContent() );
        option_counter++;
        if ( option_counter <= option_number )
          selectLog += "\r\n";
      } // for
      selectLog += "successful.";
      System.out.println( selectLog );
    } // try
    finally {
      if ( result != null ) result.close();
      result = null;
      if ( selectPStmt != null ) selectPStmt.close();
      selectPStmt = null;
    } // finally
  } // SelectOptionByQuestion_id()
  
  
  private static final String insertOneOptionSql = insertSql + "(?,?);";
  public static void InsertOption( Connection conn, int question_id, String content ) throws SQLException {
    PreparedStatement insertPStmt = null;
    try {
      insertPStmt = conn.prepareStatement( insertOneOptionSql );
      insertPStmt.setInt( 1, question_id );
      insertPStmt.setString( 2, content );
      insertPStmt.executeUpdate();

      String insertLog = String.format( "InsertOption: table='%s'\r\n" +
                                        "( %s='%d', %s='%s' )\r\n" +
                                        "successful.",
                                        option_table,
                                        OptionTable.col_label_question_id, question_id,
                                        OptionTable.col_label_content, content );
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertPStmt != null ) insertPStmt.close();
      insertPStmt = null;
    } // finally
  } // InsertOption()
  
  
  public static void InsertOption( Connection conn, Vector<Option> options ) throws SQLException {
    Statement insertStmt = null;
    try {
      int option_number = options.size();
      if ( option_number <= 0 ) return ;
    
      String insertOptionsSql = insertSql;
      int option_counter = 0;
      for ( Option option : options ) {
        insertOptionsSql += String.format( "(%d,'%s')",
                                           option.getQuestion_id(),
                                           option.getContent() );
        option_counter++;
        if ( option_counter < option_number )
          insertOptionsSql += ",";
        else if ( option_counter == option_number )
          insertOptionsSql += ";";
      } // for
    
      insertStmt = conn.createStatement();
      insertStmt.executeUpdate( insertOptionsSql );

    
      String insertLog = "InsertOption: table='" + option_table + "'\r\n";
      option_counter = 0;
      for ( Option option : options ) {
        insertLog += String.format( "( %s='%d', %s='%s' )",
                                    OptionTable.col_label_question_id, option.getQuestion_id(),
                                    OptionTable.col_label_content, option.getContent() );
        option_counter++;
        if ( option_counter <= option_number )
          insertLog += "\r\n";
      } // for
      insertLog += "successful.";
    
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertStmt != null ) insertStmt.close();
      insertStmt.close();
    } // finally
  } // InsertOption()
  
  
  private static final String updateQuestion_idSql = String.format( updateSql + ";", col_question_id + "=?", col_id + "=?" );
  public static void UpdateQuestion_id( Connection conn, int id, int question_id ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateQuestion_idSql );
      updatePStmt.setInt( 1, question_id );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateQuestion_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%d' )" +
                                        "successful.",
                                        option_table,
                                        OptionTable.col_label_id, id,
                                        OptionTable.col_label_question_id, question_id );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateQuestion_id()
  
  
  private static final String updateContentSql = String.format( updateSql + ";", col_content + "=?", col_id + "=?" );
  public static void UpdateContent( Connection conn, int id, String content ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateContentSql );
      updatePStmt.setString( 1, content );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateContent: table='%s'\r\n" +
                                        "( %s='%d', %s='%s' )\r\n" +
                                        "successful.",
                                        option_table,
                                        OptionTable.col_label_id, id,
                                        OptionTable.col_label_content, content );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateContent()
  
  
} // class OptionTable
