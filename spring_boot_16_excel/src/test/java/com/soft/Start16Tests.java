package com.soft;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Start16Tests {

    @Test
    void contextLoads() {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("D:\\操作列按钮-orgin.xlsx"));
        List<List<Object>> read = reader.read();
        System.out.println("end");
    }

}
