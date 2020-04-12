package com.yuchengxin.test.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yuchengxin.test.mongo.entity.UserInfo;
import com.yuchengxin.test.mongo.service.UserInfoService;
import com.yuchengxin.test.utils.MemoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author ：ycx
 * @version ：1.0
 * @date ：2020/3/25 21:43
 * @description ：拦截器去获取token并验证token
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    /** UserInfoService **/
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        httpServletResponse.setCharacterEncoding("utf-8");
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (null == token) {
                    String msg = "无token，请重新登录";
                    setResponseInfo(httpServletResponse, msg);
                    return false;
                }
                // 获取 token 中的 user id
                String userId = MemoryUtil.EMPTY_STR;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    String msg = "用户或密码错误";
                    setResponseInfo(httpServletResponse, msg);
                    return false;
                }
                UserInfo user = userInfoService.getUserInfoById(userId);
                if (null == user) {
                    String msg = "用户不存在";
                    setResponseInfo(httpServletResponse, msg);
                    return false;
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    String msg = "token验证失败";
                    setResponseInfo(httpServletResponse, msg);
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    private void setResponseInfo(HttpServletResponse response, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", msg);
        jsonObject.put("code", "666");
        try {
            response.getWriter().append(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println(msg);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
