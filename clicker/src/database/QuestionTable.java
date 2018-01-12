package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import data_struct.Question;

public class QuestionTable {
  private static final String question_table = Database.database_name + ".`question`";
  
  public static final String col_label_id = "id";
  public static final String col_label_content = "content";
  public static final String col_label_ans_option_id = "ans_option_id";
  public static final String col_label_time = "time";
  public static final String col_label_done = "done";

  private static final String col_id = "`" + col_label_id + "`";
  private static final String col_content = "`" + col_label_content + "`";
  private static final String col_ans_option_id = "`" + col_label_ans_option_id + "`";
  private static final String col_time = "`" + col_label_time + "`";
  private static final String col_done = "`" + col_label_done + "`";
  
  private static final String selectSql = String.format( "SELECT * FROM %s ", question_table );
  
  private static final String insertSql = String.format( "INSERT INTO %s (%s,%s,%s,%s) values ",
                                                         question_table,
                                                         col_content,
                                                         col_ans_option_id,
                                                         col_time,
                                                         col_done );
  
  private static final String updateSql = String.format( "UPDATE %s set %%s where %%s ", question_table );

  private static final String selectAllQuestionsSql = selectSql + ";";
  public static void SelectQuestion( Connection conn, Vector<Question> questions ) throws SQLException {
    Statement stmt = null;
    ResultSet result = null;
    try {
      stmt = conn.createStatement();
      result = stmt.executeQuery( selectAllQuestionsSql );
      while ( result.next() ) {
        questions.add( new Question( result.getInt( col_label_id ),
                                     result.getString( col_label_content ),
                                     result.getInt( col_label_ans_option_id ),
                                     result.getInt( col_label_time ),
                                     result.getBoolean( col_label_done ) ) );
      } // while

      String selectLog = "SelectQuestion: table='" + question_table + "'\r\n";
      int question_counter = 0;
      int question_number = questions.size();
      for ( Question question : questions ) {
        selectLog +=  String.format( "( %s='%d', %s='%s', %s='%d', %s='%d', %s='%b' )",
                                     QuestionTable.col_label_id, question.id,
                                     QuestionTable.col_label_content, question.content,
                                     QuestionTable.col_label_ans_option_id, question.ans_option_id,
                                     QuestionTable.col_label_time, question.time,
                                     QuestionTable.col_label_done, question.done );
        question_counter++;
        if ( question_counter <= question_number )
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
  } // SelectQuestion()
  
  
  
  private static final String selectQuestionByIdSql = String.format( selectSql + "where %s=?;", col_id );
  public static void SelectQuestionById( Connection conn, int id, Question question ) throws SQLException {
    PreparedStatement selectPStmt = null;
    ResultSet result = null;
    try {
      selectPStmt = conn.prepareStatement( selectQuestionByIdSql );
      selectPStmt.setInt( 1, id );
      result = selectPStmt.executeQuery();
      
      if ( result.next() ) {
        question.id = result.getInt( col_label_id );
        question.content = result.getString( col_label_content );
        question.ans_option_id = result.getInt( col_label_ans_option_id );
        question.time = result.getInt( col_label_time );
        question.done = result.getBoolean( col_label_done );
      } // if
      
      String selectLog = String.format( "SelectStudentById: table='%s'\r\n" +
                                        "( %s='%d', %s='%s', %s='%d', %s='%d', %s='%b' )\r\n" +
                                        "successful.",
                                        question_table,
                                        QuestionTable.col_label_id, question.id,
                                        QuestionTable.col_label_content, question.content,
                                        QuestionTable.col_label_ans_option_id, question.ans_option_id,
                                        QuestionTable.col_label_time, question.time,
                                        QuestionTable.col_label_done, question.done );
      System.out.println( selectLog );
    } // try
    finally {
      if ( selectPStmt != null ) selectPStmt.close();
      selectPStmt = null;
    } // finally
  } // SelectQuestionById()
  
  
  
  private static final String insertOneQuestionSql = insertSql + "(?,?,?,?);";
  public static void InsertQuestion( Connection conn, String content, int ans_option_id, int time, boolean done ) throws SQLException {
    PreparedStatement insertPStmt = null;
    try {
      insertPStmt = conn.prepareStatement( insertOneQuestionSql );
      insertPStmt.setString( 1, content );
      insertPStmt.setInt( 2, ans_option_id );
      insertPStmt.setInt( 3, time );
      insertPStmt.setBoolean( 4, done );
      insertPStmt.executeUpdate();

      String insertLog = String.format( "InsertQuestion: table='%s'\r\n" +
                                        "( %s='%s', %s='%d', %s='%d', %s='%b' )\r\n" +
                                        "successful.",
                                        question_table,
                                        QuestionTable.col_label_content, content,
                                        QuestionTable.col_label_ans_option_id, ans_option_id,
                                        QuestionTable.col_label_time, time,
                                        QuestionTable.col_label_done, done );
      System.out.println( insertLog );
    } // try
    finally {
      if ( insertPStmt != null ) insertPStmt.close();
      insertPStmt = null;
    } // finally
  } // InsertQuestion()
  
  
  
  public static void InsertQuestion( Connection conn, Vector<Question> questions ) throws SQLException {
    Statement insertStmt = null;
    try {
      int question_number = questions.size();
      if ( question_number <= 0 ) return ;
    
      String insertQuestionsSql = insertSql;
      int question_counter = 0;
      for ( Question question : questions ) {
        insertQuestionsSql += String.format( "('%s',%d,%d,%b)",
                                             question.content,
                                             question.ans_option_id,
                                             question.time );
        question_counter++;
        if ( question_counter < question_number )
          insertQuestionsSql += ",";
        else if ( question_counter == question_number )
          insertQuestionsSql += ";";
      } // for
    
      insertStmt = conn.createStatement();
      insertStmt.executeUpdate( insertQuestionsSql );

    
      String insertLog = "InsertQuestion: table='" + question_table + "'\r\n";
      question_counter = 0;
      for ( Question question : questions ) {
        insertLog += String.format( "( %s='%s', %s='%d', %s='%d', %s='%b' )",
                                    QuestionTable.col_label_content, question.content,
                                    QuestionTable.col_label_ans_option_id, question.ans_option_id,
                                    QuestionTable.col_label_time, question.time,
                                    QuestionTable.col_label_done, question.done );
        question_counter++;
        if ( question_counter <= question_number )
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
                                        question_table,
                                        QuestionTable.col_label_id, id,
                                        QuestionTable.col_label_content, content );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateContent()
  
  
  
  private static final String updateAns_option_idSql = String.format( updateSql + ";", col_ans_option_id + "=?", col_id + "=?" );
  public static void UpdateAns_option_id( Connection conn, int id, int ans_option_id ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateAns_option_idSql );
      updatePStmt.setInt( 1, ans_option_id );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateAns_option_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%s' )\r\n" +
                                        "successful.",
                                        question_table,
                                        QuestionTable.col_label_id, id,
                                        QuestionTable.col_label_ans_option_id, ans_option_id );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateAns_option_id()
  
  
  
  private static final String updateTimeSql = String.format( updateSql + ";", col_label_time + "=?", col_id + "=?" );
  public static void UpdateTime( Connection conn, int id, int time ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateTimeSql );
      updatePStmt.setInt( 1, time );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateAns_option_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%s' )\r\n" +
                                        "successful.",
                                        question_table,
                                        QuestionTable.col_label_id, id,
                                        QuestionTable.col_label_time, time );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateTime()
  
  
  private static final String updateDoneSql = String.format( updateSql + ";", col_label_done + "=?", col_id + "=?" );
  public static void UpdateDone( Connection conn, int id, boolean done ) throws SQLException {
    PreparedStatement updatePStmt = null;
    try {
      updatePStmt = conn.prepareStatement( updateDoneSql );
      updatePStmt.setBoolean( 1, done );
      updatePStmt.setInt( 2, id );
      updatePStmt.executeUpdate();
      
      String updateLog = String.format( "UpdateAns_option_id: table='%s'\r\n" +
                                        "( %s='%d', %s='%b' )\r\n" +
                                        "successful.",
                                        question_table,
                                        QuestionTable.col_label_id, id,
                                        QuestionTable.col_label_done, done );
      System.out.println( updateLog );
    } // try
    finally {
      if ( updatePStmt != null ) updatePStmt.close();
      updatePStmt = null;
    } // finally
  } // UpdateDone()
} // class QuestionTable
