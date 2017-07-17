package cn.ifox.book.dao;

import cn.ifox.book.vo.Lenbook;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by 41988 on 2017/7/10.
 */
public interface ILenbookDAO extends IDAO<Integer,Lenbook>{
    boolean doUpdateByretdate(Integer leid, Date retdate,Integer bid)throws SQLException;

}
