package data_struct;

public class Record {
  public int id;
  public String student_id;
  public int question_id;
  public int option_id;
  
  private static final int empty_id = -1;
  private static final String empty_student_id = "-1";
  private static final int empty_question_id = -1;
  private static final int empty_option_id = -1;
  
  public Record() {
    super();
    this.id = empty_id;
    this.student_id = empty_student_id;
    this.question_id = empty_question_id;
    this.option_id = empty_option_id;
  } // Record()
  
  public Record( String student_id, int question_id, int option_id ) {
    super();
    this.id = empty_id;
    this.student_id = student_id;
    this.question_id = question_id;
    this.option_id = option_id;
  } // Record()
  
  public Record( int id, String student_id, int question_id, int option_id ) {
    super();
    this.id = id;
    this.student_id = student_id;
    this.question_id = question_id;
    this.option_id = option_id;
  } // Record()
  
  public boolean isEmpty() {
    if ( this.id == empty_id &&
         this.student_id == empty_student_id &&
         this.question_id == empty_question_id &&
         this.option_id == empty_id ) return true;
    else return false;
  } // isEmpty()
} // class Record
