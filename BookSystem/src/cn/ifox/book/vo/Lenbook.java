package cn.ifox.book.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 41988 on 2017/7/10.
 */
public class Lenbook implements Serializable{
    private Integer leid;
    private Books books;
    private Member member;
    private Date credate;
    private Date retdate;

    public Integer getLeid() {
        return leid;
    }

    public void setLeid(Integer leid) {
        this.leid = leid;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public Date getRetdate() {
        return retdate;
    }

    public void setRetdate(Date retdate) {
        this.retdate = retdate;
    }
}
