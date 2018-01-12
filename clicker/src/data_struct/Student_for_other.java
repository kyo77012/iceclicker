package data_struct;

public class Student_for_other {
  public String id;
  public String name;
  public int group;
  public int remote_ctrl_num;
  public boolean isAttended;
  public float score;
  
  public Student_for_other( Student student ) {
    this.id = student.id;
    this.name = student.name;
    this.group = student.group;
    this.remote_ctrl_num = student.remote_ctrl_num;
    this.isAttended = false;
    this.score = 0;
  } // Student_for_other()
} // class Student_for_other
