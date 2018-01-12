package data_struct;

public class Option {
  private int id;
  private int question_id;
  private String content;
  
  private static final int empty_id = -1;
  private static final int empty_question_id = -1;
  private static final String empty_content = "-1";
  
  public Option() {
    super();
    this.id = empty_id;
    this.question_id = empty_question_id;
    this.content = empty_content;
  } // Option()
  
  public Option( int question_id, String content ) {
    super();
    this.question_id = question_id;
    this.content = content;
  } // Option()
  
  public Option( int id, int question_id, String content ) {
    super();
    this.id = id;
    this.question_id = question_id;
    this.content = content;
  } // Option()
  
  public boolean IsEmpty() {
    if ( this.id == empty_id &&
         this.question_id == empty_id &&
         this.content == empty_content ) return true;
    else return false;
  } // IsEmpty()
  
  
  
  public void setId( int id ) {
    this.id = id;
  } // setId()
  
  public void setQuestion_id( int question_id ) {
    this.question_id = question_id;
  } // setQuestion_id()
  
  public void setContent( String content ) {
    this.content = content;
  } // setContent()
  
  
  
  public int getId() {
    return this.id;
  } // getId()
  
  public int getQuestion_id() {
    return this.question_id;
  } // getQuestion_id()
  
  public String getContent() {
    return this.content;
  } // getContent()
} // class Option
