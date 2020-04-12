package com.yuchengxin.test.mongo.service;

import com.yuchengxin.test.mongo.entity.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * UserInfo的服务类
 */
public interface UserInfoService {

    /**
     * 根据 username和password 获取User
     * @param username username
     * @param password password
     * @return UserInfo
     */
    UserInfo getUserInfoByNameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param userInfo userInfo
     * @return UserInfo
     */
    UserInfo save(UserInfo userInfo);

    /**
     * 根据id获取用户信息
     * @param id id
     * @return UserInfo
     */
    UserInfo getUserInfoById(String id);

    /**
     * 根据name获取用户信息
     * @param name name
     * @return UserInfo
     */
    UserInfo getUserInfoByName(String name);
}
