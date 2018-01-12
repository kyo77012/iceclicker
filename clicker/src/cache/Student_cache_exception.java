package cache;

public class Student_cache_exception extends Exception {
  private static final long serialVersionUID = 3501933453547698271L;
  
  public Student_cache_exception( String msg ) {
    super( msg );
  } // Student_cache_exception()
} // class Student_cache_exception

class Update_password_failed_exception extends Student_cache_exception {
  private static final long serialVersionUID = -6032968150745203672L;
  
  public Update_password_failed_exception() {
    super( "修改密碼失敗!" );
  } // Update_password_failed_exception()
} // class Update_password_failed_exception
