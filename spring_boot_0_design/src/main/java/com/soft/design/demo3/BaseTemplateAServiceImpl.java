package com.soft.design.demo3;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BaseTemplateAServiceImpl extends BaseTemplateService {

    @Override
    public String handler(String req) {
        return super.handler(req);
    }

    @Override
    public boolean step() {
        log.info("step A");
        return false;
    }

    public static void main(String[] args) {
        List<Integer> prizeIdDtoList = Lists.newArrayList(5, 6);
        // 若编辑判断交集
        boolean update = Boolean.TRUE;
        List<Integer> prizeIdDbList = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> existList = prizeIdDbList.stream().filter(prizeIdDtoList::contains).collect(Collectors.toList());
        prizeIdDtoList.addAll(existList);

        int size = prizeIdDtoList.size();
        System.out.println(size);
    }

}
