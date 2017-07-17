package com.example.funchoufu.classhelper.Tab03.support_1;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class AchieveLine implements Serializable {
    String name;
    String number;
    HashMap<String,String> data;

    public AchieveLine(String name,String number){
        this.name=name;
        this.number=number;
    }
    public AchieveLine(String name,String number,HashMap<String,String> data){
        this.data=data;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}

