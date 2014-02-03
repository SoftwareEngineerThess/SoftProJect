import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;


public class ResultSetTableModel extends AbstractTableModel 
{
   private Connection connection;
   private Statement statement;
   private ResultSet resultSet;
   private ResultSetMetaData metaData;
   private int numberOfRows;

   // Mia flag pou krataei tin database diasindesi
   private boolean connectedToDatabase = false;
   
   //O kataskeuastis me tis parametrous pou apaitountai gia tin diasindesi
   public ResultSetTableModel( String driver, String url, 
      String username, String password, String query ) 
      throws SQLException, ClassNotFoundException
   {         
      //fortwma tou driver
      Class.forName( driver );

      //sindesi me tin database
      connection = DriverManager.getConnection( url, username, password );

      //Dimiourgia Statement gia to query database
      statement = connection.createStatement( 
         ResultSet.TYPE_SCROLL_INSENSITIVE,
         ResultSet.CONCUR_READ_ONLY );

      // update tis database sindesis
      connectedToDatabase = true;

      // set tis query
      setQuery( query );
   }

   
   //H methodos antiproswpeuei ton tupo tis stilis toy Table
   public Class getColumnClass( int column ) throws IllegalStateException
   {
      if ( !connectedToDatabase ) 
         throw new IllegalStateException( "Not Connected to Database" );

      try 
      {
         String className = metaData.getColumnClassName( column + 1 );
                  
         return Class.forName( className );
      }
      catch ( Exception exception ) 
      {
         exception.printStackTrace();
      }
      
      return Object.class;
      }

   // H methodos pernei ton arithmo ton stilwn toy Table
   public int getColumnCount() throws IllegalStateException
   {   
      if ( !connectedToDatabase ) 
         throw new IllegalStateException( "Not Connected to Database" );

      try 
      {
         return metaData.getColumnCount(); 
      }
      catch ( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
      }
      
      return 0;
   }

   
   
   //H methodos pairnei to onoma tis sigkekrimenis stilis tou Table
   public String getColumnName( int column ) throws IllegalStateException
   {    
      if ( !connectedToDatabase ) 
         throw new IllegalStateException( "Not Connected to Database" );

      try 
      {
         return metaData.getColumnName( column + 1 );  
      }
      catch ( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
      }
      
      return "";
   }

   
   //h methodos epistrefei ton arithmo twn grammwn ston ResultSet
   public int getRowCount() throws IllegalStateException
   {  
      if ( !connectedToDatabase ) 
         throw new IllegalStateException( "Not Connected to Database" );
 
      return numberOfRows;
   }

   
   //H methodos pairnei tin timi pou vrisketai stin antistoixi grammi kai stili
   public Object getValueAt( int row, int column ) 
      throws IllegalStateException
   {
      if ( !connectedToDatabase ) 
         throw new IllegalStateException( "Not Connected to Database" );
      try 
      {
         resultSet.absolute( row + 1 );
         return resultSet.getObject( column + 1 );
      }
      catch ( SQLException sqlException ) 
      {
         sqlException.printStackTrace();
      }
      
      return "";
   }
   
   
   //H methodos thetei mia new database simvoloseiras erwtimatos
   public void setQuery( String query ) 
      throws SQLException, IllegalStateException 
   {
      if ( !connectedToDatabase ) 
         throw new IllegalStateException( "Not Connected to Database" );

      resultSet = statement.executeQuery( query );

      metaData = resultSet.getMetaData();

      resultSet.last();                  
      numberOfRows = resultSet.getRow(); 
      
      fireTableStructureChanged();
   }

   
   //H methodos oloklirwnei tin to statement kai tin sindesi me tin database             
   public void disconnectFromDatabase()            
   {              
      if ( !connectedToDatabase )                  
         return;
           
      try                                          
      {                                            
         statement.close();                        
         connection.close();                       
      }                                 
      catch ( SQLException sqlException )          
      {                                            
         sqlException.printStackTrace();           
      }                            
      finally
      {                                            
         connectedToDatabase = false;              
      }                         
   }}