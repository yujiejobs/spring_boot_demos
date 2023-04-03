package com.soft;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述: GenerateInvoke
 *
 * @author yujie
 * @date 2022/11/27 16:44
 */
public class GenerateInvoke {

    public static void main(String[] args) throws NoSuchMethodException {
//        Class<OpenAPIFacade> reqService = OpenAPIFacade.class;
//
//        OpenAPIRequest req = new OpenAPIRequest();
//        req.setAreacode(AreacodeEnum.SZ);
//        req.setNsrsbh("91440300MA5FJE845Q");
//        req.setCustomerId("1888359121111");
//
//        Map<String, String> pathValuesMap = Maps.newHashMap();
//        pathValuesMap.put("yhyywdValue", "1");
//        req.setPathValues(pathValuesMap);
////        req.setRequestBody("{'nsrsbh':'91440300MA5FJE845Q','chal':'true'}");
//        req.setChannelId(ChannelEnum.YQDZ.getAppId());
//        String invokeCommand = getInvoke(req, reqService, "yhyywdV2");
//        System.out.println("【invoke命令】:\n");
//        System.out.println(invokeCommand);
//        System.out.println("\n\n");
    }

    /**
     * 生成invoke命令
     *
     * @param reqParam   请求参数
     * @param reqService 请求的接口service服务
     * @param method     请求的service下的方法
     * @throws NoSuchMethodException
     */
    public static String getInvoke(Object reqParam, Class<?> reqService, String method) throws NoSuchMethodException {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(reqParam));
        jsonObject.put("class", reqParam.getClass().getName());
        return "invoke " + reqService.getName() +
                "." + reqService.getMethod(method, reqParam.getClass()).getName() +
                " (" + jsonObject.toJSONString() + ")";
    }
}
