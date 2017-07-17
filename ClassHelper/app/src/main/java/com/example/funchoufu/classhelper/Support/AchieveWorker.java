package com.example.funchoufu.classhelper.Support;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Funchou Fu on 2016/10/23.
 */
public class AchieveWorker {
    public static String AchieveToString(Achieve achieve){
        StringBuilder B=new StringBuilder();
        for(int i=0;i<achieve.size();i++){
            B.append(AchieveLineToString(achieve.get(i)));
        }
        B.append('#');
        return B.toString();
    }
    public static Achieve StringToAchieve(String data){
        Achieve achieve=new Achieve();
        StringBuilder builder=new StringBuilder(data);
        int flag=0;     //记录下前一个#号的下标
        for(int i=1;i<builder.length();i++){
            char c=builder.charAt(i);
            if(c=='#') {
                achieve.addachieveline(new AchieveLine(data.substring(flag,i)));
                flag = i;
            }
        }
        return achieve;
    }
    private static String AchieveLineToString(AchieveLine achieveLine){
        StringBuilder builder=new StringBuilder();
        builder.append('#');
        builder.append(achieveLine.getName());
        builder.append('$');
        builder.append(achieveLine.getNumber());
        Collection ob =achieveLine.getData().entrySet();
        for(Iterator<Map.Entry<String,String>> iterator=ob.iterator();iterator.hasNext();){
            builder.append('*');
            Map.Entry<String,String> m=iterator.next();
            String key=m.getKey();
            builder.append(key);
            builder.append('*');
            String value=m.getValue();
            builder.append(value);
        }
        return builder.toString();
    }

}