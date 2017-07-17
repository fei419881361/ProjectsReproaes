package com.example.funchoufu.classhelper.Tab03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 41988 on 2016/10/17.
 */
public class WebLogin {


    public static String Login (String username,String password){
        try{
            String path = "http://" + "192.168.43.75:8080" + "/HellooWeb/LoginServlet";
            URL url= new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(50000);
            //conn.setRequestProperty();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(true);
            OutputStream os = conn.getOutputStream();
            byte[] requestbody = new String("userid="+username+"&password="+password).getBytes("GBK");
            os.write(requestbody);
            os.flush();
            os.close();
            InputStream is = conn.getInputStream();
            byte[] b =  getBytesByInputStream(is);
            System.out.println(new String(b,"GBK"));
            return new String(b,"GBK");
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }
    private static byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
}
}
