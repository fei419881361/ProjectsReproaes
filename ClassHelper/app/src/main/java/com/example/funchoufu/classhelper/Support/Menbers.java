package com.example.funchoufu.classhelper.Support;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class Menbers implements Serializable {
    ArrayList<Menber> menbers;
    SQLiteDatabase db;

    public Menbers(SQLiteDatabase db) {
        this.menbers = new ArrayList<Menber>();
        this.db = db;
    }

    public int AddMenber(Menber menber) { //添加成员，并返还添加的编号
        for (int i = 0; i < this.menbers.size(); i++) {
            if (menber.getNumber().compareTo(menbers.get(i).getNumber()) < 0) {
                menbers.add(i, menber);
                return i;
            }
        }
        menbers.add(menber);
        Collections.sort(menbers, new Comparator<Menber>() {
            @Override
            public int compare(Menber menber, Menber t1) {
                return menber.getNumber().compareTo(menber.getNumber());
            }
        });
        return this.menbers.size();
    }

    //Change the target menber's information,and reset them ordered by number asc;
    public int ChangeMenber(String name, String number, int id) {
        Menber buf = null;
        for (int i = 0; i < this.menbers.size(); i++) {
            if (this.menbers.get(i).getPhoto() == id) {
                buf = new Menber(name, number, id);
                this.menbers.get(i).setName(name);
                this.menbers.get(i).setNumber(number);
                this.menbers.remove(i);
                AddMenber(buf);
                return i;
            }
        }

        return 0;
    }

    public void RemoveAllMenber() {
        this.menbers.removeAll(this.menbers);
    }

    public void RemoveMenber(int index) {
        this.menbers.remove(index);
    }

    public void RemoveMenber(Menber menber) {
        this.menbers.remove(menber);
    }

    public int size() {
        return this.menbers.size();
    }

    public Menber get(int index) {
        return this.menbers.get(index);
    }
}
