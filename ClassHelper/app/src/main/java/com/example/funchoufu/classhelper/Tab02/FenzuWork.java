package com.example.funchoufu.classhelper.Tab02;

import android.app.Activity;
import android.content.Intent;

import com.example.funchoufu.classhelper.Support.Achieve;
import com.example.funchoufu.classhelper.Support.Achieves;
import com.example.funchoufu.classhelper.Support.Menber;
import com.example.funchoufu.classhelper.Support.Menbers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Funchou Fu on 2016/10/25.
 */
public class FenzuWork  {
    ArrayList<Menber> menbers;
    Achieves achieves;
    Activity activity;
    int grouplength;
    ArrayList<Menber>[] groups;
    public FenzuWork(ArrayList<Menber> menbers,Achieves achieves,Activity activity,int grouplength){
        this.menbers=menbers;
        this.achieves=achieves;
        this.activity=activity;
        this.grouplength=grouplength;
        this.groups=new ArrayList[grouplength];//尚未实例化
        for(int i=0;i<grouplength;i++){
            this.groups[i]=new ArrayList<>();
        }
    }
    public int divideGroupwithNumber(){
        if(menbers.size()<=grouplength){
            int i;
            for(i=0;i<menbers.size();i++){
                groups[i].add(menbers.get(i));
            }
            return i;
        }
        else{
            int moreposition;
            int morenumber;
            moreposition=menbers.size()%grouplength;
            morenumber=menbers.size()/grouplength+1;
            for(int i=0;i<moreposition;i++){
                for(int j=0;j<morenumber;j++){
                    groups[i].add(menbers.get(0));
                    menbers.remove(0);
                }
            }
            for(int i=moreposition;i<grouplength;i++){
                for(int j=0;j<morenumber-1;j++){
                    groups[i].add(menbers.get(0));
                    menbers.remove(0);
                }
            }
        }
        return grouplength;
    }

    public int divideGroupwithRandom(){
        //返还实际的长度.
        if(menbers.size()<=grouplength){
            int i;
            for(i=0;i<menbers.size();i++){
                groups[i].add(menbers.get(i));
            }
            return i;
        }
        else{
            int moreposition;
            int morenumber;

            moreposition=menbers.size()%grouplength;
            morenumber=menbers.size()/grouplength+1;

            for(int i=0;i<moreposition;i++){

                for(int j=0;j<morenumber;j++){
                    int removerandom= (int) (Math.random()*6541%menbers.size());
                    groups[i].add(menbers.get(removerandom));
                    menbers.remove(removerandom);
                }
            }
            for(int i=moreposition;i<grouplength;i++){
                for(int j=0;j<morenumber-1;j++){
                    int removerandom= (int) (Math.random()*6541%menbers.size());
                    groups[i].add(menbers.get(removerandom));
                    menbers.remove(removerandom);
                }
            }
        }
        return grouplength;
    }

    public int divideGroupwithSelf(ArrayList<String> father,ArrayList<String> son,ArrayList<String> power){
        Scores scores=new Scores();
        ArrayList<Achieve> achievelist=new ArrayList<>();
        for(int i=0;i<father.size();i++){
            achievelist.add(achieves.getachieve(father.get(i)));
        }
        //对添加进来的achieve 进行 一次添加
        for(int i=0;i<menbers.size();i++){
            Menber menber=menbers.get(i);
            double sum=0;
            Score score=new Score(menbers.get(i));
            for(int j=0;j<achievelist.size();j++){
                Achieve achieve=achievelist.get(j);
                String number=menber.getNumber();
                String sun=son.get(j);
                double va1=Double.valueOf(achieve.getitemvalue(number, sun));
                double va2=Double.valueOf(power.get(j));
                sum+= va1*va2;
            }
            score.setSum(sum);
            scores.put(score);
        }
        //DEBUG
        scores.Order();
        for(int i=0;i<scores.size();i++){
            System.out.println("DEBUG"+scores.getMenber(i).getName()+scores.get(i).getSum());
        }
        int moreposition;
        int morenumber;
        moreposition=menbers.size()%grouplength;
        morenumber=menbers.size()/grouplength+1;
        for(int i=0;i<moreposition;i++){
            for(int j=0;j<morenumber;j++){
                groups[i].add(scores.push());
            }
        }
        for(int i=moreposition;i<grouplength;i++){
            for(int j=0;j<morenumber-1;j++){
                groups[i].add(scores.push());
            }
        }

        return grouplength;
    }

    public ArrayList<Menber>[] getGroups() {
        return groups;
    }

    private class Score implements Comparable{
        Menber menber;
        double sum;

        public Score(Menber menber){
            this.menber=menber;
            sum=0;
        }

        public double getSum() {
            return sum;
        }

        public Menber getMenber() {
            return menber;
        }


        public void setSum(double sum) {
            this.sum = sum;
        }

        @Override
        public int compareTo(Object o) {
            return (int) (((Score)o).sum-this.sum);
        }
    }
    private class Scores{
        ArrayList<Score> list;
        public Scores(){
            list=new ArrayList<>();
        }
        public void put(Score score){
            list.add(score);
        }
        Menber getMenber(int index){
            return this.list.get(index).getMenber();
        }
        Score get(int index){
            return this.list.get(index);
        }
        Menber push(){
            Menber menber= this.list.get(0).getMenber();
            this.list.remove(0);
            return menber;
        }
        public int size(){
            return this.list.size();
        }

        public void Order(){
            Collections.sort(this.list);
        }
    }

}
