package com.example.funchoufu.classhelper.Support;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by Funchou Fu on 2016/10/18.
 */
public class MenberWork {
    SQLiteDatabase db;
    Menbers menbers;

    public MenberWork(SQLiteDatabase db, Menbers menbers) {
        this.db = db;
        this.menbers = menbers;
    }

    public void AddPeopleFromFile(String path, int namecolumn, int numbercolumn, Context context, Bitmap bitmap) {

        Workbook workbook;
        Sheet sheet;
        int i = 0;
        workbook = ReturnExcelBook(path);
        sheet = workbook.getSheet(0);
        this.menbers.RemoveAllMenber();
        TakeDbFile.RemoveAllMenbersFromDatabase(db);

        for (; i < sheet.getRows(); i++) {
            String name="No";
            String number="No";
            boolean flag = true;
            try {
                name = readExcel(sheet, i, namecolumn);
                number = readExcel(sheet, i, numbercolumn);
            } catch (ArrayIndexOutOfBoundsException e) {
                flag=false;
                e.printStackTrace();
            }
            if (flag) {
                int id = TakeDbFile.InputtheseNameIntoDatabase(db, name, number);
                this.menbers.AddMenber(new Menber(name, number, id));
                Takephoto.WriteBitmapIntoFile(context, bitmap, String.valueOf(id));
            }
        }
    }

    public int LongthofExcel(String path) throws IOException, BiffException {
        Workbook workbook;
        Sheet sheet;
        workbook = Workbook.getWorkbook(new File(path));
        sheet = workbook.getSheet(0);
        return sheet.getColumns();
    }

    private Workbook ReturnExcelBook(String path) {
        try {
            Workbook book = Workbook.getWorkbook(new File(path));
            return book;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readExcel(Sheet sheet, int x, int y) {
        String content = "";
        // 得到x行y列所在单元格的内容
        String cellStr = sheet.getRow(x)[y].getContents();
        content = cellStr;
        return content;
    }
}
