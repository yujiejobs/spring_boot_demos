package com.soft.design.demo2.handler;

/**
 * 描述: 责任链
 *
 * @author yujie
 * @date 2022/6/1 14:08
 */
public abstract class AbstractHandler {

    /**
     * 责任链对象
     */
    private AbstractHandler nextHandler;

    /**
     * 链路处理
     */
    public void filter(String request, String response) {
        doFilter(request, response);
        if (getNextHandler() != null) {
            getNextHandler().filter(request, response);
        }
    }

    /**
     * 处理逻辑-子类去实现
     *
     * @param request  request
     * @param response response
     */
    abstract void doFilter(String request, String response);

    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


}

