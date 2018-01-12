package service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cache.Group_cache;
import cache.OptionSet_cache;
import cache.Question_cache;
import cache.Ranking_list_cache;
import cache.Record_cache;
import cache.Student_cache;
import database.Database;


public class Initiation extends HttpServlet {
  private static final long serialVersionUID = 1605448583371496502L;
  
  public void init() throws ServletException {
    this.destroy();
    Time.Load();
    Database.Setup();
    
    Student_cache.Load();
    Group_cache.Load();
    Question_cache.Load();
    OptionSet_cache.Load();
    Record_cache.Load();
    CurrentQuestion.Load();
    Ranking_list_cache.Load();
    
//    Record_cache.Start_Update();
    Ranking_list_cache.Start_Update();
    System.out.println( "Time: " + Time.current_time );
    System.out.println( "Initiation done." );
  } // init()
  
  
  public void destroy() {
//    Record_cache.Stop_Update();
    Ranking_list_cache.Stop_Update();
    Database.Close();
  } // destroy()
} // class Initiation
