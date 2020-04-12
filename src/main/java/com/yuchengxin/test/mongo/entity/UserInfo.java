package com.yuchengxin.test.mongo.entity;

import org.springframework.data.annotation.Id;


/**
 * 用户信息类
 */
public class UserInfo {

    @Id
    private String id;
    private String username;
    private String password;

    /**
     * getId
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * setId
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getUsername
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUsername
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getPassword
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
