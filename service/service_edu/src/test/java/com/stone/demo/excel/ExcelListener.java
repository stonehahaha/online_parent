package com.stone.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

/**
 * @author stonestart
 * @create 2022/8/28 - 22:08
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("****" + data);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头:" + headMap);

    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
