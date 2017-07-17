package cn.ifox.book.factory;

import cn.ifox.book.dao.*;
import cn.ifox.book.dao.Impl.*;

import java.sql.Connection;

/**
 * Created by 41988 on 2017/7/8.
 */
public class DAOFactory {
    public static IAdminDAO getIAdminDAOInstance(Connection conn){
        return new AdminDAOImpl(conn);
    }
    public static IMemberDAO getIMemberDAOInstance(Connection conn){return new MemberDAOImpl(conn);}
    public static IItemDAO getIItemDAOInstance(Connection conn){return new ItemDAOImpl(conn);}
    public static IBooksDAO getIBooksDAOInstance(Connection conn){return new BooksDAOImpl(conn);}
    public static ILenbookDAO getILenbookDAOinstance(Connection conn){return new LenbookDAOImpl(conn);}
}
