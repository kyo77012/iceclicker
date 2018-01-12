package data_struct;

import java.util.Vector;

public class OptionSet {
  public int question_id;
  public Vector<Option> options;
  
  public OptionSet( int question_id ) {
    this.question_id = question_id;
    this.options = new Vector<Option>();
  } // OptionSet()
} // class OptionSet
