package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Record_cache;
import cache.Record_cache_exception;
import cache.Student_cache;
import data_struct.Option;
import data_struct.Question;
import data_struct.Question_out;
import data_struct.Record;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class ReceiveAns
 */
@WebServlet( "/ReceiveAns" )
public class ReceiveAns extends HttpServlet {
  private static final long serialVersionUID = -7996323489960500525L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ReceiveAns() {
    super();
  } // ReceiveAns()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    
    try {
      String client_student_id = request.getParameter( "student_id" );
      int client_question_id = Integer.parseInt( request.getParameter( "question_id" ) );
      int client_option_id = Integer.parseInt( request.getParameter( "option_id" ) );
      
      Question currentQuestion = CurrentQuestion.getCurrentQuestion();
      Question_out currentQuestion_out = CurrentQuestion.getCurrentQuestion_out();
      if ( currentQuestion != null &&
           Student_cache.student_table.get( client_student_id ) != null &&
           currentQuestion.id == client_question_id &&
           QuestionHaveThisOption( currentQuestion_out, client_option_id ) ) {
        Record newRecord = new Record( client_student_id,
                                       client_question_id,
                                       client_option_id );
        
        Record_cache.AddRecord( newRecord );
        
        Response_utility.Setup( response,
                                Response_type.OK,
                                "ans received!" );
      } // if
      else throw new Receive_ans_failed_exception();
    } // try
    catch ( Receive_ans_failed_exception receive_ans_failed_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              receive_ans_failed_exception.getMessage() );
      Exception_utility.PrintException( receive_ans_failed_exception );
    } // catch
    catch ( Record_cache_exception record_cache_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              record_cache_exception.getMessage() );
      Exception_utility.PrintException( record_cache_exception );
    } // catch
    catch ( Exception exception ) {
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
  
  
  private static boolean QuestionHaveThisOption( Question_out question_out, int client_option_id ) {
    for ( Option option : question_out.options ) {
      if ( option.getId() == client_option_id ) return true;
    } // for
    
    return false;
  } // QuestionHaveThisOption()
} // class ReceiveAns


class Receive_ans_failed_exception extends Exception {
  private static final long serialVersionUID = -2048248426639284766L;
  
  public Receive_ans_failed_exception() {
    super( "receive ans failed!" );
  } // Receive_ans_failed_exception()
} // class Receive_ans_failed_exception

