package cn.ifox.book.Service.Impl;

import cn.ifox.book.Service.IItemService;
import cn.ifox.book.dbc.DatabaseConnection;
import cn.ifox.book.factory.DAOFactory;
import cn.ifox.book.vo.Item;

import java.util.List;

/**
 * Created by 41988 on 2017/7/9.
 */
public class ItemServiceImpl implements IItemService {
        private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Item vo) throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(this.dbc.getConn()).doCreate(vo);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.Close();
        }
    }

    @Override
    public List<Item> list() throws Exception {
        try {
            return DAOFactory.getIItemDAOInstance(dbc.getConn()).finaAll();
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.Close();
        }
    }
}
