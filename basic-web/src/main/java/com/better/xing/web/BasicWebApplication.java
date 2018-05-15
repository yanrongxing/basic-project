package com.better.xing.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableRedisRepositories(redisTemplateRef ="jedisTemplate", basePackages = {"com.better.xing.web.nosql.redis"},keyValueTemplateRef = "jedisKeyValueTemplate")
@EnableSwagger2
@EntityScan(basePackages={"com.better.xing.web.jpa.model"})
public class BasicWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicWebApplication.class, args);
	}
}
