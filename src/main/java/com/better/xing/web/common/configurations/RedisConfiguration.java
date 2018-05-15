package com.better.xing.web.common.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yanrx20795@hundsun.com
 * @date 2018/5/12 22:30
 */
@Configuration
public class RedisConfiguration {
    @Bean
    @Primary
    public RedisTemplate<Object,Object> configRedisTemplate(RedisTemplate redisTemplate){
        return redisTemplate;
    }
}
