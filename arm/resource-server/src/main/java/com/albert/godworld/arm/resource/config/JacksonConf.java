package com.albert.godworld.arm.resource.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class JacksonConf {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
                    .serializerByType(Long.TYPE, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        };
    }

    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory,Jackson2JsonRedisSerializer serializer){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        //redis.key序列化器
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //redis.value序列化器
        redisTemplate.setValueSerializer(serializer);
        //redis.hash.key序列化器
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //redis.hash.value序列化器
        redisTemplate.setHashValueSerializer(serializer);
        //调用其他初始化逻辑
        redisTemplate.afterPropertiesSet();
        //设置事务一致
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }
}
