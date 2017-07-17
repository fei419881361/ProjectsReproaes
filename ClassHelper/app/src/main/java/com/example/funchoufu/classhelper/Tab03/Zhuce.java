package com.example.funchoufu.classhelper.Tab03;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.funchoufu.classhelper.R;


/**
 * Created by 41988 on 2016/10/17.
 */
public class Zhuce extends Dialog {
    Context mcontext;
    EditText e1,e2,e3;
    Button btnzc;
    String info;
    private static Handler handler = new Handler();
    public Zhuce(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        btnzc = (Button) findViewById(R.id.btnzco);
        btnzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Zhuce.MyThread()).start();
            }
        });
    }
    public class MyThread implements Runnable{
        @Override
        public void run() {
            info = WebRegist.Regist(getE1(), getE2(), getE3());  //账号，密码，昵称

            handler.post(new Runnable() {
                @Override
                public void run() {

                    if(info.indexOf("成功")!=-1){
                        Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                    else {
                        if(getE1().isEmpty())
                            Toast.makeText(getContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
                        else if(getE2().isEmpty())
                            Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                        else if(getE3().isEmpty())
                            Toast.makeText(getContext(), "昵称不能为空", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

    }
    @Override
    public void show() {
        super.show();
    }
    public String getE1(){
        String emm = null;
        e1= (EditText) findViewById(R.id.emm);
        emm = e1.getText().toString();
        return emm;
    }
    public String getE2(){
        String emm2 = null;
        e2 = (EditText) findViewById(R.id.ezh);
        emm2 = e2.getText().toString();
        return emm2;
    }
    public String getE3(){
        String emm3 = null;
        e3 = (EditText) findViewById(R.id.zcname);
        emm3 = e3.getText().toString();
        return emm3;
    }
}
