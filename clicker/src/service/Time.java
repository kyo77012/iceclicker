package service;

import java.time.LocalDateTime;
import java.time.ZoneId;


public class Time {
  public static int current_time;

  public static void Load() {
    LocalDateTime currentDateTime = LocalDateTime.now( ZoneId.of( "UTC+8" ) );
    
    int year = currentDateTime.getYear();
    int month = currentDateTime.getMonthValue();
    int day = currentDateTime.getDayOfMonth();
    
    String str_year = Integer.toString( year );
    String str_month = Integer.toString( month );
    String str_day = Integer.toString( day );
    
    if ( month < 10 ) {
      str_month = "0" + str_month;
    } // if
    
    if ( day < 10 ) {
      str_day = "0" + str_day;
    } // if
    
    Time.current_time = Integer.parseInt( str_year + str_month + str_day );
    // Time.current_time = 20160512;
    System.out.println( Time.current_time );
  } // Load()
} // class Time
