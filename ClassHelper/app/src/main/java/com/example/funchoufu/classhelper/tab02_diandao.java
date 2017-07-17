package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Tab02.Tab02Work;

import java.io.IOException;
import java.util.ArrayList;


public class tab02_diandao extends Activity {

    Menbers M;
    ArrayList<Menber> menbers;
    ArrayList<Menber> arrived;
    ArrayList<Menber> lated;

    CardView top;
    CardView left;
    CardView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab02_diandao);
        M = ((App) getApplicationContext()).getMenbers();
        menbers = new ArrayList<Menber>();
        arrived = new ArrayList<Menber>();
        lated = new ArrayList<Menber>();


        Inivalue();
        Iniview();
        Inievent();
    }

    private void Inivalue() {
        for (int i = 0; i < M.size(); i++) {
            menbers.add(new Menber(M.get(i)));
        }
    }

    private void Inievent() {
//        final Tab02Work<Menber> Ar=new Tab02Work<Menber>(menbers,arrived);
//        final Tab02Work<Menber> Lt=new Tab02Work<Menber>(menbers,lated);
        final Activity mactivity=this;
        final Tab02Work Ar = new Tab02Work(menbers, arrived);
        final Tab02Work Lt = new Tab02Work(menbers, lated);
        if (Ar.Inithree(top, left, right, this)) {
            Toast.makeText(getApplicationContext(), "你的学生名单中人数不足无法点到", Toast.LENGTH_SHORT).show();
            finish();
        }

        findViewById(R.id.tab02_diandao_dao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ar.Click(top, left, right,mactivity )) {
                    Next();
                }
            }
        });
        findViewById(R.id.tab02_diandao_que).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), String.format("%s已经确定为未到!", ((TextView) left.findViewById(R.id.leftname)).getText().toString()), Toast.LENGTH_SHORT).show();
                if (Lt.Click(top, left, right, mactivity)) {
                    Next();
                }
            }
        });
    }

    private void Iniview() {
        top = (CardView) findViewById(R.id.top);
        left = (CardView) findViewById(R.id.left);
        right = (CardView) findViewById(R.id.right);
    }


    private void Next() {
        Intent intent = new Intent(tab02_diandao.this, tab02_diandao_finally.class);
        intent.putExtra("arrive", arrived);
        intent.putExtra("late", lated);
        startActivity(intent);
        finish();
    }
}
