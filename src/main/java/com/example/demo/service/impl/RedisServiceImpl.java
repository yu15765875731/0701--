package com.example.demo.service.impl;

import com.example.demo.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * RedisServiceImpl 是 Redis 业务实现类。
 * 作用：演示缓存存取与分布式锁的基本思路。
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void cacheValue(String key, String value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    @Override
    public String getValue(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? value.toString() : null;
    }

    @Override
    public boolean tryLock(String lockKey, String requestId, Duration ttl) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, ttl);
        return Boolean.TRUE.equals(success);
    }

    @Override
    public boolean releaseLock(String lockKey, String requestId) {
        Object value = redisTemplate.opsForValue().get(lockKey);
        if (requestId.equals(value)) {
            return Boolean.TRUE.equals(redisTemplate.delete(lockKey));
        }
        return false;
    }
}
