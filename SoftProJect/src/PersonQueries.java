import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonQueries 
{
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