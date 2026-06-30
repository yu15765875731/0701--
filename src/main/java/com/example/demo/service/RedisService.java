package com.example.demo.service;

import java.time.Duration;

/**
 * RedisService 封装 Redis 常用操作。
 * 作用：演示 Redis 的 String、Hash 以及简单分布式锁的写法。
 */
public interface RedisService {

    void cacheValue(String key, String value, Duration ttl);

    String getValue(String key);

    boolean tryLock(String lockKey, String requestId, Duration ttl);

    boolean releaseLock(String lockKey, String requestId);
}
