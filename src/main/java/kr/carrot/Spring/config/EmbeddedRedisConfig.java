package kr.carrot.Spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class EmbeddedRedisConfig {

    private RedisServer redisServer;

    public EmbeddedRedisConfig(@Value("${spring.redis.port}") int redisPort) {
        this.redisServer = new RedisServer(redisPort);
    }

    @PostConstruct
    public void startRedis() {
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

}
