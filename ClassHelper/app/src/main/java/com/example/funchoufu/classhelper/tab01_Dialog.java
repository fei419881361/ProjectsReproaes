package com.example.funchoufu.classhelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.funchoufu.classhelper.Support.MenberWork;
import com.example.funchoufu.classhelper.Support.Menbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.read.biff.BiffException;

public class tab01_Dialog extends AppCompatActivity {
    public static final int CHANGED=1024;
    private static final int NAMECOLUMNCHANGE=256;
    private static final int NUMBERCOLUMNCHANGE=128;
    SQLiteDatabase db;
    Menbers menbers;
    String path;
    private int namecolumn;
    private int numbercolumn;
    private Spinner namelumn;
    private Spinner numberlumn;
    List<String> spinnerlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab01__dialog);
        db = ((App) getApplicationContext()).getDb();
        menbers = ((App) getApplicationContext()).getMenbers();
        spinnerlist=new ArrayList<String>();

        Intent intent=getIntent();
        path=intent.getStringExtra("path");
        System.out.println("DEBUG"+path);

        Iniview();
        Inivalue();
        Inievent();
    }

    private void Inievent() {
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click();
            }
        });
        namelumn.setOnItemSelectedListener(new MyOnItemSelectedListener(NAMECOLUMNCHANGE));
        numberlumn.setOnItemSelectedListener(new MyOnItemSelectedListener(NUMBERCOLUMNCHANGE));
    }

    private void Iniview() {
        namelumn = (Spinner) findViewById(R.id.name);
        numberlumn = (Spinner) findViewById(R.id.number);

        namecolumn=0;
        numbercolumn=1;
    }

    private void Inivalue() {
        int longth=0;
        MenberWork work=new MenberWork(db,menbers);
        try {
            longth=work.LongthofExcel(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        for(int i=0;i<longth;i++)
            spinnerlist.add(i+1+"");
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        namelumn.setAdapter(arrayAdapter);
        numberlumn.setAdapter(arrayAdapter);
    }
    private void Click(){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.select_tab01_add_photo_img);
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        MenberWork work = new MenberWork(db, menbers);
        work.AddPeopleFromFile(path, namecolumn, numbercolumn, this, bitmap);
        setResult(CHANGED);
        finish();
    }

    private class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        int what;
        public MyOnItemSelectedListener(int what){
            this.what=what;
        }
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(this.what==NAMECOLUMNCHANGE)
                namecolumn=i;
            else
                numbercolumn=i;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
