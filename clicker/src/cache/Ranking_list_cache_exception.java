package cache;

public class Ranking_list_cache_exception extends Exception {
  private static final long serialVersionUID = 1783633822652302874L;
  
  public Ranking_list_cache_exception( String msg ) {
    super( msg );
  } // Ranking_list_cache_exception()
} // class Ranking_list_cache_exception


class Ranking_list_is_not_sorted_exception extends Ranking_list_cache_exception {
  private static final long serialVersionUID = 4690372497022874576L;

  public Ranking_list_is_not_sorted_exception() {
    super( "Ranking list 還沒排序好!" );
  } // Ranking_list_is_not_sorted_exception()
} // class Ranking_list_is_not_sorted_exception