package com.example.funchoufu.classhelper.Tab03;

import com.example.funchoufu.classhelper.Support.Achieve;
import com.example.funchoufu.classhelper.Support.AchieveLine;
import com.example.funchoufu.classhelper.Support.Excel;

import java.util.ArrayList;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;

/**
 * Created by Funchou Fu on 2016/10/23.
 */
public class Tab03Work {
    public static Achieve FromExcelMakeAchieve(Sheet sheet) {
        Cell[] firstline= sheet.getRow(0);
        Achieve achieve=new Achieve();
        ArrayList<String> keys=new ArrayList<>();
        for(int i=2;i<firstline.length;i++){
            keys.add(firstline[i].getContents());
        }

        for (int i = 1; i < sheet.getRows(); i++) {
            Cell[] cells = sheet.getRow(i);
            AchieveLine achieveline=new AchieveLine();
            achieveline.setName(cells[0].getContents());
            achieveline.setNumber(cells[1].getContents());
            HashMap<String,String> data=new HashMap<>();
            for (int j = 2; j < cells.length; j++) {
                String buf = cells[j].getContents();
                data.put(keys.get(j-2),buf);
            }
            achieveline.setData(data);
            achieve.addachieveline(achieveline);
        }
        achieve.setName("DEFAULTE");
        return achieve;

    }
    public static int FindLastSlope(String s){
        int locate=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='/'){
                locate=i;
            }
        }
        return locate;
    }
}