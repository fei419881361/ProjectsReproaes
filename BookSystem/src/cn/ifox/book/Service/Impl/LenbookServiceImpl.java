package cn.ifox.book.Service.Impl;

import cn.ifox.book.Service.ILenbookService;
import cn.ifox.book.dbc.DatabaseConnection;
import cn.ifox.book.factory.DAOFactory;
import cn.ifox.book.vo.Lenbook;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/10.
 */
public class LenbookServiceImpl implements ILenbookService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Lenbook vo) throws Exception {
        try{
            return DAOFactory.getILenbookDAOinstance(dbc.getConn()).doCreate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            dbc.Close();
        }

    }

    @Override
    public Map<String, Object> listByMemberAndBooks() throws Exception {
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("allMembers",DAOFactory.getIMemberDAOInstance(dbc.getConn()).finaAll());
            map.put("allBooks",DAOFactory.getIBooksDAOInstance(dbc.getConn()).finaAll());
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.Close();
        }
    }

    @Override
    public Map<String, Object> listBySplit(String colum,String keyword,int currentpage,int linesize) throws Exception {
        Map<String,Object> map = new HashMap<>();
        try{
            map.put("allLenbooks",DAOFactory.getILenbookDAOinstance(dbc.getConn()).findAllBySplit(colum,keyword,currentpage,linesize));
            map.put("allLenbookCounts",DAOFactory.getILenbookDAOinstance(this.dbc.getConn()).getAllCount(colum,keyword));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            dbc.Close();
        }
    }

    @Override
    public boolean updateByRetdate(int leid,int bid) throws SQLException {
        try{
            return DAOFactory.getILenbookDAOinstance(dbc.getConn()).doUpdateByretdate(leid,new Date(),bid);
        }catch (Exception e){
            throw e;
        }finally {
            dbc.Close();
        }
    }


}
