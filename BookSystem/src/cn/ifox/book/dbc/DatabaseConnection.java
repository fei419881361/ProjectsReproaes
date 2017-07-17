package cn.ifox.book.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 41988 on 2017/7/8.
 */
public class DatabaseConnection {
    private static final String DBDriver = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://115.29.45.115/booksystem";
    private static final String DBUSER = "ifox";
    private static final String DBPASS = "ifoxstudio411";

    private Connection conn;

    public DatabaseConnection(){
        try{
            Class.forName(DBDriver);
            this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn(){
        return this.conn;
    }
    public void Close() {
        if (this.conn != null){
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
