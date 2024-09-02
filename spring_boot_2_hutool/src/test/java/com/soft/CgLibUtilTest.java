package com.soft;

import cn.hutool.extra.cglib.CglibUtil;
import org.springframework.beans.BeanUtils;

public class CgLibUtilTest {

    public static void main(String[] args) {
        DemoBean d1 = new DemoBean("test1");
        d1.setDemoBeanInner(new DemoBean.DemoBeanInner("inner1"));

        DemoBean d2 = CglibUtil.copy(d1, DemoBean.class);
        d2.setName("d2");
        d2.getDemoBeanInner().setName("d2-inner");
        System.out.println(d2);

        DemoBean clone = new DemoBean();
        BeanUtils.copyProperties(d1, clone);
        clone.setName("clone");
        clone.getDemoBeanInner().setName("clone-inner");

        System.out.println(clone);
        System.out.println(d1);
    }
}
