package service;

public class Exception_utility {
  public static void PrintException( Exception exception ) {
    System.out.println( exception.getMessage() );
    exception.printStackTrace();
  } // PrintException()
} // class Exception_utility
