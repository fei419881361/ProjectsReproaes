package Utils;

import domain.Access_Token;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

/**
 * Created by 41988 on 2017/5/20.
 * 包装发送给微信服务器的GET请求获取access_token
 *appid 和 secret是微信公众号固定的参数
 * URL 为请求token的标准格式地址
 */
public class WeixinUtils {
    public static final  int CANBORROW = 0;
    public static final  int BORROWED = 1;
    public static final  int NOTRETURN = 2;
    public static final String appid = "wx157109197bc8e05e";
    public static final String secret = "691e04b0906f163bead24a4064df70d2";
    public static String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String  Menu_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * jsonObject 用于接收返回结果
     * response 接收执行结果
     * */
    public static JSONObject doGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //entity 转为 String , String再转为JSONObject
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * doPost 提交参数
     * */
    public static JSONObject doPost(String url,String outStr){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;

        try {
            httpPost.setEntity(new StringEntity(outStr,"utf-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity,"UTF-8");
                jsonObject =JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 获取token的方法
     * 提交GET请求
     * 返回access_token实体对象
     * */
    public static Access_Token getToken(){
        Access_Token access_token = new Access_Token();
        String url = URL.replace("APPID", appid).replace("APPSECRET", secret);
        JSONObject jsonObject = null;
        jsonObject = doGet(url);
        if(jsonObject!=null){
            access_token.setAccess_token(jsonObject.getString("access_token"));
            access_token.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return access_token;
    }

    /**
     * 获取用户的昵称
     * @param FromUserName
     * */
    public static String getUserNickName(String FromUserName){
        //请求的标准链接
        String URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        String token = "";//需要的token
//        Access_Token access_token = new Access_Token();
//        access_token = WeixinUtils.getToken(); //注意！！后续这里代码需要修改，access_token获取不会是直接去请求token
        token = GetAccessToken.getToken();
        URL = URL.replace("ACCESS_TOKEN", token).replace("OPENID", FromUserName);//将标准URL中的关键词替换
        JSONObject jsonObject = doGet(URL);//获取请求后的返回值
        String nickName = jsonObject.get("nickname").toString();
        return nickName;
    }
    /**
     * 初始化菜单
     * */
    public static int creatMenu(String token, String menu){
        int i = 0;
        String url = Menu_URL;
        url = url.replace("ACCESS_TOKEN", token);
        JSONObject  jsonObject = doPost(url, menu);
        if(jsonObject!=null){
            i = jsonObject.getInt("errcode");
        }
        return i;
    }
}
