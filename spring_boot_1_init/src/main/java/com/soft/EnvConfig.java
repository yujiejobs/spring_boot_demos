package com.soft;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述: MqEnvConfig
 *
 * @author yujie
 * @date 2023/8/14/014 17:53
 */
@Component
public class EnvConfig {

    public static String envGroup;

    public static String envGroupSwitchForMq;

    @Value("${dubbo.group:}")
    public void setEnvGroup(String envGroup) {
        EnvConfig.envGroup = envGroup;
    }

    @Value("${envGroupSwitchForMq:}")
    public void setEnvGroupSwitchForMq(String envGroupSwitchForMq) {
        EnvConfig.envGroupSwitchForMq = envGroupSwitchForMq;
    }

    /**
     * 判断是否开启测试环境的MQ消费限制：根据环境进行消费
     *
     * @param env env
     * @return Boolean 是否限制，什么环境发什么环境收
     */
    public static Boolean envGroupSwitchForMq(String env) {

        if ("Y".equals(envGroupSwitchForMq) && EnvConfig.envGroup.equals(env)) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}
