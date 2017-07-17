package cn.ifox.book.Service;

import cn.ifox.book.vo.Member;

import java.util.Map;

/**
 * Created by 41988 on 2017/7/8.
 */
public interface IMemberService {
    /**
     * 实现数据增加的操作
     * */
    boolean insert(Member vo)throws Exception;
    Map<String,Object> listBySplit(String colum, String keyword, int currentPage, int lineSize)throws Exception;
}
