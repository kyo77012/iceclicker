package data_struct;

import java.util.Vector;

public class Group {
  public int number;
  public Vector<Student_for_other> members;
  public float score;
  public int attendance;
  public int rank;
  
  public Group( int number ) {
    this.number = number;
    this.members = new Vector<Student_for_other>();
    this.score = -1;
    this.attendance = -1;
    this.rank = -1;
  } // Group()
} // class Group
