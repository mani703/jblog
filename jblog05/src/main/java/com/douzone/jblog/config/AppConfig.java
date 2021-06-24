package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBatisConfig;

@Configuration
@ComponentScan({"com.douzone.jblog.repository", "com.douzone.jblog.service"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
}