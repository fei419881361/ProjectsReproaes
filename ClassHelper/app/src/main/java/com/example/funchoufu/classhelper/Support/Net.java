package com.example.funchoufu.classhelper.Support;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 41988 on 2016/10/28.
 */
public class Net {

    public String upInfo(String path,String userid,String number,String student,String encode) {
        InputStream is = null;
        OutputStream out = null;
        try {

            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setUseCaches(true);

            out = conn.getOutputStream();
            byte[] bytes = new String("userid="+userid+"&student="+student+"&number="+number).getBytes(encode);
            out.write(bytes);
            out.flush();
            out.close();

            if(conn.getResponseCode()==200){
                is = conn.getInputStream();
                System.out.println("连接成功");
            }
            byte[] b = getBytesByInputStream(is);
            is.close();
            return new String(b,encode);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }
    public String getInfo(String path,String userid,String encode){
        InputStream is = null;
        OutputStream out = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setUseCaches(true);

            out = conn.getOutputStream();
            byte[] bytes = new String("userid="+userid).getBytes(encode);
            out.write(bytes);
            out.flush();
            out.close();

            if(conn.getResponseCode()==200){
                is = conn.getInputStream();
                System.out.println("连接成功");
            }
            byte[] b = getBytesByInputStream(is);
            is.close();
            return new String(b,encode);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }
    private static byte[] getBytesByInputStream(InputStream is){
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
