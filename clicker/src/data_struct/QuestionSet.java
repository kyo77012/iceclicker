package data_struct;

import java.util.Vector;

public class QuestionSet {
  public int time;
  public Vector<Question> questions;
  
  public QuestionSet( int time ) {
    this.time = time;
    this.questions = new Vector<Question>();
  } // QuestionSet()
} // class QuestionSet
