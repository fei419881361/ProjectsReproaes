package com.example.funchoufu.classhelper;
//Version 1.0

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

import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Support.TakeDbFile;
import com.example.funchoufu.classhelper.Support.Takephoto;

import java.io.IOException;

public class tab01_delete extends Activity {

    public static final int STARTTABA01_DELETE=640;
    public static final int STARTTABA01_DELETE2=641;
    public static final int FINISHED_SURE=642;
    public static final int FINISHED_DELETE=643;


    EditText tab01_delete_name;
    EditText tab01_delete_number;
    ImageView tab01_delete_photo;
    CardView tab01_delete_sure;
    CardView tab01_delete_remove;

    SQLiteDatabase db;
    Menbers menbers;
    String name;
    String number;
    int id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab01_delete);
        Iniview();
        Inievent();

        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        number=intent.getStringExtra("number");
        id=intent.getIntExtra("id", 0);
        position=intent.getIntExtra("position", 0);
        db=((App)getApplicationContext()).getDb();
        menbers=((App)getApplicationContext()).getMenbers();
        System.out.println("DEBUG:"+position);

        tab01_delete_name.setText(name);
        tab01_delete_number.setText(number);
        try {
            tab01_delete_photo.setImageBitmap(Takephoto.ReadBitmapFromInnerFile(id, "menber", this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void Inievent() {
        tab01_delete_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, STARTTABA01_DELETE);
            }
        });
        tab01_delete_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= tab01_delete_name.getText().toString();
                String number= tab01_delete_number.getText().toString();
                Takephoto.WriteBitmapIntoFile(getApplicationContext(), ((BitmapDrawable) tab01_delete_photo.getDrawable()).getBitmap(), String.valueOf(id));

                int resultposition= TakeDbFile.ChangetheseIntoDatabase(db, name, number, String.valueOf(id));
                menbers.ChangeMenber(name,number,id);
                for(int i=0;i<menbers.size();i++){
                    System.out.println("Debug Menber:"+menbers.get(i).getName());
                }

                Intent intent=new Intent();
                intent.putExtra("from",position);
                intent.putExtra("to", resultposition);
                setResult(FINISHED_SURE, intent);
                finish();
            }
        });
        tab01_delete_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("from",position);
                menbers.RemoveMenber(position);
                TakeDbFile.RemoveMenberFromDatabase(db,id);
                setResult(FINISHED_DELETE, intent);
                finish();
            }
        });
    }
    private void Iniview() {
        tab01_delete_name= (EditText) findViewById(R.id.tab01_delete_name);
        tab01_delete_number= (EditText) findViewById(R.id.tab01_delete_number);
        tab01_delete_photo= (ImageView) findViewById(R.id.tab01_delete_photo);
        tab01_delete_sure= (CardView) findViewById(R.id.tab01_delete_sure);
        tab01_delete_remove= (CardView) findViewById(R.id.tab01_delete_remove);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==STARTTABA01_DELETE){
            if(resultCode==Activity.RESULT_OK){
                Uri uri=data.getData();
                Takephoto.CutBitmap(uri,this,STARTTABA01_DELETE2);
            }
        }
        if(requestCode==STARTTABA01_DELETE2){
            if(data!=null){
                Bundle bundle=data.getExtras();
                Bitmap bitmap=bundle.getParcelable("data");
                ((ImageView)findViewById(R.id.tab01_delete_photo)).setImageBitmap(bitmap);
            }
        }
    }
}