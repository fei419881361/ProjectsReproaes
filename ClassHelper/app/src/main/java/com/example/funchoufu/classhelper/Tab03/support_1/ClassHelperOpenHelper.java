package com.example.funchoufu.classhelper.Tab03.support_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Funchou Fu on 2016/10/5.
 */
public class ClassHelperOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_MENBERS="create table menbers(" +
            "id integer primary key autoincrement," +
            "name text," +
            "number text)";
    public static final String CREATE_ACHIEVES="create table achieves(" +
            "name text," +
            "data text)";
    private Context mcontext;

    public ClassHelperOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ACHIEVES);
        sqLiteDatabase.execSQL(CREATE_MENBERS);
        Toast.makeText(mcontext,"成功的创建数据库!",Toast.LENGTH_SHORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
