package data_struct;

import service.Cid;

public class Student {
  public String id;
  public String name;
  public int group;
  public String password;
  public int remote_ctrl_num;
  public byte[] cid;

  private static final String empty_id = "-1";
  private static final String empty_name = "-1";
  private static final int empty_group = -1;
  private static final String empty_password = "-1";
  private static final int empty_remote_ctrl_num = -1;
  
  public Student() {
    this.id = Student.empty_id;
    this.name = Student.empty_name;
    this.group = Student.empty_group;
    this.password = Student.empty_password;
    this.remote_ctrl_num = Student.empty_remote_ctrl_num;
    this.cid = Cid.GetCid( this.id );
  } // Student()
  
  public Student( String id, String name, int group, String password, int remote_ctrl_num ) {
    this.id = id;
    this.name = name;
    this.group = group;
    this.password = password;
    this.remote_ctrl_num = remote_ctrl_num;
    this.cid = Cid.GetCid( this.id );
  } // Student()
  
  
  public boolean isEmpty() {
    if ( this.id == Student.empty_id &&
         this.name == Student.empty_name &&
         this.group == Student.empty_group &&
         this.password == Student.empty_password &&
         this.remote_ctrl_num == Student.empty_remote_ctrl_num ) return true;
    else return false;
  } // isEmpty()
} // class Student
