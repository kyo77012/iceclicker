package cache;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Vector;

import data_struct.Option;
import data_struct.OptionSet;
import database.Database;
import database.OptionTable;
import service.Connection_utility;
import service.Exception_utility;

public class OptionSet_cache {
  public static Vector<Option> options;
  public static Vector<OptionSet> optionSets;
  public static Hashtable<Integer, OptionSet> optionSet_table;
  
  static {
    options = new Vector<Option>();
    optionSets = new Vector<OptionSet>();
    optionSet_table = new Hashtable<Integer, OptionSet>();
  } // static
  
  public static void Load() {
    options.removeAllElements();
    optionSets.removeAllElements();
    optionSet_table.clear();
    
    Connection conn = null;
    
    try {
      conn = Database.ConnectDatabase();
      OptionTable.SelectOption( conn, options );

      for ( Option option : options ) {
        if ( optionSet_table.containsKey( option.getQuestion_id() ) ) {
          OptionSet optionSet = optionSet_table.get( option.getQuestion_id() );
          optionSet.options.add( option );
        } // if
        else {
          OptionSet newOptionSet = new OptionSet( option.getQuestion_id() );
          newOptionSet.options.add( option );
          optionSet_table.put( newOptionSet.question_id, newOptionSet );
        } // else
      } // for
      
      System.out.println( "OptionSet_cache loaded." );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
    finally {
      Connection_utility.CloseConnection( conn );
    } // finally
  } // Load()
} // class OptionSet_cache
