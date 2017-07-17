package cn.ifox.book.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by 41988 on 2017/7/10.
 */
public class Books implements Serializable {
    private Integer bid;
    private String name;
    private Date credate;
    private Integer status;
    private String note;
    private Item item; //一本书属于一个类别 iid
    private Admin admin;//图书由谁增加 aid
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
