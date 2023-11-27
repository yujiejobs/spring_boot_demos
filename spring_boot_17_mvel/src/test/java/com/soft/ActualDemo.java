package com.soft;

import com.google.common.collect.Maps;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 描述: w("c") && w("b") && w("c")
 *
 * @author yujie
 * @date 2023/11/27 14:16
 */
public class ActualDemo {

    static String next;

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("请输入表达式:");
                Scanner sc = new Scanner(System.in);
                next = sc.nextLine();
                String expression =
                        "import com.soft.T;" + next;

                HashMap<String, Object> context = Maps.newHashMap();
                context.put("context", "这是上下文内容");

                VariableResolverFactory varFactory = new MapVariableResolverFactory();
                ParserContext ctx = new ParserContext();

                Serializable s = MVEL.compileExpression(expression, ctx);
                Object o = MVEL.executeExpression(s, context, varFactory);
                System.out.println("表达式: " + next + "  执行结果:" + o);
            } catch (Exception e) {
                System.out.println("表达式执行失败！" + next);
                e.printStackTrace();
            }
        }

    }

}
