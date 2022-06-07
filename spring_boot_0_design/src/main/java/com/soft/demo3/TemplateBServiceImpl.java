package com.soft.demo3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateBServiceImpl extends TemplateService {


    @Override
    public String handler(String req) {
        return super.handler(req);
    }


    @Override
    public boolean step() {
        log.info("step B");
        return false;
    }
}
