package cn.ifox.book.Service.Impl;

import cn.ifox.book.Service.IBookService;
import cn.ifox.book.dbc.DatabaseConnection;
import cn.ifox.book.factory.DAOFactory;
import cn.ifox.book.vo.Books;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/10.
 */
public class BookServiceImpl implements IBookService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Books vo) throws Exception {
     try{
         return DAOFactory.getIBooksDAOInstance(dbc.getConn()).doCreate(vo);
     }catch(Exception e){
         throw e;
     }finally {
        dbc.Close();
     }

    }

    @Override
    public Map<String, Object> findByAdminAndItem() throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allAdmins",DAOFactory.getIAdminDAOInstance(dbc.getConn()).finaAll());
            map.put("allItems",DAOFactory.getIItemDAOInstance(dbc.getConn()).finaAll());
            return map;
        }catch(Exception e){
            throw e;
        }finally {
            dbc.Close();
        }
    }

    @Override
    public Map<String, Object> listBySplit(String colum, String keyword, int currentPage, int lineSize) throws Exception {
        try{
            Map<String,Object> map =new HashMap<>();
            map.put("allBooks",DAOFactory.getIBooksDAOInstance(dbc.getConn()).findAllBySplit(colum,keyword,currentPage,lineSize));
            map.put("allCounts",DAOFactory.getIBooksDAOInstance(dbc.getConn()).getAllCount(colum,keyword));
            return map;
        }catch (Exception e){throw e;}
        finally {
            dbc.Close();
        }
    }


}
