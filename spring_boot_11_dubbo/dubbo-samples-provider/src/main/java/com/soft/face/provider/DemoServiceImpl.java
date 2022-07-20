/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.soft.face.provider;


import com.soft.face.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * dubbo 接口实现方法 - 生产者
 *
 * @author yujie
 */
@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("我是生产者：Hello " + name + ", 该消息来自消费者: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + "该消息来自生产者返回";
    }


}
