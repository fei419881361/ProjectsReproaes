package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/5/20.
 */
public class DatabaseConnection {
    private static String URL = "jdbc:mysql://localhost:3306/t1";
    private static String user = "root";
    private static String password = "748596";
    private static Connection connection = null;
    private static String tabname = "MyBook";

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
    public static void CloseConnection(){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
