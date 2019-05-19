package com.san.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.excel.excel
 * @description ExcelWriterFactory
 * @date 2018-12-21 13:37
 */
public class ExcelWriterFactory extends ExcelWriter {

    private OutputStream outputStream;

    /**
     * sheetNo
     */
    private int sheetNo = 1;

    /**
     * ExcelWriterFactory
     *
     * @param outputStream
     * @param typeEnum
     */
    public ExcelWriterFactory(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        super(outputStream, typeEnum);
        this.outputStream = outputStream;
    }

    /**
     * ExcelWriterFactory
     *
     * @param list
     * @param sheetName
     * @param object
     * @return
     */
    public ExcelWriterFactory write(List<? extends BaseRowModel> list, String sheetName,
                                    BaseRowModel object) {
        this.sheetNo++;
        try {
            Sheet sheet = new Sheet(sheetNo, 0, object.getClass());
            sheet.setSheetName(sheetName);
            this.write(list, sheet);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * finish
     */
    @Override
    public void finish() {
        super.finish();
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
