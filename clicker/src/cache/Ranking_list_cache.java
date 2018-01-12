package cache;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import data_struct.Group;
import data_struct.Group_rank;
import data_struct.Question;
import data_struct.Record;
import data_struct.Student_for_other;
import service.CurrentQuestion;
import service.Exception_utility;

public class Ranking_list_cache {
  // 小組排名
  public static Vector<Group> ranking_list;
  public static Vector<Group_rank> ranking_list_group_rank;
  public static ArrayList<Group_rank> top_three_group;
  public static Hashtable<Integer, Group> ranking_list_table;
  public static Hashtable<Integer, Group_rank> ranking_list_group_rank_table;
  /*
  public static String top_three_json;
  private static Gson gson;
  */
  private static UpdateRanking_list_thread updateRank_list_thread;
  
  static {
    Ranking_list_cache.ranking_list = new Vector<Group>();
    Ranking_list_cache.ranking_list_group_rank = new Vector<Group_rank>();
    Ranking_list_cache.top_three_group = new ArrayList<Group_rank>();
    Ranking_list_cache.ranking_list_table = new Hashtable<Integer, Group>();
    Ranking_list_cache.ranking_list_group_rank_table = new Hashtable<Integer, Group_rank>();
    /*
    Ranking_list_cache.gson = new Gson();
    */
  } // static
  
  public static void Load() {
    Ranking_list_cache.ranking_list.removeAllElements();
    Ranking_list_cache.ranking_list_group_rank.removeAllElements();
    Ranking_list_cache.top_three_group.clear();
    Ranking_list_cache.ranking_list_table.clear();
    Ranking_list_cache.ranking_list_group_rank_table.clear();
    
    try {
      Group_rank group_rank;
      for ( Group group : Group_cache.groups ) {
        Ranking_list_cache.ranking_list.add( group );
        group_rank = new Group_rank( group );
        Ranking_list_cache.ranking_list_group_rank.add( group_rank );
        Ranking_list_cache.ranking_list_table.put( group.number, group );
        Ranking_list_cache.ranking_list_group_rank_table.put( group.number, group_rank );
      } // for

      Ranking_list_cache.Update();
      System.out.println( "Ranking_list_cache loaded." );
    } // try
    catch ( Exception exception ) {
      Exception_utility.PrintException( exception );
    } // catch
  } // Load()
  
  private static void Caculate_score() {
    float group_total_score;
    int attendance;
    Group_rank group_rank;
    for ( Group group : Group_cache.groups ) {
      group_total_score = 0;
      attendance = 0;
      for ( Student_for_other student : group.members ) {
        student.score = 0;
        if ( CurrentQuestion.todayQuestions != null ) {
          for ( Question question : CurrentQuestion.todayQuestions.questions ) {
            if ( question.done ) {
              Hashtable<Integer, Record> question_record_table;
              Record record;
              if ( ( question_record_table = Record_cache.student_record_table.get( student.id ) ) != null &&
                   ( record = question_record_table.get( question.id ) ) != null ) {
                student.isAttended = true;
                if ( record.option_id == question.ans_option_id ) {
                  student.score++;
                  group_total_score++;
                } // if
              } // if
            } // if
          } // for
        } // if
        
        if ( student.isAttended ) attendance++;
      } // for
      
      if ( attendance != 0 ) group.score = group_total_score / attendance;
      else group.score = 0;
      group.attendance = attendance;
      
      group_rank = Ranking_list_cache.ranking_list_group_rank_table.get( group.number );
      group_rank.score = group.score;
    } // for
  } // Caculate_score()
  
  private static void Sort() {
    int i, j;
    boolean sorted = false;
    int left_group_number = Ranking_list_cache.ranking_list.size();
    for ( i = 1 ; !sorted ; left_group_number--, i = 1 ) {
      sorted = true;
      for ( j = i - 1 ; i < left_group_number ; i++, j++ ) {
        Group group_i = Ranking_list_cache.ranking_list.get( i );
        Group group_j = Ranking_list_cache.ranking_list.get( j );
        Group_rank group_rank_i = Ranking_list_cache.ranking_list_group_rank.get( i );
        Group_rank group_rank_j = Ranking_list_cache.ranking_list_group_rank.get( j );
        if ( ( group_i.score > group_j.score ) ||
             ( group_i.score == group_j.score && group_i.number < group_j.number ) ) {
          Ranking_list_cache.ranking_list.set( i, group_j );
          Ranking_list_cache.ranking_list.set( j, group_i );

          Ranking_list_cache.ranking_list_group_rank.set( i, group_rank_j );
          Ranking_list_cache.ranking_list_group_rank.set( j, group_rank_i );
          
          sorted = false;
        } // if
      } // for
    } // for
  } // Sort()
  
  private static void Pick_top_three_group() throws Ranking_list_is_not_sorted_exception {
    Ranking_list_cache.top_three_group.clear();
    
    float score = -1;
    int rank = 1;
    Group group;
    for ( Group_rank group_rank : Ranking_list_cache.ranking_list_group_rank ) {
      if ( score == -1 ) {
        score = group_rank.score;
        group_rank.rank = rank;
      } // if
      else if ( score == group_rank.score ) {
        group_rank.rank = rank;
      } // else if
      else if ( score > group_rank.score ) {
        rank++;
        score = group_rank.score;
        group_rank.rank = rank;
      } // else if
      else {
        throw new Ranking_list_is_not_sorted_exception();
      } // else
      
      group = Ranking_list_cache.ranking_list_table.get( group_rank.number );
      group.score = group_rank.score;
      group.rank = group_rank.rank;
    } // for
    
    for ( Group_rank group_rank : Ranking_list_cache.ranking_list_group_rank ) {
      if ( group_rank.rank <= 3 ) {
        Ranking_list_cache.top_three_group.add( group_rank );
      } // if
    } // for
    
    /*
    Ranking_list_cache.top_three_json = Ranking_list_cache.gson.toJson( top_three );
    */
  } // Pick_top_three_group()
  
  public static void Update() throws Ranking_list_is_not_sorted_exception {
    Ranking_list_cache.Caculate_score();
    Ranking_list_cache.Sort();
    Ranking_list_cache.Pick_top_three_group();
  } // Update()
  
  public static void Start_Update() {
    if ( Ranking_list_cache.updateRank_list_thread == null )
      Ranking_list_cache.updateRank_list_thread = new UpdateRanking_list_thread();
    Ranking_list_cache.updateRank_list_thread.Start_Update();
  } // Start_Update()
  
  public static void Stop_Update() {
    try {
      if ( Ranking_list_cache.updateRank_list_thread != null ) {
        Ranking_list_cache.updateRank_list_thread.Stop_Update();
        Ranking_list_cache.updateRank_list_thread.join();
        Ranking_list_cache.updateRank_list_thread = null;
      } // if
    } // try
    catch ( InterruptedException interrupted_exception ) {
      Exception_utility.PrintException( interrupted_exception );
    } // catch
  } // Stop_Update()
} // class Ranking_list_cache



class UpdateRanking_list_thread extends Thread {
  private boolean stop;

  public void run() {
    System.out.println( "Ranking_list_cache start updating." );
    try {
      while ( !stop ) {
        synchronized ( Ranking_list_cache.ranking_list ) {
          synchronized ( Ranking_list_cache.ranking_list_group_rank ) {
            Ranking_list_cache.Update();
          } // synchronized
        } // synchronized
        System.out.println( "Ranking_list_cache updated." );
        try {
          UpdateRanking_list_thread.yield();
          UpdateRanking_list_thread.sleep( 30000 );
        } // try
        catch ( InterruptedException interrupted_exception ) {
          synchronized ( Ranking_list_cache.ranking_list ) {
            synchronized ( Ranking_list_cache.ranking_list_group_rank ) {
              Ranking_list_cache.Update();
            } // synchronized
          } // synchronized
          continue;
        } // catch
      } // while
    } // try
    catch ( Ranking_list_cache_exception rank_list_cache_exception ) {
      Exception_utility.PrintException( rank_list_cache_exception );
    } // catch
    finally {
      System.out.println( "Ranking_list_cache stop updating." );
    } // finally
  } // run()
  
  public void Start_Update() {
    if ( !this.isAlive() ) {
      this.stop = false;
      this.start();
    } // if
  } // Start_Update()
  
  public void Stop_Update() {
    if ( this.isAlive() ) {
      this.stop = true;
      this.interrupt();
    } // if
  } // Stop_Update()
} // UpdateRanking_list_thread()