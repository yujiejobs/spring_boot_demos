package com.san.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className TemplatesController
 * @description
 * @date 2019/05/13 21:49
 */
@Controller
@RequestMapping("thymeleaf")
public class TemplatesController {

    @GetMapping(value = "/test")
    public String hello(Model model) {
        String name = "templates test value!!!";
        model.addAttribute("name", name);
        return "thymeleaf";
    }

    /**
     * Model本身不能设置页面跳转的url地址别名或者物理跳转地址，那么我们可以通过控制器方法的返回值来设置跳转url地址别名或者物理跳转地址
     *
     * @param model 一个接口， 其实现类为ExtendedModelMap，继承了ModelMap类
     * @return 跳转url地址别名或者物理跳转地址
     */
    @RequestMapping(value = "/index1")
    public String index1(Model model) {
        model.addAttribute("result", "后台返回index1");
        return "result";
    }

    /**
     * ModelMap对象主要用于传递控制方法处理数据到结果页面,类似于request对象的setAttribute方法的作用。 用法等同于Model
     *
     * @param model
     * @return 跳转url地址别名或者物理跳转地址
     */

    @RequestMapping(value = "/index2")
    public String index2(ModelMap model) {
        model.addAttribute("result", "后台返回index2");
        return "result";
    }

    /**
     * ModelAndView主要有两个作用 设置转向地址和传递控制方法处理结果数据到结果页面
     *
     * @return 返回一个模板视图对象
     */
    @RequestMapping(value = "/index3")
    public ModelAndView index3() {
        ModelAndView mv = new ModelAndView("result");
        mv.addObject("result", "后台返回index3");
        return mv;
    }

    /**
     * map用来存储递控制方法处理结果数据，通过ModelAndView方法返回。
     * 当然Model,ModelMap也可以通过这种方法返回
     *
     * @return 返回一个模板视图对象
     */
    @RequestMapping(value = "/index4")
    public ModelAndView index4() {
        Map<String, String> map = new HashMap<>(10);
        map.put("result", "后台返回index4");
        return new ModelAndView("result", map);
    }
}
