package cache;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Vector;

import data_struct.Student;
import data_struct.Student_self;
import database.Database;
import database.StudentTable;
import service.Connection_utility;
import service.Exception_utility;

public class Student_cache {
  public static Vector<Student> students;
  public static Vector<Student_self> students_out;
  public static Hashtable<String, Student> student_table;
  
  static {
    students = new Vector<Student>();
    students_out = new Vector<Student_self>();
    student_table = new Hashtable<String, Student>();
  } // static
  
  public static void Load() {
    students.removeAllElements();
    students_out.removeAllElements();
    student_table.clear();
    
    Connection conn = null;
    try {
      conn = Database.ConnectDatabase();
      StudentTable.SelectStudent( conn, students );
      for ( Student student : students ) {
        students_out.addElement( new Student_self( student ) );
        student_table.put( student.id, student );
      } // for
      
      System.out.println( "Student_cache loaded." );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
    finally {
      Connection_utility.CloseConnection( conn );
    } // finally
  } // Load()
  
  public static void UpdatePassword( Student student, String newPassword ) throws Update_password_failed_exception {
    student.password = newPassword;
    
    Connection conn = null;
    
    try {
      conn = Database.ConnectDatabase();
      StudentTable.UpdatePassword( conn, student.id, newPassword );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
      throw new Update_password_failed_exception();
    } // catch
    finally {
      Connection_utility.CloseConnection( conn );
    } // finally
  } // UpdatePassword()
} // class Student_cache
