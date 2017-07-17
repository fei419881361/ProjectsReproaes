package cn.ifox.book.Service;

import cn.ifox.book.vo.Admin;

/**
 * Created by 41988 on 2017/7/8.
 */
public interface IAdminService {
    /**
     * 实现管理员登陆检查操作
     * @return 将最后一次登陆日期传递到前台页面 否则返回false
     * */
    boolean login(Admin vo) throws Exception;
}
