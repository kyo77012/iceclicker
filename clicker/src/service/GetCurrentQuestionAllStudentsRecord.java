package service;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Record_cache;
import cache.Student_cache;
import data_struct.Question;
import data_struct.Record;
import data_struct.Record_and_Remote;
import data_struct.Student;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class GetCurrentQuestionAllStudentsRecord
 */
@WebServlet( "/GetCurrentQuestionAllStudentsRecord" )
public class GetCurrentQuestionAllStudentsRecord extends HttpServlet {
  private static final long serialVersionUID = 8720939624207571463L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GetCurrentQuestionAllStudentsRecord() {
    super();
  } // GetCurrentQuestionAllStudentsRecord()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      Question currentQuestion = CurrentQuestion.getCurrentQuestion();
      Vector<Record_and_Remote> currentQuestionAllRecords = new Vector<Record_and_Remote>();
      if ( currentQuestion != null ) {
        for ( Student student : Student_cache.students ) {
          Hashtable<Integer, Record> question_record_table 
            = Record_cache.student_record_table.get( student.id );
          
          if ( question_record_table != null ) {
            Record record = question_record_table.get( currentQuestion.id );
            
            if ( record != null ) {
              currentQuestionAllRecords.add( new Record_and_Remote( record ) );
            } // if
          } // if
        } // for
      } // if
      
      Response_utility.Setup( response,
                              Response_type.OK,
                              currentQuestionAllRecords );
    } // try
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "get record failed!" );
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
} // class GetCurrentQuestionAllStudentsRecord
