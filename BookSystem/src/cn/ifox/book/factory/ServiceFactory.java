package cn.ifox.book.factory;

import cn.ifox.book.Service.*;
import cn.ifox.book.Service.Impl.*;

/**
 * Created by 41988 on 2017/7/8.
 */
public class ServiceFactory {
    public static IAdminService getIAdminServiceInstence(){
        return new AdminServiceImpl();
    }
    public static IMemberService getIMemberServiceInstence(){return new MemberServiceImpl();}
    public static IItemService getIItemServiceInstence(){return new ItemServiceImpl();}
    public static IBookService getIBookServiceInstence(){return new BookServiceImpl();}
    public static ILenbookService getILenbookServiceInstence(){return new LenbookServiceImpl();}
}
