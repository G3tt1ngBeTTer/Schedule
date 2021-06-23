package Database;

import static MainLobby.MainForm_v2.er;
import java.sql.*;

/**
 *
 * @author White Fenics
 */
public class MyDBConnection {
    public Connection myConnection;
    /** Creates a new instance of MyDBConnection */
    public MyDBConnection() {
    }
    public void init(){
       try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        myConnection=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schedule_death_db?"
                        + "characterEncoding=utf8&zeroDateTimeBehavior="
                        + "convertToNull&serverTimezone=UTC","root","" 
            );
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
           e.printStackTrace(er);
           
        }
    }
    public Connection getMyConnection(){
        return myConnection;
    }
    public void close(ResultSet rs){
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        }
    }
     public void close(java.sql.Statement stmt){
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}   
        }
    }
  public void destroy(){
    if(myConnection !=null){
         try{
               myConnection.close();
            }
            catch(Exception e){}     
    }
  }   
}
