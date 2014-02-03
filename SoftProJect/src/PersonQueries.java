import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonQueries 
{
   private static final String URL = "jdbc:mysql://localhost/address";
   private static final String USERNAME = "stathis";
   private static final String PASSWORD = "password";
		
   //Epitinxanetai h diaxeirisi twn sindesewn
   private Connection connection = null;
   //Dimiourgia ouras gia ta epilegomena stoixeia tou vivliou dieuthinsewn
   private PreparedStatement selectAllPeople = null; 
   //Dimiourgia ouras gia ta epilegomena stoixeia me vasi to epitheto
   private PreparedStatement selectPeopleByLastName = null; 
   //Dimiourgia insert gia nees kataxwriseis sth vasi dedomenwn
   private PreparedStatement insertNewPerson = null; 
    
   
   //Dimiourgia tou Kataskeuasti PersonQueries
   public PersonQueries()
   {
      try 
      {
         connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );

         //Dimiourgia ouras gia ta epilegomena stoixeia tou vivliou dieuthinsewn
         selectAllPeople = connection.prepareStatement( "SELECT * FROM Addresses" );
         
       //Dimiourgia ouras gia ta epilegomena stoixeia me vasi to epitheto
         selectPeopleByLastName = connection.prepareStatement
        		 ("SELECT * FROM Addresses WHERE LastName = ?" );
         
       //Dimiourgia insert gia nees kataxwriseis sth vasi dedomenwn
         insertNewPerson = connection.prepareStatement
        		 ("INSERT INTO Addresses " + 
            "( FirstName, LastName, Email, PhoneNumber ) " + 
            "VALUES ( ?, ?, ?, ? )" );
      }
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      }
   }
   
   //Epilogi twn dieuthinsewn apo tin database
   public List< Person > getAllPeople()
   {
      List< Person > results = null;
      ResultSet resultSet = null;
      
      try 
      {
         resultSet = selectAllPeople.executeQuery(); 
         results = new ArrayList< Person >();
         
         while ( resultSet.next() )
         {
            results.add( new Person(
               resultSet.getInt( "addressID" ),
               resultSet.getString( "firstName" ),
               resultSet.getString( "lastName" ),
               resultSet.getString( "email" ),
               resultSet.getString( "phoneNumber" ) ) );
         }
      }
      
      
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();         
      } 
      
            
      finally
      {
         try 
         {
            resultSet.close();
         }
         
         
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         } 
         
      } 
      
      return results;
   } 
   
   //Epilogi Endiaferomenou me vasi to epitheto
   public List< Person > getPeopleByLastName( String name )
   {
      List< Person > results = null;
      ResultSet resultSet = null;

      try 
      {
         selectPeopleByLastName.setString( 1, name );
         
         // H executeQuery epistrefei ta dedomena tis ResultSet poy exoun eisaxtei
         resultSet = selectPeopleByLastName.executeQuery(); 

         results = new ArrayList< Person >();

         while ( resultSet.next() )
         {
            results.add( new Person(
               resultSet.getInt( "addressID" ),
               resultSet.getString( "firstName" ),
               resultSet.getString( "lastName" ),
               resultSet.getString( "email" ),
               resultSet.getString( "phoneNumber" ) ) );
         }
      }
      
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      }
      
      finally
      {
         try 
         {
            resultSet.close();
         }
         
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();         
            close();
         }
         
      } 
      
      return results;
   } 
   
   //Prosthesi endiaferomenou sti vasi dedomenwn
   public int addPerson( 
      String fname, String lname, String email, String num )
   {
      int result = 0;
      
      //Eisagwgi twn (4) parametrwn pou exoun dilwthei apo tin klasi tis Person 
      try 
      {
         insertNewPerson.setString( 1, fname );
         insertNewPerson.setString( 2, lname );
         insertNewPerson.setString( 3, email );
         insertNewPerson.setString( 4, num );

         
         result = insertNewPerson.executeUpdate(); 
         
      } 
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      }
      
      return result;
   }
   
   //Oloklirwsi tis sindesis
   public void close()
   {
      try 
      {
         connection.close();
         
      }
      
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      }
   }
}