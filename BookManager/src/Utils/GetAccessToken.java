package Utils;


import domain.Access_Token;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * Created by 41988 on 2017/5/21.
 */
public class GetAccessToken {
    public static String getToken(){
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Long currenttime = new Date().getTime()/1000;
        try {
            Properties props = new Properties();   // 创建Properties对象
            fileInputStream = new FileInputStream("F:\\IDEAPROJECTS\\BookManager\\web\\WEB-INF\\Access_Token");
            props.loadFromXML(fileInputStream);
            String time = (String) props.get("time");
            String token = (String) props.get("token");
            Long time2 = Long.parseLong(time);
            if(currenttime-time2>=7000){
                fileOutputStream = new FileOutputStream("F:\\IDEAPROJECTS\\BookManager\\web\\WEB-INF\\Access_Token");
                Access_Token token2 = WeixinUtils.getToken();
                props.put("token",token2.getAccess_token());
                props.put("time",new Date().getTime()/1000+"");
                props.storeToXML(fileOutputStream,null);
                return token2.getAccess_token();
            }else {
                return token;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
//    public static void main(String arge[]) {
//        String token = GetAccessToken.getToken();
//        System.out.print(token);
//    }
}
