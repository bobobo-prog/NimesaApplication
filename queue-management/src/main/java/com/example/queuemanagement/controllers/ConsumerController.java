package com.example.queuemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/subscribe/{topic}")
    public String subscribeToTopic(@PathVariable("topic") String topic) {
        return "Subscribed to topic: " + topic;
    }

    @KafkaListener(topics = "sampleTopic", groupId = "group_id")
    public synchronized void consumeData(String data) {
        System.out.println("Received data from Kafka topic: " + data);
    }
}
