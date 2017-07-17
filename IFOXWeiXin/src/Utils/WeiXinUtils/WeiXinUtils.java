package Utils.WeiXinUtils;

import Utils.MessageUtils.ConstantMessage;
import domain.Access_Token.Access_Token;
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
 * Created by 41988 on 2017/7/16.
 */
public class WeiXinUtils {
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
        String url = ConstantMessage.URL.replace("APPID", ConstantMessage.appid).replace("APPSECRET", ConstantMessage.secret);
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
        int i = -1;
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", token);
        JSONObject  jsonObject = doPost(url, menu);
        if(jsonObject!=null){
            System.out.println("---------------");
            System.out.println(jsonObject.toString());
            i = jsonObject.getInt("errcode");
        }
        return i;
    }
    public static int deleteMenu(String token){
        int i = -1;
        String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", token);
        JSONObject  jsonObject = doGet(url);
        if(jsonObject!=null){
            System.out.println("---------------");
            System.out.println(jsonObject.toString());
            i = jsonObject.getInt("errcode");
        }
        return i;
    }

}
