package data_struct;

import java.util.Vector;

public class QuestionSet_out {
  public int time;
  public Vector<Question_out> questions;
  
  private static int empty_time = -1;
  
  public QuestionSet_out( int time ) {
    this.time = time;
    this.questions = new Vector<Question_out>();
  } // QuestionSet_out()
  
  public QuestionSet_out( QuestionSet questionSet ) {
    if ( questionSet == null ) {
      this.time = empty_time;
      this.questions = new Vector<Question_out>();
    } // if
    else {
      this.time = questionSet.time;
      this.questions = new Vector<Question_out>();
      for ( Question question : questionSet.questions ) {
        this.questions.add( new Question_out( question ) );
      } // for
    } // else
  } // QuestionSet_out()
} // class QuestionSet_out
