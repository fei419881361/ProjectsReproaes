package cn.ifox.book.dao;

import cn.ifox.book.vo.Member;

import java.sql.SQLException;

/**
 * Created by 41988 on 2017/7/8.
 */
public interface IMemberDAO extends IDAO<String,Member> {
    @Override
    boolean doCreate(Member vo) throws SQLException;
}
