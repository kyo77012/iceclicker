package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Ranking_list_cache;
import data_struct.Group_rank;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetGroupRank
 */
@WebServlet( "/GetGroupRank" )
public class GetGroupRank extends HttpServlet {
  private static final long serialVersionUID = 5849530666842408429L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GetGroupRank() {
    super();
  } // GetGroupRank()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      int group_number = Integer.parseInt( request.getParameter( "group_number" ) );
      Group_rank group_rank = Ranking_list_cache.ranking_list_group_rank_table.get( group_number );
      if ( group_rank != null ) {
        Response_utility.Setup( response,
                                Response_type.OK,
                                group_rank );
      } // if
      else throw new Group_does_not_exist_exception();
    } // try
    catch ( Get_group_rank_exception get_group_rank_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              null );
    } // catch
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              null );
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
} // class GetGroupRank

class Get_group_rank_exception extends Exception {
  private static final long serialVersionUID = 1247444921807663142L;
  
  public Get_group_rank_exception( String msg ) {
    super( msg );
  } // Get_group_rank_exception()
} // class Get_group_rank_exception

class Group_does_not_exist_exception extends Get_group_rank_exception {
  private static final long serialVersionUID = -4425709714898964902L;

  public Group_does_not_exist_exception() {
    super( "Group doesn't exist!" );
  } // Group_does_not_exist_exception()
} // class Group_does_not_exist_exception
