package cache;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Vector;

import data_struct.Question;
import data_struct.QuestionSet;
import database.Database;
import database.QuestionTable;
import service.Connection_utility;
import service.Exception_utility;

public class Question_cache {
  public static Vector<Question> questions;
  public static Vector<QuestionSet> questionSets;
  public static Hashtable<Integer, QuestionSet> questionSet_table;
  public static Hashtable<Integer, Question> question_table;
  
  static {
    questions = new Vector<Question>();
    questionSets = new Vector<QuestionSet>();
    questionSet_table = new Hashtable<Integer, QuestionSet>();
    question_table = new Hashtable<Integer, Question>();
  } // static
  
  public static void Load() {
    questions.removeAllElements();
    questionSets.removeAllElements();
    questionSet_table.clear();
    question_table.clear();
    
    Connection conn = null;
    try {
      conn = Database.ConnectDatabase();
      QuestionTable.SelectQuestion( conn, questions );
      for ( Question question : questions ) {
        if ( questionSet_table.containsKey( question.time ) ) {
          QuestionSet questionSet = questionSet_table.get( question.time );
          questionSet.questions.add( question );
        } // if
        else {
          QuestionSet newQuestionSet = new QuestionSet( question.time );
          newQuestionSet.questions.add( question );
          questionSets.add( newQuestionSet );
          questionSet_table.put( newQuestionSet.time, newQuestionSet );
        } // else
        
        question_table.put( question.id, question );
      } // for
      
      System.out.println( "Question_cache loaded." );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
    finally {
      Connection_utility.CloseConnection( conn );
    } // finally
  } // Load()
} // class Question_cache
