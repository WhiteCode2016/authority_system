package com.white.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebMVC 配置
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 登录页
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        // 注册页
        registry.addViewController("/register").setViewName("register");
        // 后台主页
        registry.addViewController("/admin/index").setViewName("admin/index");
        // 菜单界面
        registry.addViewController("/menu").setViewName("admin/menu/menu_view");
        registry.addViewController("/menu/menu_add").setViewName("admin/menu/menu_add");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
