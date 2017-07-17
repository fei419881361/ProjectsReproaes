package com.example.funchoufu.classhelper;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.funchoufu.classhelper.Support.Achieve;
import com.example.funchoufu.classhelper.Support.Achieves;

import java.util.ArrayList;

/**
 * Created by Funchou Fu on 2016/10/27.
 */
public class tab02_fenzu_dialog extends Dialog {
    Context mContext;
    Spinner mSpinner;
    ArrayList<String> mList;
    String select;
    Dialog mDialog;
    public tab02_fenzu_dialog(Context context,ArrayList<String> list) {
        super(context);
        mContext=context;
        mList=list;
        select=list.get(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlyspinner);
        //得到传入列表中的第一个元素

        //对spinner 进行初始化赋值
        mSpinner= (Spinner) findViewById(R.id.spinner);
        mDialog=this;

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,mList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = mList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select!=null) {
                    ((tab02_fenzu) mContext).Add(select);
                    mDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.CENTER;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= 500;

        getWindow().getDecorView().setPadding(50, 0, 0, 0);

        getWindow().setAttributes(layoutParams);

    }

}
