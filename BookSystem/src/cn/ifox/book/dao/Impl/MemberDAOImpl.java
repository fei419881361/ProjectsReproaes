package cn.ifox.book.dao.Impl;

import cn.ifox.book.dao.AbstractDAOImpl;
import cn.ifox.book.dao.IMemberDAO;
import cn.ifox.book.vo.Member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 41988 on 2017/7/8.
 */
public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(mid,name,age,sex,phone) VALUES(?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getMid());
        super.pstmt.setString(2,vo.getName());
        super.pstmt.setInt(3,vo.getAge());
        super.pstmt.setInt(4,vo.getSex());
        super.pstmt.setString(5,vo.getPhone());
        return super.pstmt.executeUpdate()>0;
    }

    @Override
    public boolean doUpdate(Member vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemove(Set<?> ids) throws SQLException {
        return false;
    }

    @Override
    public Member findById(String id) throws SQLException {
        Member vo = null;
        String sql = "SELECT mid name,age,sex,phone FROM member WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,id);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()){
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getInt(4));
            vo.setPhone(rs.getString(5));
        }
        return vo;
    }

    @Override
    public List<Member> finaAll() throws SQLException {
        List<Member> all = new ArrayList<>();
        String sql = "SELECT mid,name,age,sex,phone FROM member";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        MemberUtils(all, rs);
        return all;
    }

    private void MemberUtils(List<Member> all, ResultSet rs) throws SQLException {
        while (rs.next()){
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setName(rs.getString(2));
            vo.setAge(rs.getInt(3));
            vo.setSex(rs.getInt(4));
            vo.setPhone(rs.getString(5));
            all.add(vo);
        }
    }

    @Override
    public List<Member> findAllBySplit(String colum, String keyWord, Integer curentPage, Integer lineSize) throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT m.mid,m.name,m.age,m.sex,m.phone FROM member m LIMIT x1,x2";
        sql = sql.replace("x1",(curentPage-1)*lineSize+"");
        sql = sql.replace("x2",lineSize+"");
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet rs = super.pstmt.executeQuery();
        MemberUtils(members, rs);
        return members;
    }

    @Override
    public Integer getAllCount(String colum, String keyWord) throws SQLException {
        String sql = "SELECT COUNT(*) FROM member WHERE "+colum+" LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,"%"+keyWord+"%");
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
}
