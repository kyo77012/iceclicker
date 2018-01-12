package data_struct;

import cache.Student_cache;

public class Record_and_Remote {
  public int remote_ctrl_number;
  public String student_id;
  public int question_id;
  public int option_id;
  
  public Record_and_Remote( Record record ) {
    this.remote_ctrl_number = Student_cache.student_table.get( record.student_id ).remote_ctrl_num;
    this.student_id = record.student_id;
    this.question_id = record.question_id;
    this.option_id = record.option_id;
  } // Record_and_Remote()
} // class Record_and_Remote
