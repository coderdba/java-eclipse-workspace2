package gm.mysql.dh;

import java.sql.Connection;
import java.sql.ResultSet;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	System.out.println( "Hello MySQL!" );
    	
    	/// EXISTING DB ///
        //MySqlDatabase db = new MySqlDatabase ("localhost", "13306", "hr", "root", "root123_ROOT123");
    	MySqlDatabase db = new MySqlDatabase ("localhost", "13306", "hr", "hruserr", "hr123_HR123");
        
        // get connection
        Connection connection = db.openConnection();
        System.out.println ("Connection is closed? = " + connection.isClosed());
        System.out.println ("Connection schema= " + connection.getSchema());
        System.out.println ("Connection auto-commit property = " + connection.getAutoCommit());
        
        // do something
        //String sql = "select host,user from mysql.user where user='root'";
        //String sql="show tables";
        String sql="select id, name from dept order by 1";
        
        MySqlDataHandler dh = db.getDataHandler();
        ResultSet        rs;
        
        System.out.println ("Running sql: " + sql);
        rs = dh.runSqlQuery(sql);
        
        System.out.println ("ResultSet is before-first?: " + rs.isBeforeFirst());
        System.out.println ("ResultSet output: ");
        dh.printResultSetRows(rs);
        
        System.out.println ("\nResultSet output tabular: ");
        dh.printResultSetRowsTabular(rs);
        
        // close connection
        System.out.println ("Closing the connection now");
        connection.close();
        System.out.println ("Connection is closed? = " + connection.isClosed());
        
        /// NEW DB ///
        System.out.println ("\n\nCreating new database\n");
        MySqlDatabase newDB = new MySqlDatabase("localhost", "13306", "sales", "salesuser", "mys123_MYS123");
        newDB.createDb("root", "root123_ROOT123");
        System.out.println ("Created new database\n");
  
    }
}
