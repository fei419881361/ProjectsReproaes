package com.example.funchoufu.classhelper.Tab03;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.funchoufu.classhelper.App;
import com.example.funchoufu.classhelper.R;
import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Support.Net;
import com.example.funchoufu.classhelper.Support.TakeDbFile;

public class tab02_yunduan extends AppCompatActivity {

    String path = "";

    String userid = null;
    String student = null;
    String number = null;
    String getInfo = null;
    String gstudent = null;
    String gnumber = null;
    SQLiteDatabase db = null;
    Menbers menbers ;
    Menber menber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_yunduan);
        menbers=((App)getApplicationContext()).getMenbers();
        db=((App)getApplicationContext()).getDb();
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        this.getStuInfo();
        ((Button)findViewById(R.id.tab02_yunduan_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new myThread()).start();
            }
        });
        ((Button)findViewById(R.id.tab02_yunduan_down)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new myThread2()).start();
            }
        });
    }
    private class myThread2 implements Runnable{
        @Override
        public void run() {
            Net n = new Net();
            getInfo=n.getInfo(path,userid,"GBK");
            dealInfo();
        }
    }
    private class myThread implements Runnable{
        @Override
        public void run() {
            Net n = new Net();
            n.upInfo(path,userid,number,student,"GBK");
        }
    }
    private void getStuInfo(){
        for(int i =0 ; i<menbers.size();i++){
            menber = menbers.get(i);
            this.student = menber.getName()+"|";
            this.number = menber.getNumber()+"|";
        }
    }
    private void dealInfo(){
        String[] split =this.getInfo.split("/");
        this.gstudent = split[0];
        this.gnumber = split[1];
        String[] sname = this.gstudent.split("\\|");
        String[] snumber = this.gnumber.split("\\|");
        for(int i=0;i<sname.length;i++){
            TakeDbFile.InputtheseNameIntoDatabase(db,sname[i],snumber[i]);
        }
    }
}

