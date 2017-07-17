package cn.ifox.book.Service;

import cn.ifox.book.vo.Lenbook;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/10.
 */
public interface ILenbookService {
    boolean insert(Lenbook vo)throws Exception;
    Map<String,Object> listByMemberAndBooks()throws Exception;
    Map<String,Object> listBySplit(String colum,String keyword,int currentpage,int linesize)throws Exception;
    boolean updateByRetdate(int leid,int bid)throws Exception;

}
