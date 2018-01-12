package data_struct;

import java.util.Vector;

import cache.OptionSet_cache;

public class Question_out {
  public int id;
  public String content;
  public boolean done;
  public Vector<Option> options;
  
  public Question_out( int id, String content, boolean done ) {
    this.id = id;
    this.content = content;
    this.done = done;
    this.options = new Vector<Option>();
  } // Question_out()
  
  public Question_out( Question question ) {
    this.id = question.id;
    this.content = question.content;
    this.done = question.done;
    
    OptionSet optionSet = OptionSet_cache.optionSet_table.get( question.id );
    if ( optionSet != null )
      this.options = optionSet.options;
    else
      this.options = new Vector<Option>();
  } // Question_out()
} // class Question_out
