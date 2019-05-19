package com.san.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.excel.excel
 * @description ExcelListener
 * @date 2018-12-21 13:37
 */
@Data
public class ExcelListener extends AnalysisEventListener {

    /**
     * 数据容器
     */
    private List<Object> datas = new ArrayList<>();

    /**
     * 数据容器
     */
    private static final Long DATE_MAX = 200L;

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     *
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        //如数据过大，进行定量分批处理
        if (datas.size() <= DATE_MAX) {
            datas.add(object);
        } else {
            datas = new ArrayList<>();
        }
        System.out.println(datas);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
        //datas.clear();
    }
}