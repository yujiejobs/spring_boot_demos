package com.soft;

import com.soft.model.LogMongoModel;
import com.soft.repository.LogMongoModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: SpringBoot初始脚手架
 *
 * @author yujie
 * @date 2022/1/5 20:01
 */
@SpringBootApplication
@RestController
public class Start8 {

    public static void main(String[] args) {
        SpringApplication.run(Start8.class, args);
    }

    @Autowired
    private LogMongoModelRepository logMongoModelRepository;

    @GetMapping("log")
    public List<String> log(String traceId) {
        // 模糊查询 - 根据 traceId 查询链路
        List<LogMongoModel> fileLineList = logMongoModelRepository.findAllByContextLike(traceId);
        return fileLineList.stream().map(LogMongoModel::getContext).collect(Collectors.toList());
    }
}
