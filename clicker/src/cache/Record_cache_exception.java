package cache;

public class Record_cache_exception extends Exception {
  private static final long serialVersionUID = -7944863435089192010L;
  
  public Record_cache_exception( String msg ) {
    super( msg );
  } // Record_cache_exception()
} // class Record_cache_exception

class Add_record_failed_exception extends Record_cache_exception {
  private static final long serialVersionUID = 8197995972467643130L;
  
  public Add_record_failed_exception() {
    super( "答題記錄上傳失敗!" );
  } // Add_record_failed_exception()
} // class Add_record_failed_exception

class HandleWait_for_insert_failed_exception extends Record_cache_exception {
  private static final long serialVersionUID = 5631448013847202841L;

  public HandleWait_for_insert_failed_exception() {
    super( "新增答題記錄到資料庫失敗!" );
  } // HandleWait_for_insert_failed_exception()
} // class HandleWait_for_insert_failed_exception

class HandleWait_for_update_failed_exception extends Record_cache_exception {
  private static final long serialVersionUID = 7272536071951451137L;

  public HandleWait_for_update_failed_exception() {
    super( "更新資料庫裡的答題記錄失敗!" );
  } // HandleWait_for_update_failed_exception()
} // class HandleWait_for_update_failed_exception