package domain;

/**
 * Created by 41988 on 2017/5/20.
 * access_token的实体类
 * access_token;访问请求票据
 * expires_in;有效时间
 */
public class Access_Token {
    private String access_token;
    private int expires_in;
    public String getAccess_token() {
        return access_token;
    }
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public int getExpires_in() {
        return expires_in;
    }
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
