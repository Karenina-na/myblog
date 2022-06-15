package com.augenstern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis远程访问测试
 */
@SpringBootTest
public class RedisCacheTests {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.connect-timeout}")
    private int timeout;

    /**
     * 测试连接
     */
    @Test
    public void Test(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);        //最大连接数
        jedisPoolConfig.setMaxIdle(10);         //最大空闲连接

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        Jedis resource = jedisPool.getResource();
        resource.set("Test","Test success");

        System.out.println(resource.get("Test"));
    }
}
