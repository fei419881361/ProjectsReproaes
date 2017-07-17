package com.example.funchoufu.classhelper.Support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Funchou Fu on 2016/10/7.
 */
public class Achieve implements Serializable {
    ArrayList<AchieveLine> achieve;
    String name;
    int id;
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

    public int size(){
        return achieve.size();
    }

    public AchieveLine get(int index){
        return achieve.get(index);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addachieveline(AchieveLine achieveLine){
        this.achieve.add(achieveLine);
    }

    public ArrayList<String> lookThrough(){
        ArrayList<String> R=new ArrayList<>();
        Collection ob=achieve.get(0).getData().entrySet();
        for(Iterator<Map<String,String>> iterator=ob.iterator();iterator.hasNext();){
            Map.Entry<String,String> m= (Map.Entry<String, String>) iterator.next();
            R.add(m.getKey());
        }
        return R;
    }

    public String getitemname(int index){
        Collection ob=achieve.get(0).getData().entrySet();
        Iterator<Map<String,String>> iterator=ob.iterator();
        String R="null";
        int i=0;
        for(;i<index+1;i++){
            Map.Entry<String,String> m= (Map.Entry<String, String>) iterator.next();
            R=m.getKey();
        }
        return R;
    }

    public String getitemvalue(String itemnumber,String key){
        AchieveLine e;
        for(int i=0;i<achieve.size();i++){
            e=achieve.get(i);
            if(e.getNumber().equals(itemnumber)){
                String debug=e.getData().get(key);
                System.out.println("DEBUG"+debug);
                return debug;
            }
        }
        return "0";
    }


    public void DEBUG(){
        for(int i=0;i<achieve.size();i++){
            AchieveLine a=achieve.get(i);
            System.out.println(a.getName()+" "+a.getNumber());
            Collection ob= (Collection) a.getData().entrySet();
            for(Iterator<Map.Entry<String,String>> iterator=ob.iterator();iterator.hasNext();){
                Map.Entry m=iterator.next();
                System.out.println(m.getKey()+" "+m.getValue());
            }
        }
    }
}
