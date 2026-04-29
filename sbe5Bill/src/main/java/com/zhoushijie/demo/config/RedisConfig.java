package com.zhoushijie.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

//@Configuration//定义一个配置类
@Configuration
public class RedisConfig {
    //    @Bean//注入一个默认名称为redisTemplate的组件
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        //使用JSON格式序列化对象，对缓存的数据ket和value进行转换
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //设置RedisTemplate模板API的序列化方式为JSON
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


}
