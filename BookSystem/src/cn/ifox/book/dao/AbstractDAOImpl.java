package cn.ifox.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by 41988 on 2017/7/8.
 */
public class AbstractDAOImpl {
    protected Connection conn;
    protected PreparedStatement pstmt;

    public AbstractDAOImpl(Connection conn) {
        this.conn = conn;
        int[][] school = new int[][]{{1,4,5},{1,2,3,4,5}};

    }
}
