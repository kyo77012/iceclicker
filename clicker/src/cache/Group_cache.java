package cache;

import java.util.Hashtable;
import java.util.Vector;

import data_struct.Group;
import data_struct.Student;
import data_struct.Student_for_other;
import service.Exception_utility;

public class Group_cache {
  public static Vector<Group> groups;
  public static Hashtable<Integer, Group> group_table;
  
  static {
    groups = new Vector<Group>();
    group_table = new Hashtable<Integer, Group>();
  } // static
  
  public static void Load() {
    groups.removeAllElements();
    group_table.clear();
    
    try {
      for ( Student student : Student_cache.students ) {
        if ( group_table.containsKey( student.group ) ) {
          Group group = group_table.get( student.group );
          group.members.add( new Student_for_other( student ) );
        } // if
        else {
          Group newGroup = new Group( student.group );
          newGroup.members.add( new Student_for_other( student ) );
          groups.add( newGroup );
          group_table.put( newGroup.number, newGroup );
        } // else
      } // for
      
      System.out.println( "Group_cache loaded." );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
    finally {
    } // finally
  } // Load()
} // class Group_cache
