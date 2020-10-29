package com.fangheng.meepo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fangheng Sun on 2020/10/28
 */
@Configuration
@MapperScan("com.fangheng.meepo.mapper")
public class MeepoConfig {
}