package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Group_cache;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetGroupMembers
 */
@WebServlet( "/GetGroupMembers" )
public class GetGroupMembers extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = 535772986257136892L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  
  /*
  private static Hashtable<Integer, Group> groups;
  */
  
  public GetGroupMembers() {
    super();
  } // GetGroupMembers()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      int group = Integer.parseInt( request.getParameter( "group" ) );

      Response_utility.Setup( response,
                              Response_type.OK,
                              Group_cache.group_table.get( group ) );
    } // try
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "get group members failed!" );
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
} // class GetGroupMembers
