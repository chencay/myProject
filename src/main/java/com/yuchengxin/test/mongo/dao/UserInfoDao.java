package com.yuchengxin.test.mongo.dao;


import com.yuchengxin.test.mongo.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoDao extends MongoRepository<UserInfo, String> {

    /**
     * 根据用户名和密码获取 user实体
     * @param username username
     * @param password password
     * @return UserInfo
     */
    UserInfo findByUsernameAndPassword(String username, String password);

    /**
     * 根据id查找用户
     * @param id id
     * @return UserInfo
     */
    UserInfo findUserInfoById(String id);

    /**
     * 根据name查找用户
     * @param username username
     * @return UserInfo
     */
    UserInfo findByUsername(String username);
}
