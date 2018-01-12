package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetCurrentQuestionAns
 */
@WebServlet( "/GetCurrentQuestionAns" )
public class GetCurrentQuestionAns extends HttpServlet {
  private static final long serialVersionUID = 11399675168369525L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GetCurrentQuestionAns() {
    super();
  } // GetCurrentQuestionAns()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      if ( CurrentQuestion.getCurrentQuestion().done )
        Response_utility.Setup( response,
                                Response_type.OK,
                                CurrentQuestion.getCurrentQuestion() );
      else
        throw new CurrentQuestion_is_not_done_exception();
    } // try
    catch ( GetCurrentQuestionAnsException get_currentQuestion_ans_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              get_currentQuestion_ans_exception.getMessage() );
      Exception_utility.PrintException( get_currentQuestion_ans_exception );
    } // catch
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "get current question ans failed!" );
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
} // class GetCurrentQuestionAns



class GetCurrentQuestionAnsException extends Exception {
  private static final long serialVersionUID = 1251069179418422354L;
  
  public GetCurrentQuestionAnsException( String msg ) {
    super( msg );
  } // GetCurrentQuestionAnsException()
} // class GetCurrentQuestionAnsException

class CurrentQuestion_is_not_done_exception extends GetCurrentQuestionAnsException {
  private static final long serialVersionUID = -6434542334196181731L;
  
  public CurrentQuestion_is_not_done_exception() {
    super( "目前題目尚未作答完畢!" );
  } // CurrentQuestion_is_not_done_exception()
} // class CurrentQuestion_is_not_done_exception
