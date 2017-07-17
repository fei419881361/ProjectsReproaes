package com.example.funchoufu.classhelper.Support;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class AchieveLine implements Serializable {
    String name;
    String number;
    HashMap<String, String> data=new HashMap<String, String>();

    public AchieveLine() {}

    public AchieveLine(String name, String number) {
        this.name = name;
        this.number = number;
        data = new HashMap<String, String>();
    }

    //此处的data #Name$Number*key1*value1
    public AchieveLine(String data) {
        int flag = 0;
        String buf;
        data += '*';
        for (int i = 1; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == '$') {
                buf = data.substring(flag+1, i);
                this.setName(buf);
                flag=i;
            }
            if (c == '*') {
                buf = data.substring(flag+1, i);
                if (this.number == null) {
                    this.setNumber(buf);
                    flag=i;
                }
                else {
                    String key = data.substring(flag+1, i);
                    for (int j = i + 1; j < data.length(); j++) {
                        if (data.charAt(j) == '*') {
                            String value = data.substring(i+1, j);
                            this.getData().put(key,value);
                            i=j;
                            break;
                        }
                    }
                    flag = i;
                }
            }
        }
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

