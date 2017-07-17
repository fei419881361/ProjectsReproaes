package cn.ifox.book.dao;

import cn.ifox.book.vo.Admin;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by 41988 on 2017/7/8.
 */
public interface IAdminDAO extends IDAO<String,Admin> {
    /**
     * 实现用户登录检查
     * @param vo 检查对象（aid password flag）
     * */
    boolean findLogin(Admin vo)throws SQLException;

    /**
     * 登录日期的上传
     * */
    boolean doUPdateByLastDate(String aid)throws Exception;
}
