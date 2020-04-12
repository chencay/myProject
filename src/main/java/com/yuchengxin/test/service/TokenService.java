package com.yuchengxin.test.service;

import com.yuchengxin.test.mongo.entity.UserInfo;
import org.springframework.stereotype.Component;


public interface TokenService {

    /**
     * 获取token
     * @param userInfo userInfo
     * @return String
     */
    public String getToken(UserInfo userInfo);
}
