package com.example.funchoufu.classhelper.Tab03.support_1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Funchou Fu on 2016/10/5.
 */
public class TakeDbFile {
    //将下列数据添加进入 menbers 表,并且添加后的id
    public static int InputtheseNameIntoDatabase(SQLiteDatabase db,String name,String number){
        String id="0";
        Cursor cursor=db.rawQuery("select * from menbers order by id asc",null);
        if(cursor.moveToLast())
            id=String.valueOf(cursor.getInt(cursor.getColumnIndex("id")) + 1);
        db.execSQL("insert into menbers(name,number,id)values(?,?,?)", new String[]{name, number, id});
        return Integer.valueOf(id);
    }
    public static void OutputmenberFromDatabase(SQLiteDatabase db,Menbers menbers,Activity activity){
        Cursor cursor=db.rawQuery("select * from menbers order by number asc", null);
        if(cursor.moveToFirst()){
            for(;;cursor.moveToNext()){
                Menber menber=new Menber(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("number")),cursor.getInt(cursor.getColumnIndex("id")));
                menbers.AddMenber(menber);
                if (cursor.isLast())
                    break;
            }
        }
        else
            Toast.makeText(activity,"数据库中没有学生数据",Toast.LENGTH_SHORT).show();
    }
    public static void RemoveMenberFromDatabase(SQLiteDatabase db,Menber menber){
        db.execSQL("delete from menbers where id =?",new String[]{String.valueOf(menber.getPhoto())});
    }

}
