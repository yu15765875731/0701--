package com.example.demo.mq;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * RocketMqConsumer 演示 RocketMQ 消费者。
 * 作用：接收生产者发送的消息，并做后续业务处理。
 */
@Component
@RocketMQMessageListener(topic = "order-topic", consumerGroup = "order-group", messageModel = MessageModel.BROADCASTING)
public class RocketMqConsumer implements RocketMQListener<String> {

    private static final Logger logger = LoggerFactory.getLogger(RocketMqConsumer.class);

    @Override
    public void onMessage(String message) {
        logger.info("[RocketMQ Consumer] 接收到消息：{}", message);
        // 这里可以做后续处理，例如发邮件、记录日志、更新库存状态等。
    }
}
