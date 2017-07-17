package com.example.funchoufu.classhelper.Support;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Funchou Fu on 2016/10/5.
 */
public class TakeDbFile {
    //将数据修改后，根据id，查找出它根据学号排列后的个数.
    public static int ChangetheseIntoDatabase(SQLiteDatabase db, String name, String number, String id) {
        db.execSQL("update menbers set name=? where id = ?", new String[]{name, id});
        db.execSQL("update menbers set number=? where id = ?", new String[]{number, id});
        Cursor cursor = db.rawQuery("select * from menbers order by number asc", null);
        int i;
        for (i = 0, cursor.moveToFirst(); ; cursor.moveToNext(), i++) {
            if (cursor.getInt(cursor.getColumnIndex("id")) == Integer.valueOf(id))
                return i;
        }
    }

    //将下列数据添加进入 menbers 表,并且添加后的id
    public static int InputtheseNameIntoDatabase(SQLiteDatabase db, String name, String number) {
        String id = "0";
        Cursor cursor = db.rawQuery("select * from menbers order by id asc", null);
        if (cursor.moveToLast())
            id = String.valueOf(cursor.getInt(cursor.getColumnIndex("id")) + 1);
        db.execSQL("insert into menbers(name,number,id)values(?,?,?)", new String[]{name, number, id});
//        Bitmap bitmap=((BitmapDrawable).getDrawable()).getBitmap();
        return Integer.valueOf(id);
    }
    public static int InputtheseNameIntoDatabase(SQLiteDatabase db, Menber menber) {
        String name=menber.getName();
        String number=menber.getNumber();
        String id = "0";
        Cursor cursor = db.rawQuery("select * from menbers order by id asc", null);
        if (cursor.moveToLast())
            id = String.valueOf(cursor.getInt(cursor.getColumnIndex("id")) + 1);
        db.execSQL("insert into menbers(name,number,id)values(?,?,?)", new String[]{name, number, id});
//        Bitmap bitmap=((BitmapDrawable).getDrawable()).getBitmap();
        return Integer.valueOf(id);
    }

    public static void OutputmenberFromDatabase(SQLiteDatabase db, Menbers menbers, Activity activity) {
        Cursor cursor = db.rawQuery("select * from menbers order by number asc", null);
        if (cursor.moveToFirst()) {
            for (; ; cursor.moveToNext()) {
                Menber menber = new Menber(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("number")), cursor.getInt(cursor.getColumnIndex("id")));
                menbers.AddMenber(menber);
                if (cursor.isLast())
                    break;
            }
        } else
            Toast.makeText(activity, "数据库中没有学生数据", Toast.LENGTH_SHORT).show();
    }

    public static void RemoveMenberFromDatabase(SQLiteDatabase db, Menber menber) {
        db.execSQL("delete from menbers where id =?", new String[]{String.valueOf(menber.getPhoto())});
    }

    public static void RemoveMenberFromDatabase(SQLiteDatabase db, int id) {
        db.execSQL("delete from menbers where id =?", new String[]{String.valueOf(id)});
    }

    public static void RemoveAllMenbersFromDatabase(SQLiteDatabase db) {
        db.execSQL("delete from menbers");
    }

    public static void AddNewAchiveIntoDatabase(SQLiteDatabase db, Achieve achieve) {
        String data = AchieveWorker.AchieveToString(achieve);
        String name = achieve.getName();
        String id = "0";
        Cursor cursor = db.rawQuery("select * from achieves order by id asc", null);
        if (cursor.moveToLast()) {
            id = String.valueOf(cursor.getInt(cursor.getColumnIndex("id")) + 1);
        }
        db.execSQL("insert into achieves(name,data,id)values(?,?,?)", new String[]{name, data, id});
    }

    public static void RemoveAchiveFromDatabase(SQLiteDatabase db, Achieve achieve) {
        Cursor cursor=db.rawQuery("select * from achieves order by id asc",null);
        if(cursor.moveToFirst()){
            for(;;cursor.moveToNext()){
                System.out.println("DEBUG"+cursor.getInt(cursor.getColumnIndex("id")));
                if (cursor.isLast())
                    break;
            }
        }
        System.out.println("DEBUG:Nowid:"+achieve.getId());
        db.execSQL("delete from achieves where id =?", new String[]{String.valueOf(achieve.getId())});
    }

    public static void OutputAchieveFromDatabase(SQLiteDatabase db, Achieves achieves) {
        Cursor cursor=db.rawQuery("select * from achieves order by id asc",null);
        if(cursor.moveToFirst()){
            for(int i=0;;i++,cursor.moveToNext()){
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String data=cursor.getString(cursor.getColumnIndex("data"));
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                Achieve achieve= AchieveWorker.StringToAchieve(data);
                achieve.setName(name);
                achieve.setId(id);
                achieves.AddAchieve(achieve);
                if(cursor.isLast())
                    break;
            }
        }
    }
}