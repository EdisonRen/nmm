package com.edisonren.nmm.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by edison on 9/13/17.
 */
@Configuration
public class DaoConfig {

    @Value("${HOST_NAME:localhost}")
    private String hostName;
    @Value("${PORT:6379}")
    private Integer port;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setKeySerializer( new StringRedisSerializer() );
        //template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        //template.setValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        return template;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName(hostName);
        jedisConFactory.setPort(port);
        return jedisConFactory;
    }
}
