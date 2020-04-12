package com.yuchengxin.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description  跨域请求cors配置类
 * @Author yuchengxin
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /** * **/
    private static final String ALL = "*";
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(ALL)
                .allowedMethods(ALL)
                .allowedHeaders(ALL)
                .allowCredentials(true)
                .maxAge(1800);
    }
}
