package com.example.interviewPrep.quiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/*
 disableCachingNullValues - null값이 캐싱될 수 없도록 설정한다
 entryTtl - 캐시의 TTL(Time To Live)를 설정한다. Duraction class로 설정할 수 있다
 serializeKeysWith - 캐시 Key를 직렬화-역직렬화 하는데 사용하는 Pair를 지정한다
 serializeValuesWith - 캐시 Value를 직렬화-역직렬화 하는데 사용하는 Pair를 지정한다
 Value는 다양한 자료구조가 올 수 있기 때문에 GenericJackson2JsonRedisSerializer를 사용한다
 */

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    private long expireSecond = 600; //10분

    @Autowired
    private ObjectMapper objectMapper;



    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(host, port);
    }


    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues() //null 값 캐시 x
                .entryTtl(Duration.ofSeconds(expireSecond)) //캐시의 유효기간
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)));

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
    }

}

