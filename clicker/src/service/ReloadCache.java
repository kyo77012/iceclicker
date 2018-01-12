package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Response_utility.Response_type;

/**
 * Servlet implementation class ReloadCache
 */
@WebServlet( "/ReloadCache" )
public class ReloadCache extends HttpServlet {
  private static final long serialVersionUID = 5075539316685100016L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ReloadCache() {
    super();
  } // ReloadCache()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      ( new Initiation() ).init();
      Response_utility.Setup( response, Response_type.OK, "ReloadCache done." );
      System.out.println( "ReloadCache done." );
    } // try
    catch ( Exception exception ) {
      Response_utility.Setup( response, Response_type.Error, "ReloadCache failed!" );
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
} // class ReloadCache
