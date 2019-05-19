package com.san.controller.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author yujie
 * @projectName xyl-groupbuy-center
 * @packageName com.xyl.groupbuy
 * @description ImportInfo
 * @date 2018-12-25 9:59
 */
@Data
public class ImportInfo extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String name;

    @ExcelProperty(index = 1)
    private String age;

    @ExcelProperty(index = 2)
    private String email;
}
