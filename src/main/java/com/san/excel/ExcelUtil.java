package com.san.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.excel.excel
 * @description ExcelUtil
 * @date 2018-12-21 13:37
 */
public class ExcelUtil {
    /**
     * 表头行数
     */
    private static int HEAD_LINE_NUM = 1;

    /**
     * 读取 Excel(多个 sheet)
     *
     * @param excel    文件
     * @param clazz 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, Class<? extends BaseRowModel> clazz , ExcelListener listener) {
        ExcelReader reader = getReader(excel, listener);
        if (reader == null) {
            return Lists.newArrayList();
        }
        for (Sheet sheet : reader.getSheets()) {
            if (clazz != null) {
                sheet.setClazz(clazz);
            }
            reader.read(sheet);
        }
        return listener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param clazz 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, Class<? extends BaseRowModel> clazz, int sheetNo, ExcelListener listener) {
        return readExcel(excel, clazz, sheetNo, HEAD_LINE_NUM ,listener);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param clazz    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, Class<? extends BaseRowModel> clazz, int sheetNo, int headLineNum , ExcelListener listener) {
        ExcelReader reader = getReader(excel, listener);
        if (reader == null) {
            return Lists.newArrayList();
        }
        reader.read(new Sheet(sheetNo, headLineNum, clazz));
        return listener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response     HttpServletResponse
     * @param dataList     数据 list，每个元素为一个 BaseRowModel 集合
     * @param columnWidths 列宽 集合
     * @param headNames    表头 集合（可自定义，传null即使用默认模型映射作为表头名）
     * @param fileName     导出的文件名
     * @param sheetName    导入文件的 sheet 名
     * @param clazz       映射实体类，Excel 模型
     * @param afterWriteHandler afterWriteHandler
     */
    public static void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> dataList, int[] columnWidths, List<List<String>> headNames,
                                  String fileName, String sheetName, Class<? extends BaseRowModel> clazz , WriteHandler afterWriteHandler) {

        if(afterWriteHandler == null){
            afterWriteHandler = new AfterWriteHandlerImpl();
        }
        ExcelWriter writer = getMyHandleWriter(fileName, response,afterWriteHandler);

        Sheet sheet = new Sheet(1, HEAD_LINE_NUM, clazz, sheetName, headNames);
        //设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        if (columnWidths != null) {
            sheet.setColumnWidthMap(getColumnWidth(columnWidths));
        }
        writer.write(dataList, sheet);
        writer.finish();
    }


    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param response    HttpServletResponse
     * @param lists       数据 list，每个元素为一个 BaseRowModel
     * @param columnWidth 列宽
     * @param fileName    导出文件的 file 名
     * @param sheetNames  导出文件的 sheet 名
     * @param classes     映射实体类，Excel 模型
     */
    public static void writeExcelWithSheets(HttpServletResponse response, List<? extends BaseRowModel>[] lists, int[] columnWidth,
                                            String fileName, String[] sheetNames, Class[] classes, WriteHandler afterWriteHandler) {
        if(afterWriteHandler == null){
            afterWriteHandler = new AfterWriteHandlerImpl();
        }
        ExcelWriter writer = getMyHandleWriter(fileName, response ,afterWriteHandler);

        for (int i = 0; i < sheetNames.length; i++) {
            Sheet sheet = new Sheet(i + 1, HEAD_LINE_NUM, classes[i], sheetNames[i], null);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            if (columnWidth != null) {
                sheet.setColumnWidthMap(getColumnWidth(columnWidth));
            }
            writer.write(lists[i], sheet);
        }
        writer.finish();
    }

    /**
     * 获取WriteHandler特定ExcelWriter
     * @param fileName fileName
     * @param response response
     * @return
     */
    private static ExcelWriter getMyHandleWriter(String fileName, HttpServletResponse response , WriteHandler writeHandler) {
        setResponse(response);
        //创建本地文件
        String filePath = fileName + ExcelTypeEnum.XLSX.getValue();
        File dbfFile = new File(filePath);
        ServletOutputStream outputStream = null;
        InputStream fis = null;
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                dbfFile.createNewFile();
            }
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "filename=" + fileName);
            outputStream = response.getOutputStream();
            fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        } catch (IOException e) {
            throw new ExcelException("创建文件失败！");
        }finally {
            dbfFile.delete();
        }
        return EasyExcelFactory.getWriterWithTempAndHandler(fis, outputStream, ExcelTypeEnum.XLSX, true, writeHandler);
    }

    /**
     * 设置 列宽
     *
     * @param columnWidth columnWidth
     * @return widthMap
     */
    private static Map getColumnWidth(int[] columnWidth) {
        Map widthMap = Maps.newHashMap();
        for (int i = 0; i < columnWidth.length; i++) {
            widthMap.put(i, columnWidth[i]);
        }
        return widthMap;
    }

    /**
     * 设置 response 响应体
     *
     * @param response response
     */
    private static void setResponse(HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();
        if (filename == null || (!filename.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !filename.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue()))) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = excel.getInputStream();
            return new ExcelReader(inputStream, inputStream, excelListener, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static TableStyle getDefaultStyle() {
        TableStyle style = new TableStyle();
        Font headFont = new Font();
        headFont.setFontHeightInPoints((short) 11);
        headFont.setBold(Boolean.TRUE);
        style.setTableHeadFont(headFont);
        style.setTableHeadBackGroundColor(IndexedColors.GREY_25_PERCENT);

        Font font = new Font();
        font.setFontHeightInPoints((short) 10);
        style.setTableContentFont(font);
        style.setTableContentBackGroundColor(IndexedColors.WHITE1);
        return style;
    }
}
