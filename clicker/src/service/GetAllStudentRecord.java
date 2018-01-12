package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Ranking_list_cache;
import data_struct.Group;
import data_struct.Student_for_other;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetAllGroup
 */
@WebServlet( "/GetAllStudentRecord" )
public class GetAllStudentRecord extends HttpServlet {
  private static final long serialVersionUID = -5604817346924696958L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GetAllStudentRecord() {
    super();
  } // GetAllStudentRecord()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      Ranking_list_cache.Update();
      
      response.addHeader( "Content-type", "text/plain; charset=utf-8" );
      response.setCharacterEncoding( "UTF-8" );
      response.getWriter().println( "學號\t姓名\t遙控器號碼\t是否出席\t個人成績\t小組成績\t小組名次" );
      for ( Group group : Ranking_list_cache.ranking_list ) {
        for ( Student_for_other student : group.members ) {
          response.getWriter().printf( "%s\t%s\t%d\t%b\t%f\t%f\t%d\n",
                                       student.id, student.name, student.remote_ctrl_num,
                                       student.isAttended, student.score,
                                       group.score, group.rank );
        } // for
      } // for
    } // try
    catch ( Exception exception ) {
      Response_utility.Setup( response, Response_type.Error, "GetAllStudentRecord failed!" );
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
} // class GetAllStudentRecord
