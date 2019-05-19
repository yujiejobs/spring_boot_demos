package com.san.excel;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.ss.usermodel.*;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.excel.excel
 * @description 写处理程序后Impl，本类为公共样式，需自定义只需实现WriteHandler接口
 * @date 2019-02-28 10:40
 */
public class AfterWriteHandlerImpl implements WriteHandler {

    CellStyle cellStyle;
    Font font;

    @Override
    public void sheet(int sheetNo, Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();
        cellStyle = workbook.createCellStyle();
        font = workbook.createFont();
        //创建样式
        font.setFontHeightInPoints((short) 10);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setFont(font);
    }

    @Override
    public void row(int rowNum, Row row) {
        //设置行高
        row.setHeight((short)400);
        //设置首行行高
        if(rowNum == 0){
            row.setHeight((short)500);
        }
    }

    @Override
    public void cell(int cellNum, Cell cell) {
        //设置样式 注意：样式最好采用公用样式，样式在创建sheet后创建，如果有多个样式也需要在创建sheet时候创建后面直接使用，不要每个Cell Create 一个样式，不然会导致报错 The maximum number
        if(cell.getRowIndex() > 0){
            cell.setCellStyle(cellStyle);
        }
    }
}
