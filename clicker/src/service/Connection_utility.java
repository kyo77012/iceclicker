package service;

import java.sql.Connection;

public class Connection_utility {
  public static void CloseConnection( Connection conn ) {
    if ( conn != null ) {
      try {
        conn.close();
      } // try
      catch ( Exception exception ) {
        System.out.println( exception.getMessage() );
        exception.printStackTrace();
      } // catch
    } // if
  } // CloseConnection()
} // class Connection_utility
