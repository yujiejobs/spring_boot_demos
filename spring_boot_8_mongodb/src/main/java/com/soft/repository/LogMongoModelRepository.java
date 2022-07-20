package com.soft.repository;


import com.soft.model.LogMongoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 描述: LogMongoModelRepository
 *
 * @author yujie
 * @date 2022/6/15 10:31
 */
public interface LogMongoModelRepository extends MongoRepository<LogMongoModel, Long> {

    /**
     * findAllByContextLike
     *
     * @param context context
     * @return List<LogMongoModel>
     */
    List<LogMongoModel> findAllByContextLike(String context);

}
