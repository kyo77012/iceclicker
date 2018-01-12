package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Student_cache;
import data_struct.Student;
import data_struct.Student_self;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class Login
 */
@WebServlet( "/Login" )
public class Login extends HttpServlet {

  /**
   * 
   */
  private static final long serialVersionUID = -7405369947797380996L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Login() {
    super();
  } // Login()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException { 
    String id = request.getParameter( "id" );
    String password = request.getParameter( "password" );
    
    try {
      if ( id == null ) throw new Incorrect_account_exception();
      else if ( password == null ) throw new Incorrect_password_exception();
    
      Student student = Student_cache.student_table.get( id );
      if ( student == null ) throw new Incorrect_account_exception();
      else if ( !student.password.equals( password ) )
        throw new Incorrect_password_exception();
      
      Response_utility.Setup( response,
                              Response_type.OK,
                              new Student_self( student ) );
    } // try
    catch ( Login_exception login_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              login_exception.getMessage() );
    } // catch
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "Login failed!" );
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
} // class Login


class Login_exception extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = -3099388929989185505L;

  public Login_exception( String msg ) {
    super( msg );
  } // Login_exception()
} // class Login_exception

class Incorrect_account_exception extends Login_exception {
  /**
   * 
   */
  private static final long serialVersionUID = -5002428251216443310L;

  public Incorrect_account_exception() {
    super( "帳號錯誤!" );
  } // Incorrect_account_exception()
} // class Incorrect_account_exception()

class Incorrect_password_exception extends Login_exception {
  /**
   * 
   */
  private static final long serialVersionUID = 387515814268319440L;

  public Incorrect_password_exception() {
    super( "密碼錯誤!" );
  } // Incorrect_password_exception()
} // class Incorrect_password_exception
