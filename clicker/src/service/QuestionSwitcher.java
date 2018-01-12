package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Response_utility.Response_type;

/**
 * Servlet implementation class NextQuestion
 */
@WebServlet( "/QuestionSwitcher" )
public class QuestionSwitcher extends HttpServlet {
  private static final long serialVersionUID = 8077562366463389488L;

  /**
   * @see HttpServlet#HttpServlet()
   */

  public QuestionSwitcher() {
    super();
  } // NextQuestion()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      String action = request.getParameter( "action" );
      if ( action.equals( "current_question" ) ) {
        Response_utility.Setup( response,
                                Response_type.OK,
                                CurrentQuestion.getCurrentQuestion() );
      } // if
      else if ( action.equals( "prev_question" ) ) {
        Response_utility.Setup( response,
                                Response_type.OK,
                                CurrentQuestion.PrevQuestion() );
      } // else if
      else if ( action.equals( "next_question" ) ) {
        Response_utility.Setup( response,
                                Response_type.OK,
                                CurrentQuestion.NextQuestion() );
      } // else if
      else
        throw new Unknown_action_exception();
    } // try
    catch ( Switch_question_exception switch_question_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              switch_question_exception.getMessage() );
      // Exception_utility.PrintException( switch_question_exception );
    } // catch
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
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
} // class QuestionSwitcher

class Unknown_action_exception extends Exception {
  private static final long serialVersionUID = 7132158235246662507L;

  public Unknown_action_exception() {
    super( "unknown action!" );
  } // Unknown_action_exception
} // class Unknown_action_exception
