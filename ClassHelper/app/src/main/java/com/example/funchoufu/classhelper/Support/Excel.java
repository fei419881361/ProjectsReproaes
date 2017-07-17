package com.example.funchoufu.classhelper.Support;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by Funchou Fu on 2016/10/23.
 */
public class Excel {
    public static int LongthofExcel(String path) throws IOException, BiffException {
        Workbook workbook;
        Sheet sheet;
        workbook = Workbook.getWorkbook(new File(path));
        sheet = workbook.getSheet(0);
        return sheet.getColumns();
    }

    public static Workbook ReturnExcelBook(String path) {
        try {
            Workbook book = Workbook.getWorkbook(new File(path));
            System.out.println("DEBUGNO"+book);
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("DEBUGIO");
        } catch (BiffException e) {
            e.printStackTrace();
            System.out.println("DEBUGBIFF");
        }
        return null;
    }

    public static String readExcel(Sheet sheet, int x, int y) {
        String content = "";
        // 得到x行y列所在单元格的内容
        String cellStr = sheet.getRow(x)[y].getContents();
        content = cellStr;
        return content;
    }
}
