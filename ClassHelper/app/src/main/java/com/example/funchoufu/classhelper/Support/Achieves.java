package com.example.funchoufu.classhelper.Support;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class Achieves implements Serializable {
    ArrayList<Achieve> achieves;
    SQLiteDatabase db;
    public Achieves(SQLiteDatabase db){
        this.achieves=new ArrayList<Achieve>();
        this.db=db;
    }
    public Achieve getachieve(int index){
        return this.achieves.get(index);
    }
    public Achieve getachieve(String name){
        for(int i=0;i<achieves.size();i++){
            if(achieves.get(i).getName().equals(name))
                return achieves.get(i);
        }
        return null;
    }
    public void AddAchieve(Achieve achieve){
        this.achieves.add(achieve);
    }
    public void RemoveAchieve(Achieve achieve){
        this.achieves.remove(achieve);
    }
    public void RemoveAchieve(int index){
        this.achieves.remove(index);
    }
    public int length(){
        return this.achieves.size();
    }
}
