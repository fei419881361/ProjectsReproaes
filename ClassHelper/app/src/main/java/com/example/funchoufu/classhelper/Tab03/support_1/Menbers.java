package com.example.funchoufu.classhelper.Tab03.support_1;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class Menbers implements Serializable {
    ArrayList<Menber> menbers;
    SQLiteDatabase db;
    public Menbers(SQLiteDatabase db){
        this.menbers=new ArrayList<>();
        this.db=db;
    }
    public int AddMenber(Menber menber){ //添加成员，并返还添加的编号
        for (int i=0;i<this.menbers.size();i++){
            if(menber.getNumber().compareTo(menbers.get(i).getNumber())<0){
                menbers.add(i,menber);
                return i;
            }
        }
        menbers.add(menber);
        return this.menbers.size();
    }
    public void RemoveMenber(int index){
        this.menbers.remove(index);
    }
    public void RemoveMenber(Menber menber){
        this.menbers.remove(menber);
    }
    public int size(){
        return this.menbers.size();
    }
    public Menber get(int index){
        return this.menbers.get(index);
    }
}
