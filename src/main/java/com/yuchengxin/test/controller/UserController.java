package com.yuchengxin.test.controller;

import com.yuchengxin.test.config.PassToken;
import com.yuchengxin.test.config.UserLoginToken;
import com.yuchengxin.test.mongo.entity.UserInfo;
import com.yuchengxin.test.mongo.service.UserInfoService;
import com.yuchengxin.test.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 与用户相关的Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /** success **/
    private static final String SUCCESS = "success";
    /** fail **/
    private static final String FAIL = "fail";
    /**UserInfoService**/
    @Autowired
    private UserInfoService userInfoService;
    /**TokenService**/
    @Autowired
    private TokenService tokenService;

    @PassToken
    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public Map<String, String> login(@RequestParam("username") String username
            , @RequestParam("password") String password) {
        Map<String, String> map = new HashMap<>();
        if (null == username || null == password) {
            return map;
        }
        UserInfo userInfoForBase =userInfoService.getUserInfoByNameAndPassword(
                username, password);
        if (null == userInfoForBase) {
            map.put(FAIL, "用户名或者密码错误");
            return map;
        }
        String token = tokenService.getToken(userInfoForBase);
        map.put("token", token);
        return map;
    }

    @RequestMapping(value = "/register", method = POST)
    @ResponseBody
    public Map<String,Object> register(@RequestParam("username") String username
            , @RequestParam("password") String password){
        Map<String, Object> map = new HashMap<>();
        if (null == username || null == password) {
            map.put(FAIL, "用户名或者密码为空！");
            return map;
        }
        UserInfo oldUserInfo = userInfoService.getUserInfoByName(username);
        if (null != oldUserInfo) {
            map.put(FAIL, "用户名已存在！");
            return map;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(password);
        userInfo.setUsername(username);
        UserInfo newUserInfo = userInfoService.save(userInfo);
        if (null != newUserInfo) {
            map.put(SUCCESS, newUserInfo);
        } else {
            map.put(FAIL, "注册失败！");
        }
        return map;
    }

    /*测试token  不登录没有token*/
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}
