package com.soft;

import cn.hutool.core.lang.Console;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


@SpringBootTest
class Start1Tests {


    @Test
    void contextLoads() {


    }

    public static void main(String[] args) {
        test0();
//        test1();
//        test2();
    }

    private static void test0() {
        String expression =
                "import com.soft.*;" +
                        "this.setName(\"内部名称：修改\");" +
                        "this.demoInner.setName(\"内部对象：修改\");" +
                        "this.demoInner = new Demo(\"内部对象二次赋值\");" +
                        "return this;";

        Demo context = new Demo();
        context.setName("外部名称");
        context.setDemoInner(new Demo("外部对象"));

        VariableResolverFactory varFactory = new MapVariableResolverFactory();
        ParserContext ctx = new ParserContext();

        Serializable s = MVEL.compileExpression(expression, ctx);
        MVEL.executeExpression(s, context, varFactory);
        System.out.println(context);
    }

    private static void test2() {
        Serializable expression = MVEL.compileExpression(
                "import com.soft.*;" +
                        "context.setName(\"内部名称：修改\");" +
                        "context.demoInner.setName(\"内部对象：修改\");" +
                        "context.demoInner = new Demo(\"内部对象二次赋值\");" +
                        "strContext = \"内部字符串内部：修改\";" +
                        "list.forEach(System.out::println);" +
                        "return this;");

        HashMap<String, Object> mvelContext = new HashMap<>(10);
        ArrayList<Integer> list = Lists.newArrayList(1, 3, 2);
        ArrayList<Integer> list1 = Lists.newArrayList(1);


        Demo context = new Demo();
        context.setName("外部名称");
        context.setDemoInner(new Demo("外部对象"));

        mvelContext.put("list", list);
        mvelContext.put("list1", list1);
        mvelContext.put("context", context);
        mvelContext.put("strContext", "字符串外部对象");

        Object eval = MVEL.executeExpression(expression, mvelContext);

        Console.log("context:", mvelContext);
        Console.log("eval:", eval);
        Console.log("list:", list);
    }

    private static void test1() {
        Serializable expression = MVEL.compileExpression(
                "import com.soft.*;" +
                        "this.setName(\"内部名称：修改\");" +
                        "this.demoInner.setName(\"内部对象：修改\");" +
                        "this.demoInner = new Demo(\"内部对象二次赋值\");" +
                        "return this;");

        Demo context = new Demo();
        context.setName("外部名称");
        context.setDemoInner(new Demo("外部对象"));

        Object eval = MVEL.executeExpression(expression, context);

        Console.log("context:", context);
        Console.log("eval:", eval);
    }

}
