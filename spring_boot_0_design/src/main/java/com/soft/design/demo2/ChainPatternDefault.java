package com.soft.design.demo2;

import com.soft.design.demo2.handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 描述: 责任链
 *
 * @author yujie
 * @date 2022/6/1 14:24
 */
@Slf4j
@Component
public class ChainPatternDefault {


    @Autowired
    private List<AbstractHandler> abstractHandleList;

    private AbstractHandler abstractHandler;

    /**
     * spring注入后自动执行，责任链的对象连接起来
     */
    @PostConstruct
    public void initializeChainFilter() {
        for (int i = 0; i < abstractHandleList.size(); i++) {
            if (i == 0) {
                abstractHandler = abstractHandleList.get(0);
            } else {
                AbstractHandler current = abstractHandleList.get(i - 1);
                AbstractHandler next = abstractHandleList.get(i);
                current.setNextHandler(next);
            }
        }
        StringBuilder logStr = new StringBuilder();
        abstractHandleList.forEach(h -> logStr.append(h.getClass()).append(" "));
        log.info("当前执行顺序：{}", logStr);
    }

    /**
     * 方法执行
     *
     * @param request  request
     * @param response response
     * @return String
     */
    public String execDefault(String request, String response) {
        abstractHandler.filter(request, response);
        return response;
    }

    public AbstractHandler getAbstractHandler() {
        return abstractHandler;
    }

    public void setAbstractHandler(AbstractHandler abstractHandler) {
        this.abstractHandler = abstractHandler;
    }
}
