package com.example.demo.controller;

import com.example.demo.service.RedisService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

/**
 * RedisController 演示 Redis 接口。
 * 作用：为学习者提供 Redis 缓存与分布式锁的 REST 入口。
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/cache/{key}")
    public String cacheValue(@PathVariable String key, @RequestBody String value) {
        redisService.cacheValue(key, value, Duration.ofMinutes(10));
        return "缓存成功";
    }

    @GetMapping("/cache/{key}")
    public String getValue(@PathVariable String key) {
        return redisService.getValue(key);
    }

    @PostMapping("/lock/{key}")
    public String lock(@PathVariable String key) {
        String requestId = String.valueOf(System.currentTimeMillis());
        boolean locked = redisService.tryLock(key, requestId, Duration.ofSeconds(30));
        return locked ? "获取锁成功，请在 30 秒内完成业务" : "锁已被占用";
    }

    @DeleteMapping("/lock/{key}/{requestId}")
    public String unlock(@PathVariable String key, @PathVariable String requestId) {
        boolean released = redisService.releaseLock(key, requestId);
        return released ? "释放锁成功" : "释放失败，requestId 不匹配";
    }
}
