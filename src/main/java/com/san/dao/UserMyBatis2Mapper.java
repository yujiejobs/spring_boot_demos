package com.san.dao;

import com.san.domain.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.mapper
 * @className UserMyBatisMapper
 * @description
 * @date 2019/05/14 0:38
 */
@Mapper
public interface UserMyBatis2Mapper {
    /**
     * getAll
     *
     * @return
     */
    @Select("SELECT * FROM user")
    @Results(id = "userMap",value = {
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "user_name")
    })
    List<UserEntity> getAll();

    /**
     * getOne
     *
     * @param id
     * @return
     */
    @ResultMap("userMap")
    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity getOne(@Param("id") Long id);

    /**
     * insert
     *
     * @param user
     */
    @Insert("INSERT INTO user(user_name,pass_word,email) VALUES(#{user.userName}, #{user.passWord},#{user.email})")
    void insert(@Param("user") UserEntity user);

    /**
     * update
     *
     * @param user
     */
    @Update("UPDATE user SET user_name=#{user.userName} WHERE id =#{user.id}")
    void update(@Param("user") UserEntity user);

    /**
     * delete
     *
     * @param id
     */
    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(@Param("id") Long id);


}
