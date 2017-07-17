package cn.ifox.book.dao.Impl;

import cn.ifox.book.dao.AbstractDAOImpl;
import cn.ifox.book.dao.IItemDAO;
import cn.ifox.book.vo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 41988 on 2017/7/9.
 */
public class ItemDAOImpl extends AbstractDAOImpl implements IItemDAO {
    public ItemDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Item vo) throws SQLException {
        String sql = "INSERT INTO item(name,note) VALUES(?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getName());
        super.pstmt.setString(2, vo.getNote());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Item vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Item findById(Integer id) throws SQLException {
        Item vo = null;
        String sql = "SELECT iid ,name,note,FROM item WHERE iid=?";
        super.pstmt.setInt(1,id);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()){
            vo = new Item();
            vo.setIid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setNote(rs.getString(3));
        }
        return vo;
    }

    @Override
    public List<Item> finaAll() throws SQLException {
        List<Item> all = new ArrayList<>();
        String sql = "SELECT iid,name,note FROM item";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Item vo = new Item();
            vo.setIid(rs.getInt(1));
            vo.setName(rs.getString(2));
            vo.setNote(rs.getString(3));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Item> findAllBySplit(String colum, String keyWord, Integer curentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String colum, String keyWord) throws SQLException {
        return null;
    }
}
