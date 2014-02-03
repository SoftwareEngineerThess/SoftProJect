import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import com.sun.rowset.JdbcRowSetImpl;

public class JdbcRowSetTest 
{                            
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";        
   static final String DATABASE_URL = "jdbc:mysql://localhost/books";
   static final String USERNAME = "jhtp6";
   static final String PASSWORD = "jhtp6";
   
   public JdbcRowSetTest() 
   {
      
      try 
      {
         Class.forName( JDBC_DRIVER ); 

         
         JdbcRowSet rowSet = new JdbcRowSetImpl();  
         rowSet.setUrl( DATABASE_URL ); 
         rowSet.setUsername( USERNAME ); 
         rowSet.setPassword( PASSWORD ); 
         rowSet.setCommand( "SELECT * FROM authors" );
         rowSet.execute(); 

        
         ResultSetMetaData metaData = rowSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
         System.out.println( "Authors Table of Books Database:" );

         
         for ( int i = 1; i <= numberOfColumns; i++ )
            System.out.printf( "%-8s\t", metaData.getColumnName( i ) );
         System.out.println();
         
      
         while ( rowSet.next() ) 
         {
            for ( int i = 1; i <= numberOfColumns; i++ )
               System.out.printf( "%-8s\t", rowSet.getObject( i ) );
            System.out.println();
         }
      } 
      catch ( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } 
      catch ( ClassNotFoundException classNotFound ) 
      {
         classNotFound.printStackTrace();            
         System.exit( 1 );
      }                          
   } 
   
 
   public static void main( String args[] )
   {
      JdbcRowSetTest window = new JdbcRowSetTest();      
   } 
} 