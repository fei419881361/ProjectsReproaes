package cn.ifox.book.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 41988 on 2017/7/9.
 */
public class Item implements Serializable {
    private Integer iid;
    private String name;
    private String note;
    private List<Books> book; //一个类别下有多本书
    public String getName() {
        return name;
    }

    public List<Books> getBook() {
        return book;
    }

    public void setBook(List<Books> book) {
        this.book = book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
