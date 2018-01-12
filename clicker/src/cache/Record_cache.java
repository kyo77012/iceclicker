package cache;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import data_struct.Record;
import database.Database;
import database.RecordTable;
import service.Connection_utility;
import service.Exception_utility;

public class Record_cache {
  public static Vector<Record> records;
  public static Hashtable<String, Hashtable<Integer, Record>> student_record_table;

  static {
    Record_cache.records = new Vector<Record>();
    Record_cache.student_record_table = new Hashtable<String, Hashtable<Integer, Record>>();
  } // static
  
  public static void Load() {
    Record_cache.records.removeAllElements();
    if ( Record_cache.student_record_table != null ) {
      Set<String> student_record_table_key_set = Record_cache.student_record_table.keySet();
      Iterator<String> it = student_record_table_key_set.iterator();
      String student_id;
      while ( it.hasNext() ) {
        student_id = it.next();
        Record_cache.student_record_table.get( student_id ).clear();
      } // for
    } // if
    Record_cache.student_record_table.clear();
    
    Connection conn = null;
    try {
      conn = Database.ConnectDatabase();
      RecordTable.SelectRecord( conn, Record_cache.records );
      
      Hashtable<Integer, Record> question_record_table;
      for ( Record record : Record_cache.records ) {
        question_record_table = Record_cache.student_record_table.get( record.student_id );
        if ( question_record_table == null ) {
          question_record_table = new Hashtable<Integer, Record>();
          question_record_table.put( record.question_id, record );
          
          Record_cache.student_record_table.put( record.student_id, question_record_table );
        } // if
        else {
          question_record_table.put( record.question_id, record );
        } // else
      } // for
      
      System.out.println( "Record_cache loaded." );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
    finally {
      Connection_utility.CloseConnection( conn );
    } // finally
  } // Load()
  
  public static void AddRecord( Record newRecord ) throws Add_record_failed_exception {
    Hashtable<Integer, Record> question_record_table
      = Record_cache.student_record_table.get( newRecord.student_id );
    
    Connection conn = null;
    try {
      conn = Database.ConnectDatabase();
      if ( question_record_table != null ) {
        Record oldRecord = question_record_table.get( newRecord.question_id );
        if ( oldRecord != null ) {
          RecordTable.UpdateOption_id( conn,
                                       oldRecord.id,
                                       newRecord.option_id );
          oldRecord.option_id = newRecord.option_id;
        } // if
        else {
          RecordTable.InsertRecord( conn,
                                    newRecord.student_id,
                                    newRecord.question_id,
                                    newRecord.option_id );
          RecordTable.SelectRecordByStudent_idAndQuestion_id( conn,
                                                              newRecord.student_id,
                                                              newRecord.question_id,
                                                              newRecord );
          question_record_table.put( newRecord.question_id, newRecord );
        } // else
      } // if
      else {
        RecordTable.InsertRecord( conn,
                                  newRecord.student_id,
                                  newRecord.question_id,
                                  newRecord.option_id );
        RecordTable.SelectRecordByStudent_idAndQuestion_id( conn,
                                                            newRecord.student_id,
                                                            newRecord.question_id,
                                                            newRecord );
        question_record_table = new Hashtable<Integer, Record>();
        question_record_table.put( newRecord.question_id, newRecord );
        Record_cache.student_record_table.put( newRecord.student_id, question_record_table );
      } // else
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
      throw new Add_record_failed_exception();
    } // catch
    finally {
      Connection_utility.CloseConnection( conn );
    } // finally
  } // AddRecord()
} // class Record_cache
