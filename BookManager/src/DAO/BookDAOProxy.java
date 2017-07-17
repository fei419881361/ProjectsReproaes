package DAO;

import domain.Book;

import java.util.List;

/**
 * Created by 41988 on 2017/5/20.
 */
public class BookDAOProxy {
    public List<Book> getAllBook(){
        ImpBookDAO dao = new ImpBookDAO(DatabaseConnection.getConnection());
        List<Book> books = dao.queryAll();
       // DatabaseConnection.CloseConnection();
        return books;
    }

    public void UpDateBookInfo(String FromName,String bookname){
        String Condition = "book_name = 'xxx';".replace("xxx", bookname);
        ImpBookDAO dao = new ImpBookDAO(DatabaseConnection.getConnection());
        dao.ChangeDBdata(Condition,"1", "ising");
        dao.ChangeDBdata(Condition,FromName, "now_user");
       // DatabaseConnection.CloseConnection();
    }
    public Book getBookbyName(String bookname){
        ImpBookDAO dao = new ImpBookDAO(DatabaseConnection.getConnection());
        Book book = dao.getBookByName(bookname);
       // DatabaseConnection.CloseConnection();
        return book;
    }
    //name :还书人的名字
    public boolean ReturnBook(Book book,String name){
        String book_now_user = book.getNow_user();
        ImpBookDAO dao = new ImpBookDAO(DatabaseConnection.getConnection());
        String condition = "book_name = 'xxx';".replace("xxx", book.getBook_name());
        if(name.equals(book_now_user)){
            //还书人的名字，和当前被借书的借书人的名字相同。
            //执行还书逻辑
            dao.ChangeDBdata(condition,name, "recent_user");
            dao.ChangeDBdata(condition,"2", "ising");
            dao.ChangeDBdata(condition,"null", "now_user");
            return true;
        }else
            return false;
    }
    public void ManagerConfirm(Book book){
        ImpBookDAO dao = new ImpBookDAO(DatabaseConnection.getConnection());
        String condition = "book_name = 'xxx';".replace("xxx", book.getBook_name());
        dao.ChangeDBdata(condition,"0", "ising");
    }
}
