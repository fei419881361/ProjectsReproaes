package cn.ifox.book.Service.Impl;

import cn.ifox.book.Service.IAdminService;
import cn.ifox.book.dbc.DatabaseConnection;
import cn.ifox.book.factory.DAOFactory;
import cn.ifox.book.vo.Admin;

/**
 * Created by 41988 on 2017/7/8.
 */
public class AdminServiceImpl implements IAdminService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean login(Admin vo) throws Exception {
        try{
            if(DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).findLogin(vo)){
                return DAOFactory.getIAdminDAOInstance(this.dbc.getConn()).doUPdateByLastDate(vo.getAid());
            }
            return false;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.Close();
        }
    }


}
