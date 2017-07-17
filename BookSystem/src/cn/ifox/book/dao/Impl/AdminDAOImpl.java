package cn.ifox.book.dao.Impl;

import cn.ifox.book.dao.AbstractDAOImpl;
import cn.ifox.book.dao.IAdminDAO;
import cn.ifox.book.vo.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by 41988 on 2017/7/8.
 */
public class AdminDAOImpl extends AbstractDAOImpl implements IAdminDAO {
    public AdminDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean findLogin(Admin vo) throws SQLException {
        boolean flag = false;
        String sql = "SELECT lastdate,flag FROM admin WHERE aid=? AND password=? AND status=1";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getAid());
        super.pstmt.setString(2,vo.getPassword());
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()){
            flag = true;
            vo.setLastdate(rs.getString(1));
            vo.setFlag(rs.getInt(2));
        }
        return flag;
    }

    @Override
    public boolean doUPdateByLastDate(String aid) throws Exception {
        String sql = "UPDATE admin SET lastdate=? WHERE aid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        // 登录成功后直接使用当前日期为最后一次登录日期
        super.pstmt.setString(1, String.valueOf(new Timestamp(new Date().getTime())));
        super.pstmt.setString(2, aid);
        System.out.println(new Timestamp(new Date().getTime()));
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doCreate(Admin vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Admin vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Admin findById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Admin> finaAll() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT aid,password,lastdate,flag,status FROM admin";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Admin vo = new Admin();
            vo.setAid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setLastdate(rs.getString(3));
            vo.setFlag(rs.getInt(4));
            vo.setStatus(rs.getInt(5));
            admins.add(vo);
        }
        return admins;
    }

    @Override
    public List<Admin> findAllBySplit(String colum, String keyWord, Integer curentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String colum, String keyWord) throws SQLException {
        return null;
    }
}
