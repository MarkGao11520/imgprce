package com.hw.xyls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by gaowenfeng on 2017/6/25.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/image").setViewName("imageManager");
        registry.addViewController("/result").setViewName("resultManager");
        registry.addViewController("/user").setViewName("userManager");
    }
}
