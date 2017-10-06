package com.edisonren.nmm.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by edison on 9/13/17.
 */
@Configuration
public class DaoConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${REDIS_HOST_NAME}")
    private String hostName;
    @Value("${REDIS_PORT}")
    private Integer port;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        //template.setKeySerializer(new StringRedisSerializer());
        //template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        //template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        // TODO: add Jedis sentinel to ConnFactor for monitor, notification and failover
        // TODO: use pool
        logger.info("NMM_REDIS starting connection on {}:{}", hostName, port);
        JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
        jedisConnFactory.setHostName(hostName);
        jedisConnFactory.setPort(port);
        return jedisConnFactory;
    }
}
