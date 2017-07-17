package cn.ifox.book.dao.Impl;

import cn.ifox.book.dao.AbstractDAOImpl;
import cn.ifox.book.dao.ILenbookDAO;
import cn.ifox.book.vo.Books;
import cn.ifox.book.vo.Lenbook;
import cn.ifox.book.vo.Member;
import com.mysql.jdbc.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by 41988 on 2017/7/10.
 */
public class LenbookDAOImpl extends AbstractDAOImpl implements ILenbookDAO {
    public LenbookDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Lenbook vo) throws SQLException {
        String sql = "INSERT INTO lenbook(bid,mid,credate) VALUES(?,?,?)";
        String sql2 = "UPDATE books SET status=2 WHERE bid="+vo.getBooks().getBid();
        super.pstmt = super.conn.prepareStatement(sql);
        PreparedStatement pstmt1 = super.conn.prepareStatement(sql2);
        pstmt1.executeUpdate();
        super.pstmt.setInt(1,vo.getBooks().getBid());
        super.pstmt.setString(2,vo.getMember().getMid());
        super.pstmt.setString(3, String.valueOf(new Timestamp(new Date().getTime())));
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doUpdate(Lenbook vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Lenbook findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Lenbook> finaAll() throws SQLException {
        return null;
    }

    @Override
    public List<Lenbook> findAllBySplit(String colum, String keyWord, Integer curentPage, Integer lineSize) throws SQLException {
        List<Lenbook> all = new ArrayList<Lenbook>();
        String sql =" SELECT l.leid,b.name,m.name,l.credate,l.retdate,b.bid " +
                " FROM  lenbook l,books b,member m " +
                " WHERE l.bid = b.bid  AND l.mid = m.mid  AND l."+colum+" LIKE ? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        super.pstmt.setInt(2,(curentPage-1) * lineSize);
        super.pstmt.setInt(3,lineSize);
        ResultSet rs = (ResultSet) super.pstmt.executeQuery();
        while (rs.next()){
            Lenbook vo = new Lenbook();
            vo.setLeid(rs.getInt(1));
            Books books = new Books();
            books.setName(rs.getString(2));
            books.setBid(rs.getInt(6));
            vo.setBooks(books); // bid
            Member member = new Member();
            member.setName(rs.getString(3));
            vo.setMember(member); // mid
            vo.setCredate(rs.getDate(4));
            vo.setRetdate(rs.getDate(5));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String colum, String keyWord) throws SQLException {
        String sql = "SELECT COUNT(*) FROM lenbook WHERE "+colum+" LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        ResultSet rs = (ResultSet) super.pstmt.executeQuery();
        if (rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateByretdate(Integer leid, Date retdate,Integer bid) throws SQLException {
        String sql = "UPDATE lenbook SET retdate=?WHERE leid=?";
        String sql2 = "UPDATE books SET status=1 WHERE bid="+bid;
        PreparedStatement pstmt1 = super.conn.prepareStatement(sql2);
        pstmt1.executeUpdate();
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setDate(1, new java.sql.Date(retdate.getTime()));
        super.pstmt.setInt(2,leid);
        return super.pstmt.executeUpdate()>0;
    }
}
