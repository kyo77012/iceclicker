package service;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Question_cache;
import data_struct.Question;
import data_struct.QuestionSet;
import data_struct.QuestionSet_out;
import data_struct.Question_out;
import database.Database;
import database.QuestionTable;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetCurrentQuestion
 */
@WebServlet( "/CurrentQuestion" )
public class CurrentQuestion extends HttpServlet {
  private static final long serialVersionUID = 4506210970027722229L;
  
  public static QuestionSet todayQuestions;
  public static QuestionSet_out todayQuestions_out;
  private static int index;
  private static Question currentQuestion;
  private static Question_out currentQuestion_out;
  /*
  private static String currentQuestion_json = null;
  private static String currentQuestion_out_json = null;
  private static Gson gson = new Gson();
  */
  
  public static void Load() {
    CurrentQuestion.todayQuestions = Question_cache.questionSet_table.get( Time.current_time );
    CurrentQuestion.todayQuestions_out = new QuestionSet_out( todayQuestions );
    CurrentQuestion.index = -1;
    CurrentQuestion.currentQuestion = null;
    CurrentQuestion.currentQuestion_out = null;
    /*
    CurrentQuestion.currentQuestion_json = null;
    CurrentQuestion.currentQuestion_out_json = null;
    Gson gson = new Gson();
    */
  } // Load()
  
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public CurrentQuestion() {
    super();
  } // CurrentQuestion()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      if ( CurrentQuestion.currentQuestion != null ) {
        Response_utility.Setup( response,
                                Response_type.OK,
                                CurrentQuestion.currentQuestion_out );
      } // if
      else {
        Response_utility.Setup( response,
                                Response_type.OK,
                                null );
      } // else
    } // try
    catch( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "get current question failed!" );
      Exception_utility.PrintException( exception );
    } // catch()
    finally {
    } // finally
  } // doGet()

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    doGet( request, response );
  } // doPost()
  
  private static void SwitchQuestion( int index ) throws Switch_question_exception {
    if ( CurrentQuestion.todayQuestions != null &&
         index >= 0 &&
         index < CurrentQuestion.todayQuestions.questions.size() ) {
      CurrentQuestion.currentQuestion = CurrentQuestion.todayQuestions.questions.get( index );
      CurrentQuestion.currentQuestion_out = CurrentQuestion.todayQuestions_out.questions.get( index );
      
      /*
      CurrentQuestion.currentQuestion_json = CurrentQuestion.gson.toJson( CurrentQuestion.currentQuestion );
      CurrentQuestion.currentQuestion_out_json = CurrentQuestion.gson.toJson( CurrentQuestion.currentQuestion_out );
      */
    } // if
    else {
      throw new Switch_question_exception( "" );
    } // else
  } // SwitchQuestion()
  
  public static Question PrevQuestion() throws No_prev_question_exception {
    try {
      SwitchQuestion( CurrentQuestion.index - 1 );
      CurrentQuestion.index--;
      return CurrentQuestion.currentQuestion;
    } // try
    catch ( Switch_question_exception switch_question_exception ) {
      throw new No_prev_question_exception();
    } // catch
  } // PrevQuestion()
  
  public static Question NextQuestion() throws No_next_question_exception {
    try {
      SwitchQuestion( CurrentQuestion.index + 1 );
      CurrentQuestion.index++;
      return CurrentQuestion.currentQuestion;
    } // try
    catch ( Switch_question_exception switch_question_exception ) {
      throw new No_next_question_exception();
    } // catch
  } // NextQuestion()
  
  
  
  /*
  public static String PrevQuestion_json() throws No_prev_question_exception {
    CurrentQuestion.PrevQuestion();
    return CurrentQuestion.currentQuestion_json;
  } // PrevQuestion_json()
  
  public static String NextQuestion_json() throws No_next_question_exception {
    CurrentQuestion.NextQuestion();
    return CurrentQuestion.currentQuestion_json;
  } // NextQuestion_json()
  */
  
  
  
  public static void SetCurrentQuestionDone() throws Update_currentQuestion_done_exception {
    if ( CurrentQuestion.currentQuestion != null ) {
      CurrentQuestion.currentQuestion.done = true;
      CurrentQuestion.currentQuestion_out.done = true;
      
      /*
      CurrentQuestion.currentQuestion_json = gson.toJson( CurrentQuestion.currentQuestion );
      CurrentQuestion.currentQuestion_out_json = gson.toJson( CurrentQuestion.currentQuestion_out );
      */
      
      
      /*
      Question question = Question_cache.question_table.get( CurrentQuestion.currentQuestion.id );
      question.done = true;
      */
      
      Connection conn = null;
      try {
        conn = Database.ConnectDatabase();
        QuestionTable.UpdateDone( conn,
                                  CurrentQuestion.currentQuestion.id,
                                  CurrentQuestion.currentQuestion.done );
      } // try
      catch ( Exception exception ) {
        Exception_utility.PrintException( exception );
        throw new Update_currentQuestion_done_exception();
      } // catch()
      finally {
        Connection_utility.CloseConnection( conn );
      } // finally
    } // if
  } // SetCurrentQuestionDone()
  
  /*
  public static void setCurrentQuestion( Question currentQuestion ) {
    GetCurrentQuestion.currentQuestion = currentQuestion;
    GetCurrentQuestion.currentQuestion_out = new Question_out( currentQuestion );
  } // setCurrentQuestion()
  */
  
  public static Question getCurrentQuestion() {
    return CurrentQuestion.currentQuestion;
  } // getCurrentQuestion()
  
  public static Question_out getCurrentQuestion_out() {
    return CurrentQuestion.currentQuestion_out;
  } // getCurrentQuestion_out()
  
  
  /*
  public static String getCurrentQuestion_json() {
    return CurrentQuestion.currentQuestion_json;
  } // getCurrentQuestion_json()
  
  public static String getCurrentQuestion_out_json() {
    return CurrentQuestion.currentQuestion_out_json;
  } // getCurrentQuestion_out_json()
  */
} // class CurrentQuestion



class CurrentQuestion_Exception extends Exception {
  private static final long serialVersionUID = 2810895371087547780L;
  
  public CurrentQuestion_Exception( String msg ) {
    super( msg );
  } // CurrentQuestion_Exception()
} // CurrentQuestion_Exception()



class Switch_question_exception extends CurrentQuestion_Exception {
  private static final long serialVersionUID = 552024592796673269L;
  
  public Switch_question_exception( String msg ) {
    super( msg );
  } // Switch_question_exception()
} // class Switch_question_exception

class No_prev_question_exception extends Switch_question_exception {
  private static final long serialVersionUID = 5303479377813839852L;

  public No_prev_question_exception() {
    super( "no prev question!" );
  } // No_prev_question_exception()
} // class No_prev_question_exception

class No_next_question_exception extends Switch_question_exception {
  private static final long serialVersionUID = 5303479377813839852L;

  public No_next_question_exception() {
    super( "no next question!" );
  } // No_next_question_exception()
} // class No_next_question_exception



class Update_currentQuestion_done_exception extends CurrentQuestion_Exception {
  private static final long serialVersionUID = 1350206063527824417L;

  public Update_currentQuestion_done_exception() {
    super( "update currentQuestion done failed!" );
  } // Update_currentQuestion_done_exception()
} // class Update_currentQuestion_done_exception
