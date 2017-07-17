package com.example.funchoufu.classhelper.Tab03;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 41988 on 2016/10/17.
 */
public class WebRegist {
    public static String Regist (String username,String password,String name){
        try{

            String path = "http://" + "192.168.43.75:8080" + "/HellooWeb/RegLet";
            Map<String,String> parms = new HashMap<>();
            parms.put("userid",username);
            parms.put("password", password);
            parms.put("name",name);
            return sendPostRequest(path,parms,"GBK");
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }

    private static String sendPostRequest(String path, Map<String, String> parms, String s) throws Exception {
        List<NameValuePair> pairs = new ArrayList<>();
        if(parms!=null&&!parms.isEmpty()){
            for(Map.Entry<String,String> entry:parms.entrySet()){
                pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs,s);
        HttpPost post = new HttpPost(path);
        post.setEntity(entity);

        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {

            return getInfo(response);
        } else {return "";}
    }
    private static String getInfo(HttpResponse response) throws Exception {
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        // 将输入流转化为byte型
        byte[] data = read(is);
        // 转化为字符串
        return new String(data, "GBK");
    }
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len ;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}
