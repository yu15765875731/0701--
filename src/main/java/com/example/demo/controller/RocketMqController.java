package com.example.demo.controller;

import com.example.demo.mq.RocketMqProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RocketMqController 演示 RocketMQ 的生产者接口。
 * 作用：将消息发送到消息队列，演示异步处理的思想。
 */
@RestController
@RequestMapping("/mq")
public class RocketMqController {

    private final RocketMqProducer rocketMqProducer;

    public RocketMqController(RocketMqProducer rocketMqProducer) {
        this.rocketMqProducer = rocketMqProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        rocketMqProducer.sendMessage("order-topic", message);
        return "消息已发送：" + message;
    }
}
