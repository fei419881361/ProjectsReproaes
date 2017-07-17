package cn.ifox.book.Service;

import cn.ifox.book.vo.Item;

import java.util.List;

/**
 * Created by 41988 on 2017/7/9.
 */
public interface IItemService {
    boolean insert(Item vo)throws Exception;
    List<Item> list()throws Exception;
}
