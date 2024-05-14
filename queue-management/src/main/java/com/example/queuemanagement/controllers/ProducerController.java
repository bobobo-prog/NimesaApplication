package com.example.queuemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/addData/{topic}")
    public synchronized String addDataToTopic(@PathVariable("topic") String topic, @RequestBody String data) {
        // Send data to Kafka topic
        kafkaTemplate.send(topic, data);
        return "Data added to topic " + topic + ": " + data;
    }
}
