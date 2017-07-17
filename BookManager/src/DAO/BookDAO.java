package DAO;

import domain.Book;

import java.util.List;

/**
 * Created by 41988 on 2017/5/20.
 */
public interface BookDAO {
    List<Book> queryAll();
    boolean BookCanBorrow(String Condition,String col_name);//Condition:查询的条件语句，col_name:数据库字段名
    void ChangeDBdata(String Condition,String UpdateValue,String col_name);
    Book getBookByName(String BookName);

}
