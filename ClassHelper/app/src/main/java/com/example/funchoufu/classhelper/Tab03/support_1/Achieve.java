package com.example.funchoufu.classhelper.Tab03.support_1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class Achieve implements Serializable {
    ArrayList<AchieveLine> achieve;
    String name;
    public Achieve(){
        this.achieve=new ArrayList<AchieveLine>();
    }


    public ArrayList<AchieveLine> getAchieve() {
        return achieve;
    }

    public void setAchieve(ArrayList<AchieveLine> achieve) {
        this.achieve = achieve;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
