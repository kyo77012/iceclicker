package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Student_cache;
import cache.Student_cache_exception;
import data_struct.Student;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet( "/ChangePassword" )
public class ChangePassword extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ChangePassword() {
    super();
  } // ChangePassword()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    String id = request.getParameter( "id" );
    String password = request.getParameter( "password" );
    String newPassword = request.getParameter( "newPassword" );
    
    try {
      if ( id == null ) throw new Incorrect_account_exception();
      else if ( password == null ) throw new Incorrect_password_exception();
      else if ( newPassword == null ) throw new Incorrect_new_password_exception();
      
      Student student = Student_cache.student_table.get( id );
      if ( student == null ) throw new Incorrect_account_exception();
      else if ( !student.password.equals( password ) ) throw new Incorrect_password_exception();
      else Student_cache.UpdatePassword( student, newPassword );
      
      Response_utility.Setup( response,
                              Response_type.OK,
                              "success!" );
    } // try
    catch ( Login_exception login_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              login_exception.getMessage() );
      Exception_utility.PrintException( login_exception );
    } // catch
    catch ( Student_cache_exception student_cache_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              student_cache_exception.getMessage() );
      Exception_utility.PrintException( student_cache_exception );
    } // catch
  } // doGet()

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    doGet( request, response );
  } // doPost()
} // class ChangePassword


class Incorrect_new_password_exception extends Login_exception {
  /**
   * 
   */
  private static final long serialVersionUID = -3369615525533731898L;

  public Incorrect_new_password_exception() {
    super( "新密碼不正確!" );
  } // Incorrect_new_password_exception()
} // class
