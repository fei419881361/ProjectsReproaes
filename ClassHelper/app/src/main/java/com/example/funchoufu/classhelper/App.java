package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.funchoufu.classhelper.Support.Achieves;
import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;
import com.example.funchoufu.classhelper.Support.TakeDbFile;

import java.util.ArrayList;

/**
 * Created by Funchou Fu on 2016/10/5.
 */
public class App extends Application {
    SQLiteDatabase db;

    Menbers menbers;
    ArrayList<Menber>[] groups;
    Achieves achieves;
    public void setDb(SQLiteDatabase db,Activity activity) {
        this.db = db;
        menbers=new Menbers(db);
        achieves=new Achieves(db);
        TakeDbFile.OutputmenberFromDatabase(db, menbers, activity);
        TakeDbFile.OutputAchieveFromDatabase(db,achieves);
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    public Achieves getAchieves() {
        return achieves;
    }

    public Menbers getMenbers() {
        return menbers;
    }

    public void setGroups(ArrayList<Menber>[] groups) {
        this.groups = groups;
    }

    public ArrayList<Menber>[] getGroups() {
        ArrayList<Menber>[] g=this.groups;
        this.groups=null;
        return g;

    }
}