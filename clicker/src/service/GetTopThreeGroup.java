package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Ranking_list_cache;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetTopThree
 */
@WebServlet( "/GetTopThreeGroup" )
public class GetTopThreeGroup extends HttpServlet {
  private static final long serialVersionUID = -8717893481467915438L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GetTopThreeGroup() {
    super();
  } // GetTopThree()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      Response_utility.Setup( response,
                              Response_type.OK,
                              Ranking_list_cache.top_three_group );
    } // try
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
} // class GetTopThreeGroup
