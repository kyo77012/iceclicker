package data_struct;

public class Student_self {
  public String id;
  public String name;
  public int group;
  public int remote_ctrl_num;
  public byte[] cid;
  public boolean isAttended;
  
  public Student_self( Student student ) {
    this.id = student.id;
    this.name = student.name;
    this.group = student.group;
    this.remote_ctrl_num = student.remote_ctrl_num;
    this.cid = student.cid;
    this.isAttended = false;
  } // Student_self()
} // class Student_self
