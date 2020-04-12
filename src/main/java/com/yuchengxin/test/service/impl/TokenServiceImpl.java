package com.yuchengxin.test.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yuchengxin.test.mongo.entity.UserInfo;
import com.yuchengxin.test.service.TokenService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * TokenService继承类
 */
@Component
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(UserInfo userInfo) {
        String token = "";
        if (null == userInfo) {
            return token;
        }
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        token = JWT.create().withAudience(userInfo.getId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(userInfo.getPassword()));
        return token;
    }
}
