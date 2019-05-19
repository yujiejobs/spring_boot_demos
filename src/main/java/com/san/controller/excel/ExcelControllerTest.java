package com.san.controller.excel;

import com.google.common.collect.Lists;
import com.san.excel.AfterWriteHandlerImpl;
import com.san.excel.ExcelListener;
import com.san.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yujie
 * @projectName xyl-groupbuy-center
 * @packageName com.xyl.groupbuy
 * @description ali easy excel 使用案例
 * @date 2018-12-25 9:56
 */
@RestController
@Api(value = "ExcelControllerTest", tags = "ali easy excel 使用案例")
public class ExcelControllerTest {

    @ApiOperation(value = "导入 Excel（允许多个 sheet）")
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    public Object importExcel(MultipartFile excel) {
        ExcelListener listener = new ExcelListener();
        return ExcelUtil.readExcel(excel, ImportInfo.class,listener);
    }

    @ApiOperation(value = "导入 Excel（指定某个 sheet）")
    @RequestMapping(value = "importExcelBySheetNo", method = RequestMethod.POST)
    public Object importExcelBySheetNo(MultipartFile excel, int sheetNo) {
        ExcelListener listener = new ExcelListener();
        return ExcelUtil.readExcel(excel, ImportInfo.class, sheetNo ,listener);
    }

    @ApiOperation(value = "导入 Excel（指定某个 sheet  与 headLineNum ）")
    @RequestMapping(value = "importExcelBySheetNoAndHeadLineNum", method = RequestMethod.POST)
    public Object importExcelBySheetNoAndHeadLineNum(MultipartFile excel, int sheetNo, @RequestParam(defaultValue = "1") int headLineNum) {
        ExcelListener listener = new ExcelListener();
        return ExcelUtil.readExcel(excel,ImportInfo.class, sheetNo, headLineNum ,listener);
    }

    @ApiOperation(value = "导出 Excel（一个 sheet）")
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletResponse response) {
        int[] columnWidth = {4000, 4000, 4000, 4000};
        ExcelUtil.writeExcel(response, getTestData(), columnWidth, null, "一个 Excel 文件", "第一个 sheet", ExportInfo.class ,null);
    }

    @ApiOperation(value = "导出 Excel(多个sheet，多个Model)")
    @RequestMapping(value = "exportExcelWithSheets", method = RequestMethod.GET)
    public void exportExcelWithSheets(HttpServletResponse response) {
        List<ExportInfo>[] lists = new List[]{getTestData(), getTestData(), getTestData()};
        String[] sheetNames = {"第一个 sheet", "第二个 sheet", "第三个 sheet"};
        Class[] classes = {ExportInfo.class, ExportInfo.class, ExportInfo.class};
        int[] columnWidth = {4000, 4000, 40000};
        ExcelUtil.writeExcelWithSheets(response, lists, columnWidth, "导出 Excel（多个 sheet）", sheetNames, classes ,null);
    }


    @ApiOperation(value = "导出 Excel,自定义表头")
    @RequestMapping(value = "exportExcelSetHead", method = RequestMethod.GET)
    public void exportExcelSetHead(HttpServletResponse response) {
        int[] columnWidth = {4000, 4000, 4000, 4000};
        ExcelUtil.writeExcel(response, getTestData(), columnWidth, createTestListHead(), "一个 Excel 文件", "第一个 sheet", ExportInfo.class,new AfterWriteHandlerImpl());
    }

    /**
     * 获取模拟多条数据
     *
     * @return 模拟数据
     */
    private List<ExportInfo> getTestData() {
        List<ExportInfo> list = new ArrayList<>();
        ExportInfo model1 = new ExportInfo();
        model1.setName("howie");
        model1.setAge("19");
        model1.setAddress("123456789");
        model1.setEmail("123456789@gmail.com");
        list.add(model1);
        ExportInfo model2 = new ExportInfo();
        model2.setName("harry");
        model2.setAge("20");
        model2.setAddress("1987522331");
        model2.setEmail("198752233@gmail.com");
        list.add(model2);
        for (int i = 0; i < 50; i++) {
            list.add(model2);
        }
        return list;
    }

    /**
     * 模拟自定义表头数据
     *
     * @return head
     */
    public static List<List<String>> createTestListHead() {
        //写sheet3  模型上没有注解，表头数据动态传入
        List<List<String>> head = Lists.newArrayList();
        List<String> headColumn1 = Lists.newArrayList();
        List<String> headColumn2 = Lists.newArrayList();
        List<String> headColumn3 = Lists.newArrayList();
        List<String> headColumn4 = Lists.newArrayList();
        List<String> headColumn5 = Lists.newArrayList();

        headColumn1.add("第一列");
        headColumn1.add("第一列");
        headColumn1.add("第一列");
        headColumn2.add("第一列");
        headColumn2.add("第一列");
        headColumn2.add("第一列");

        headColumn3.add("第二列");
        headColumn3.add("第二列");
        headColumn3.add("第二列");

        headColumn4.add("第三列");

        headColumn4.add("第三列2");
        headColumn4.add("第三列2");

        headColumn5.add("第一列");

        headColumn5.add("第3列");
        headColumn5.add("第4列");

        head.add(headColumn1);
        head.add(headColumn2);
        head.add(headColumn3);
        head.add(headColumn4);
        head.add(headColumn5);
        return head;
    }
}
