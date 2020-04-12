package com.yuchengxin.test.mongo.service.impl;

import com.yuchengxin.test.mongo.dao.UserInfoDao;
import com.yuchengxin.test.mongo.entity.UserInfo;
import com.yuchengxin.test.mongo.service.UserInfoService;
import com.yuchengxin.test.utils.MemoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoServiceImpl implements UserInfoService {

    /** UserInfoDao **/
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfoByNameAndPassword(String username, String password) {
        if (null == username || null == password ||
                MemoryUtil.EMPTY_STR.equals(username) || MemoryUtil.EMPTY_STR.equals(password)) {
            return null;
        }
        return userInfoDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        if (null == userInfo) {
            return null;
        }
        return userInfoDao.save(userInfo);
    }

    @Override
    public UserInfo getUserInfoById(String id) {
        if (null == id) {
            return null;
        }
        return userInfoDao.findUserInfoById(id);
    }

    @Override
    public UserInfo getUserInfoByName(String name) {
        if (null == name) {
            return null;
        }
        return userInfoDao.findByUsername(name);
    }
}
