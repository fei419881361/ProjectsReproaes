package domain;

/**
 * Created by 41988 on 2017/5/20.
 * ising:是否可借状态，0可借，1不能借
 */
public class Book {
    private int id;
    private String book_name;
    private String now_user;
    private String recent_user;
    private int ising;

    public int getId() {
        return id;
    }

    public String getBook_name() {
        return book_name;
    }

    public int getIsing() {
        return ising;
    }

    public String getNow_user() {
        return now_user;
    }

    public String getRecent_user() {
        return recent_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setIsing(int ising) {
        this.ising = ising;
    }

    public void setNow_user(String now_user) {
        this.now_user = now_user;
    }

    public void setRecent_user(String recent_user) {
        this.recent_user = recent_user;
    }

}
