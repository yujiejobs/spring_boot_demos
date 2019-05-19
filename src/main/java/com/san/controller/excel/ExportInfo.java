package com.san.controller.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author yujie
 * @projectName xyl-groupbuy-center
 * @packageName com.xyl.groupbuy
 * @description ExportInfo
 * @date 2018-12-25 9:59
 */
@Data
public class ExportInfo extends BaseRowModel {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "年龄", index = 1)
    private String age;

    @ExcelProperty(value = "邮箱", index = 2)
    private String email;

    @ExcelProperty(value = "地址", index = 3)
    private String address;
}
