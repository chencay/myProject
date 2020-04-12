package com.yuchengxin.test.controller;

import com.yuchengxin.test.config.PassToken;
import com.yuchengxin.test.config.UserLoginToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第一个controller
 * @author chencay
 */

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    /**导入configuration**/
    @Autowired
    private Configuration configuration;

    /**
     * helloWorld
     * @return String
     */
    @GetMapping("/world")
    @ResponseBody
    public String helloWorld() {
        return "hello, world!";
    }

    @GetMapping("/firstFtl")
    public ModelAndView showFirstFtl() throws IOException, TemplateException {
        List<String> showList = new ArrayList<>();
        showList.add("haha");
        showList.add("xixi");
        // 作为网页html使用
        ModelAndView mv = new ModelAndView();
        mv.addObject("showList", showList);
        mv.setViewName("firstFtl");
        // 作为非网页模板使用(比如邮箱的模板等)
        Map<String, Object> map = new HashMap<>();
        map.put("showList", showList);
        Template template = configuration.getTemplate("/firstFtl.ftl");
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(content);
        return mv;
    }

    @GetMapping("/getInputData")
    @ResponseBody
    @UserLoginToken
    public String getInputData(){
        return "haha, welcome to u";
    }
}
