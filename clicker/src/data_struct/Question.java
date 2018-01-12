package data_struct;

public class Question {
  public int id;
  public String content;
  public int ans_option_id;
  public int time;
  public boolean done;
  
  private static final int empty_id = -1;
  private static final String empty_content = "-1";
  private static final int empty_ans_option_id = -1;
  private static final int empty_time = -1;
  private static final boolean empty_done = false;
  
  
  public Question() {
    super();
    this.id = empty_id;
    this.content = empty_content;
    this.ans_option_id = empty_ans_option_id;
    this.time = empty_time;
    this.done = empty_done;
  } // Question()
  
  public Question( String content, int ans_option_id, int time, boolean done ) {
    super();
    this.id = empty_id;
    this.content = content;
    this.ans_option_id = ans_option_id;
    this.time = time;
    this.done = done;
  } // Question()
  
  public Question( int id, String content, int ans_option_id, int time, boolean done ) {
    super();
    this.id = id;
    this.content = content;
    this.ans_option_id = ans_option_id;
    this.time = time;
    this.done = done;
  } // Question()
  
  public boolean isEmpty() {
    if ( this.id == empty_id &&
         this.content == empty_content &&
         this.ans_option_id == empty_ans_option_id &&
         this.time == empty_time &&
         this.done == empty_done ) return true;
    else return false;
  } // isEmpty()
} // class Question
