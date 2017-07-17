package DAO;

import domain.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 41988 on 2017/5/20.
 */
public class ImpBookDAO implements BookDAO{
    Connection connection;
    Statement statement =null;
    public ImpBookDAO(Connection connection){
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Book> queryAll() {
        List<Book> books = new ArrayList<>();
        String sql ="SELECT * FROM MyBook";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Book book = new Book();
                book.setId(Integer.parseInt(resultSet.getString("id")));
                book.setBook_name(resultSet.getString("book_name"));
                book.setNow_user(resultSet.getString("now_user"));
                book.setRecent_user(resultSet.getString("recent_user"));
                book.setIsing(Integer.parseInt(resultSet.getString("ising")));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 返回书可借阅状态
     * */
    @Override
    public boolean BookCanBorrow(String Condition, String col_name) {
        try {
            String sqldata = "SELECT XX FROM MyBook "+Condition;
            sqldata = sqldata.replace("XX", col_name);
            System.out.println("测试打印sql语句"+sqldata); //测试打印sql语句
            ResultSet resultSet = (ResultSet) statement.executeQuery(sqldata);
            String result = "0";
            while(resultSet.next()){
                result = resultSet.getString(col_name);
                System.out.println("当前书ising:"+result);
            }
            if("0".equals(result)){
                return true;//可借阅状态
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void ChangeDBdata(String Condition, String UpdateValue, String col_name) {
        try {
            String sqldata = "UPDATE MyBook SET col_name = 'changename' WHERE "+Condition;
            sqldata = sqldata.replace("col_name",col_name ).replace("changename", UpdateValue);
            System.out.println("测试打印sql语句"+sqldata);//测试打印sql语句
            int i = statement.executeUpdate(sqldata);
            System.out.println("i = "+i);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookByName(String BookName) {
        Book book = new Book();
        String sql = "SELECT * FROM MyBook WHERE book_name="+"'"+BookName+"';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            book.setIsing(Integer.parseInt(resultSet.getString("ising")));
            book.setId(Integer.parseInt(resultSet.getString("id")));
            book.setNow_user(resultSet.getString("now_user"));
            book.setRecent_user(resultSet.getString("recent_user"));
            book.setBook_name(BookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


}
