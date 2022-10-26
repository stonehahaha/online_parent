package com.stone.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stonestart
 * @create 2022/8/28 - 21:30
 */
public class TestEasyExcel {
    public static void main(String[] args) {
//        String fileName = "D:\\write.xlsx";
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
        String fileName = "D:\\write.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("stone" + i);
            list.add(data);
        }
        return list;
    }
}
