package com.soft;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.soft.model.LogMongoModel;
import com.soft.repository.LogMongoModelRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class Start8Tests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LogMongoModelRepository logMongoModelRepository;

    @Test
    void saveByLogFile() {
        // 从日志文件写入MongDb中
        List<String> fileLineList = FileUtil.readLines(new File("./logtest.log"), Charset.defaultCharset());
        List<LogMongoModel> logMongoModelList = Lists.newArrayList();
        fileLineList.forEach(log -> {
            LogMongoModel logMongoModel = new LogMongoModel();
            String time = log.substring(0, 24);
            DateTime dateTime = DateUtil.parseDateTime(time);
            logMongoModel.setCreateTime(dateTime);
            logMongoModel.setId(IdUtil.getSnowflakeNextId());
            logMongoModel.setContext(log);
            logMongoModelList.add(logMongoModel);
        });
        logMongoModelRepository.saveAll(logMongoModelList);
        log.info("查询到的参数 fileLineList {} ", JSONUtil.toJsonStr(fileLineList));
    }

    @Test
    void mongoTemplate() {
        LogMongoModel template = mongoTemplate.findById(1536948750163644419L, LogMongoModel.class);
        log.info("查询到的参数 template {} ", JSONUtil.toJsonStr(template));
    }

    @Test
    void queryLogById() {
        // 演示简单查询
        LogMongoModel search = new LogMongoModel();
        search.setId(1536948750163644419L);
        List<LogMongoModel> fileLineList = logMongoModelRepository.findAll(Example.of(search),Sort.by("createTime").descending());
        log.info("查询到的参数 fileLineList {} ", JSONUtil.toJsonStr(fileLineList));
    }

    @Test
    void queryLogByTraceIdLike() {
        // 模糊查询 - 根据traceId查询链路
        List<LogMongoModel> fileLineList = logMongoModelRepository.findAllByContextLike("10767304951005952_0");
        fileLineList.forEach(log -> {
            Console.log(log.getContext());
        });
    }

    @Test
    void findById() {
        Optional<LogMongoModel> opt = logMongoModelRepository.findById(1L);
        if(opt.isPresent()){
            LogMongoModel logMongoModel = opt.get();
            log.info("查询到的参数 {} ", JSONUtil.toJsonStr(logMongoModel));
        }else {
            log.info("查询数据为空！");
        }
    }

}
