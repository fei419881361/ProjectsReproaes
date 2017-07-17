package Test;

import Utils.WeixinUtils;
import domain.Access_Token;

/**
 * Created by 41988 on 2017/5/20.
 */
public class WeiXinTest {
    public static void main(String[] args){
        Access_Token access_token = WeixinUtils.getToken();
        System.out.print(access_token.getAccess_token()+"------"+access_token.getExpires_in());
    }
}
