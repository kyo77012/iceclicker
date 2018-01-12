package service;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Record_cache;
import data_struct.Ans_and_Record;
import data_struct.Question;
import data_struct.Record;
import service.Response_utility.Response_type;

/**
 * Servlet implementation class CheckAns
 */
@WebServlet( "/CheckAns" )
public class CheckAns extends HttpServlet {
  private static final long serialVersionUID = -941708941794965664L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public CheckAns() {
    super();
  } // CheckAns()

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    try {
      String student_id = request.getParameter( "student_id" );
      int question_id = Integer.parseInt( request.getParameter( "question_id" ) );
      
      Question currentQuestion = CurrentQuestion.getCurrentQuestion();
      if ( currentQuestion != null ) {
        if ( currentQuestion.id == question_id ) {
          if ( currentQuestion.done ) {
            Hashtable<Integer, Record> question_record_table = Record_cache.student_record_table.get( student_id );
            Record student_record = null;
            Ans_and_Record ans_and_record;
            
            if ( question_record_table != null )
              student_record = question_record_table.get( question_id );

            ans_and_record = new Ans_and_Record( currentQuestion.ans_option_id, student_record );
            Response_utility.Setup( response,
                                    Response_type.OK,
                                    ans_and_record );
          } // if
          else throw new Question_is_not_done_exceptione();
        } // if
        else throw new Question_id_does_not_match_exception();
      } // if
      else throw new No_current_question_exception();
    } // try
    catch ( Check_ans_exception check_ans_exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              check_ans_exception.getMessage() );
      Exception_utility.PrintException( check_ans_exception );
    } // catch
    catch ( Exception exception ) {
      Response_utility.Setup( response,
                              Response_type.Error,
                              "CheckAns failed!" );
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
} // class CheckAns



class Check_ans_exception extends Exception {
  private static final long serialVersionUID = -3026884694900537030L;
  
  public Check_ans_exception( String msg ) {
    super( msg );
  } // Check_ans_exception()
} // class Check_ans_exception

class No_current_question_exception extends Check_ans_exception {
  private static final long serialVersionUID = -7244445026739136367L;

  public No_current_question_exception() {
    super( "目前沒有題目可以作答!" );
  } // No_current_question_exception()
} // class No_current_question_exception

class Question_id_does_not_match_exception extends Check_ans_exception {
  private static final long serialVersionUID = -3465518842517847166L;

  public Question_id_does_not_match_exception() {
    super( "目前非作答此題時間!" );
  } // No_current_question_exception()
} // class Question_id_does_not_match_exception

class Question_is_not_done_exceptione extends Check_ans_exception {
  private static final long serialVersionUID = -4844262382439593737L;

  public Question_is_not_done_exceptione() {
    super( "此題尚未做答完畢!" );
  } // Question_is_not_done_exceptione()
} // class Question_is_not_done_exceptione
