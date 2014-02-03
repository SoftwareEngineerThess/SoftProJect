import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

 
public class DisplayAuthors 
{                        
   static final String JDBC_DRIVER = "";        
   static final String DATABASE_URL = "";
   
   
   public static void main( String args[] )
   {
      Connection connection = null;
      Statement statement = null; 
      try 
      {
         Class.forName( JDBC_DRIVER ); 

         
         connection = 
            DriverManager.getConnection( DATABASE_URL, "jhtp6", "jhtp6" );

         
         statement = connection.createStatement();
         
        
         ResultSet resultSet = statement.executeQuery( 
            "SELECT authorID, firstName, lastName FROM authors" );
         
         
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
         System.out.println( "Authors Table of Books Database:" );

         for ( int i = 1; i <= numberOfColumns; i++ )
            System.out.printf( "%-8s\t", metaData.getColumnName( i ) );
         System.out.println();
         
         while ( resultSet.next() ) 
         {
            for ( int i = 1; i <= numberOfColumns; i++ )
               System.out.printf( "%-8s\t", resultSet.getObject( i ) );
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
      finally 
      {                                                             
         try                                                        
         {                                                          
            statement.close();                                      
            connection.close();                                     
         }                                              
         catch ( Exception exception )                        
         {                                                          
            exception.printStackTrace();                                     
            System.exit( 1 );                                       
         }                                             
      }    
      
      
   }
}