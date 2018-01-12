package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Response_utility.Response_type;

/**
 * Servlet implementation class CurrentQuestionDone
 */
@WebServlet( "/CurrentQuestionDone" )
public class CurrentQuestionDone extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CurrentQuestionDone() {
    super();
  } // CurrentQuestionDone()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      CurrentQuestion.SetCurrentQuestionDone();
      Response_utility.Setup( response,
                              Response_type.OK,
                              "CurrentQuestionDone done." );
    } // try
    catch ( CurrentQuestion_Exception currentQuestion_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              currentQuestion_exception.getMessage() );
    } // catch
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "CurrentQuestionDone failed!" );
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
} // class CurrentQuestionDone
