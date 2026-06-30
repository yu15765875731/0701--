package com.example.demo.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

/**
 * RocketMqProducer 演示 RocketMQ 生产者。
 * 作用：将业务事件发送到消息队列，实现异步处理。
 */
@Component
public class RocketMqProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public RocketMqProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }
}
