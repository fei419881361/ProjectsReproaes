package com.example.funchoufu.classhelper.Tab02;

import com.example.funchoufu.classhelper.Support.Menber;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Funchou Fu on 2016/10/18.
 */
public class Print {
    File file;
    int year;
    int month;
    int day;
    int hour;
    int minutes;
    public Print(File file){
        this.file=file;
    }
    public static int WhichModule(int H){
        if(H>=8&&H<10)
            return 1;
        else if (H>=10&&H<12)
            return 3;
        else if (H>=14&&H<16)
            return 6;
        else if (H>=16&&H<18)
            return 8;
        else if (H>=18&&H<20)
            return 10;
        else
            return -1;
    }
    public void ExplodeTxt(ArrayList<Menber> list) throws IOException {
        file.createNewFile();
        Calendar c=Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        hour=c.get(Calendar.HOUR_OF_DAY);
        minutes=c.get(Calendar.MINUTE);
        IsnewYear();
        FileOutputStream fos=new FileOutputStream(file);
        OutputStreamWriter writer=new OutputStreamWriter(fos);
        writer.write(year+"/"+month+"/"+day+"\t"+"\n"+hour+":"+minutes+"\n");
        writer.write("迟到列表如下:\n");
        for(int i=0;i<list.size();i++){
            writer.write(String.format("\t姓名:%s\t学号:%s\n",list.get(i).getName(),list.get(i).getNumber()));
        }
        writer.flush();
        writer.close();
        fos.close();
    }
    private void IsnewYear(){
        if(month==12) {
            month = 1;
            year+=1;
        }
        else
            month+=1;
    }
}
