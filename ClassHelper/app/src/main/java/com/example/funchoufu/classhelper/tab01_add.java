package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Support.TakeDbFile;
import com.example.funchoufu.classhelper.Support.Takephoto;

public class tab01_add extends Activity {
    ImageView tab01_add_photo;
    CardView tab01_add_sure;
    EditText tab01_add_name;
    EditText tab01_add_number;
    SQLiteDatabase db;
    Menbers menbers;

    public static final int FINISHED=999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab01_add);
        db=((App)getApplicationContext()).getDb();
        menbers=((App)getApplicationContext()).getMenbers();

        Iniview();
        Inievent();

    }
    private void Iniview() {
        tab01_add_photo= (ImageView) findViewById(R.id.tab01_add_photo);
        tab01_add_sure=(CardView)findViewById(R.id.tab01_add_sure);
        tab01_add_name= (EditText) findViewById(R.id.tab01_add_name);
        tab01_add_number= (EditText) findViewById(R.id.tab01_add_number);
    }
    private void Inievent() {
        tab01_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0x1);
            }
        });

        tab01_add_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=tab01_add_name.getText().toString();
                String number=tab01_add_number.getText().toString();
                String id="0";
                //From Here,I did some works for bitmap the right size;
                Bitmap bitmap=((BitmapDrawable)tab01_add_photo.getDrawable()).getBitmap();
                id= String.valueOf(TakeDbFile.InputtheseNameIntoDatabase(db, name, number));
                Takephoto.WriteBitmapIntoFile(getApplicationContext(), bitmap, id);
                Toast.makeText(getApplicationContext(),String.format("成员:%s(%s)已经成功的添加进了数据库",name,number),Toast.LENGTH_SHORT).show();
                menbers.AddMenber(new Menber(name, number, Integer.valueOf(id)));
                Intent Re=new Intent();
                Re.putExtra("moveid",Integer.valueOf(id));
                setResult(FINISHED, Re);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0x1){
            if(data!=null){
                Uri uri=data.getData();
                Takephoto.CutBitmap(uri, this, 0x03);
            }
        }
        if(requestCode==0x03){
            if (data != null) {
                Bundle bundle = data.getExtras();
                //得到图片
                Bitmap bitmap = bundle.getParcelable("data");
                //设置图片
                tab01_add_photo.setImageBitmap(bitmap);
            } else {
                return;
            }
        }
    }

    /**
     * Created by Funchou Fu on 2016/10/7.
     */

}
