package com.example.funchoufu.classhelper.Tab03.support_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Funchou Fu on 2016/10/5.
 */
public class Takephoto {
    public static final int RIGHTSIZE=51961;

    public static Bitmap GetBitmap(Uri uri, Context mcontext) {
        return getImg(uri, mcontext);
    }
    public static void CutBitmap(Uri uri,Context mcontext,int requestcode){
        //            InputStream inputStream = mcontext.getContentResolver().openInputStream(uri);
        //从输入流中解码位图
        // Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        //保存位图
        // img.setImageBitmap(bitmap);
        cutImg(uri, (Activity) mcontext, requestcode);
        //关闭流
//            inputStream.close();
    }
    public static Bitmap ComPress(Bitmap bitmap){       //根据类中定义的大小，来确定压缩后bitmap的大小
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] b=os.toByteArray();
        System.out.println("DEBUG");
        if(b.length<=RIGHTSIZE)
            return bitmap;
        else{
            double i=b.length/RIGHTSIZE;
            return zoomImage(bitmap,bitmap.getWidth()/Math.sqrt(i),bitmap.getHeight()/Math.sqrt(i));
        }
    }
    public static Bitmap ReadBitmapFromInnerFile(int id,String idname,Activity activity) throws IOException {
        FileInputStream fis = activity.openFileInput(idname + String.valueOf(id));
        byte[] in = new byte[fis.available()];
        fis.read(in);
        Bitmap bitmap = BitmapFactory.decodeByteArray(in, 0, in.length);
        return bitmap;
    }

    public static void WriteBitmapIntoInnerFile(Menber menber,Bitmap bitmap,Activity activity){
        try {


            FileOutputStream fos=activity.openFileOutput("menber"+menber.getPhoto(), Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        Bitmap bitmap=Bitmap.createBitmap(bgimage,0,0,(int)newWidth,(int)newHeight);
        return bitmap;
    }

    private static void cutImg(Uri uri,Activity activity,int requestcode) {
        if (uri != null) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            //true:出现裁剪的框
            intent.putExtra("crop", "true");
            //裁剪宽高时的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //裁剪后的图片的大小
            intent.putExtra("outputX", 500);
            intent.putExtra("outputY", 500);
            intent.putExtra("return-data", true);  // 返回数据
            intent.putExtra("output", uri);
            intent.putExtra("scale", true);
            activity.startActivityForResult(intent, requestcode);
            /*if (requestCode == 0x2) {
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    //得到图片
                    Bitmap bitmap = bundle.getParcelable("data");
                    //保存到图片到本地
                    saveImg(bitmap);
                    //设置图片
                    img.setImageBitmap(bitmap);
                } else {
                    return;
                }
            }*/
        } else {
            return;
        }
    }

    private static Bitmap getImg(Uri uri, Context mcontext) {
        try {
            InputStream inputStream = mcontext.getContentResolver().openInputStream(uri);
            //从输入流中解码位图
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            //保存位图
            //关闭流
            inputStream.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
