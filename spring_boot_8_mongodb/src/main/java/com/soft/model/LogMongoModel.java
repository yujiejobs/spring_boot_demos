package com.soft.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogMongoModel {

    /**
     * 文章id
     */
    @Id
    private Long id;

    /**
     * ip
     */
    private String ip;

    /**
     * 日志内容
     */
    private String context;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上游微服务节点名称
     */
    private String preApp;


    /**
     * 上游微服务的Host Name
     */
    private String preHost;


    /**
     * 上游微服务的IP地址
     */
    private String preIp;

    /**
     * 链路spanId，具体规则可以参照八.SpanId生成规则
     */
    private String spanId;


    /**
     * 全局唯一跟踪ID
     */
    private String traceId;

}
