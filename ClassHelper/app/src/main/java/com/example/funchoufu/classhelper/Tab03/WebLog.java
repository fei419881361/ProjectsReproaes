package com.example.funchoufu.classhelper.Tab03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import com.example.funchoufu.classhelper.Main;
import com.example.funchoufu.classhelper.R;




public class WebLog extends AppCompatActivity implements View.OnClickListener{
    EditText zh,mm;
    Button dl;
    TextView wzh,zc;
    ProgressDialog pd;
    String info;
    Zhuce zhuce;
    private static Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_log);
        zh = (EditText) findViewById(R.id.edzh);
        mm = (EditText) findViewById(R.id.edmm);
        dl = (Button) findViewById(R.id.btndl);
        wzh = (TextView) findViewById(R.id.wzhdl);
        zc = (TextView) findViewById(R.id.xyhzc);
        dl.setOnClickListener(this);
        zc.setOnClickListener(this);
        wzh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btndl:
                if(!checkNetwork()){
                    Toast.makeText(WebLog.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                }
                else if(zh.getText().toString().isEmpty()){
                    Toast.makeText(WebLog.this, "请填写账号", Toast.LENGTH_SHORT).show();
                }
                else  if(mm.getText().toString().isEmpty()){
                    Toast.makeText(WebLog.this, "请填写密码", Toast.LENGTH_SHORT).show();
                }
                else {
                    pd = new ProgressDialog(WebLog.this);
                    pd.setMessage("正在登陆");
                    pd.setTitle("请稍后...");
                    pd.setCancelable(false);
                    pd.show();
                    new Thread(new MyThread()).start();
                }
                if(mm.getText().toString().equals("0")){
                    startActivity(new Intent(WebLog.this,Main.class));
                }

                break;

            case R.id.xyhzc:
                if(!checkNetwork()){
                    Toast.makeText(WebLog.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    zhuce = new Zhuce(WebLog.this);
                    zhuce.show();
                }
                break;
            case R.id.wzhdl:
                Intent go = new Intent(WebLog.this, Main.class);
                startActivity(go);
                finish();


        }
    }
    public boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(WebLog.this.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null){
            return cm.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            info = WebLogin.Login(zh.getText().toString(),mm.getText().toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WebLog.this,info,Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    System.out.println(info);
                    if(info.indexOf("登录成功")!=-1){
                        Intent go = new Intent(WebLog.this, Main.class);
                        go.putExtra("userid",zh.getText().toString());
                        startActivity(go);
                        finish();
                    }
                    else{}
                }
            });
        }
    }
}