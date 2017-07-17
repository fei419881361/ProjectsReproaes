package cn.ifox.book.Service.Impl;

import cn.ifox.book.Service.IMemberService;
import cn.ifox.book.dbc.DatabaseConnection;
import cn.ifox.book.factory.DAOFactory;
import cn.ifox.book.vo.Member;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 41988 on 2017/7/8.
 */
public class MemberServiceImpl implements IMemberService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(Member vo) throws Exception {
        //判定当前的mid为空则插入数据
        try {
            if(DAOFactory.getIMemberDAOInstance(this.dbc.getConn()).findById(vo.getMid())==null){
                return DAOFactory.getIMemberDAOInstance(this.dbc.getConn()).doCreate(vo);
            }
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.Close();
        }
        return false;
    }

    @Override
    public Map<String, Object> listBySplit(String colum, String keyword, int currentPage, int lineSize) throws Exception {
        try{
            Map<String,Object> map =new HashMap<>();
            map.put("allMembers",DAOFactory.getIMemberDAOInstance(dbc.getConn()).findAllBySplit(colum,keyword,currentPage,lineSize));
            map.put("allCounts",DAOFactory.getIMemberDAOInstance(dbc.getConn()).getAllCount(colum,keyword));
            return map;
        }catch (Exception e){throw e;}
        finally {
            dbc.Close();
        }
    }
}
