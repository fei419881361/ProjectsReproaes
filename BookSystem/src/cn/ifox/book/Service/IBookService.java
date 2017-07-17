package cn.ifox.book.Service;

import cn.ifox.book.vo.Books;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/10.
 */
public interface IBookService {
    boolean insert(Books vo)throws Exception;

    /**
     * 执行数据的全部列出操作
     * */
    Map<String,Object> findByAdminAndItem()throws Exception;
    Map<String,Object> listBySplit(String colum,String keyword,int currentPage,int lineSize)throws Exception;
}
