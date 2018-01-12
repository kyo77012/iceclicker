package database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import service.Exception_utility;

public class Database {
  public static final String database_name = "`ice_clicker`";
  private static final String database_url = "jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf-8";
  private static final String user_name = "clicker_user";
  private static final String pwd = "XfVNmIdodRBJs22O";
  private static final String driver_class_name = "com.mysql.jdbc.Driver";
  private static final PoolProperties poolProperties = new PoolProperties();
  private static DataSource datasource;

  public static void Setup() {
    Database.datasource = new DataSource();
    Database.poolProperties.setUrl( Database.database_url );
    Database.poolProperties.setDriverClassName( Database.driver_class_name );
    Database.poolProperties.setUsername( Database.user_name );
    Database.poolProperties.setPassword( Database.pwd );
    Database.poolProperties.setJmxEnabled( true );
    Database.poolProperties.setTestWhileIdle( false );
    Database.poolProperties.setTestOnBorrow( true );
    Database.poolProperties.setValidationQuery( "SELECT 1" );
    Database.poolProperties.setTestOnReturn( false );
    Database.poolProperties.setValidationInterval( 30000 );
    Database.poolProperties.setTimeBetweenEvictionRunsMillis( 30000 );
    Database.poolProperties.setMaxActive( 100 );
    Database.poolProperties.setInitialSize( 30 );
    Database.poolProperties.setMaxWait( 10000 );
    Database.poolProperties.setRemoveAbandonedTimeout( 60 );
    Database.poolProperties.setMinEvictableIdleTimeMillis( 30000 );
    Database.poolProperties.setMinIdle( 5 );
    Database.poolProperties.setLogAbandoned( true );
    Database.poolProperties.setRemoveAbandoned( true );
    Database.poolProperties.setJdbcInterceptors( "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer" );
    Database.datasource.setPoolProperties( Database.poolProperties );
    
    if ( !AbandonedConnectionCleanupThread.currentThread().isAlive() )
      AbandonedConnectionCleanupThread.currentThread().start();
    System.out.println( "Database setup done." );
  } // Setup()

  public static Connection ConnectDatabase() throws ClassNotFoundException, SQLException {
    return Database.datasource.getConnection();
  } // ConnectDatabase()
  
  public static void Close() {
    try {
      if ( Database.datasource != null )
        Database.datasource.close();
      Database.datasource = null;
      AbandonedConnectionCleanupThread.shutdown();
      System.out.println( "Database close done." );
    } // try
    catch ( InterruptedException interrupted_exception ) {
      Exception_utility.PrintException( interrupted_exception );
    } // catch
  } // Close()
} // class Database()
