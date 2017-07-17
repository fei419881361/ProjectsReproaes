package com.example.funchoufu.classhelper.Support;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class Menber implements Serializable {

    String name;
    String number;
    int photo;
    public static final int DEFAULTEPHOTO = -66;

    public Menber(Menber menber) {
        this.name = new String(menber.getName());
        this.number = new String(menber.getNumber());
        this.photo = menber.getPhoto();
    }

    public Menber(String name, String number) {
        this.name = name;
        this.number = number;
        this.photo = DEFAULTEPHOTO;
    }

    public Menber(String name, String number, int photo) {
        this.name = name;
        this.number = number;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public boolean equals(Menber menber) {
        if (!this.getName().equals(menber.getName()))
            return false;
        if (!this.getNumber().equals(menber.getNumber()))
            return false;
        if (this.getPhoto() != menber.getPhoto())
            return false;
        return true;
    }
}

