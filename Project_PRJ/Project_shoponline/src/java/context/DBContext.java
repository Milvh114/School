
package context;

import dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {

//    public DBContext()
//    {
//        try {
//            // Edit URL , username, password to authenticate with your MS SQL Server
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=Demo_ShopOnline";
//            String username = "sa";
//            String password = "Minh@123";
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(ex);
//        }
//    }
    
    
//    public Connection makeConnection() throws ClassNotFoundException, SQLException{
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url="jdbc:sqlserver:"
//                + "//localhost:1433"
//                + ";databaseName=Demo_ShopOnline";
//        //3. Open Connection
//        Connection con=DriverManager.getConnection(url,"sa","Minh@123");
//        return con;
//    }
    protected Connection con;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Demo_ShopOnline";
            String username = "sa";
            String password = "Minh123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, username, password);
            //System.out.println("----");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}