package com.example.queuemanagement.controllers;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @GetMapping("/list")
    public List<String> listTopics() throws ExecutionException, InterruptedException {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        ListTopicsOptions options = new ListTopicsOptions().listInternal(false);
        ListTopicsResult topics = adminClient.listTopics(options);
        Collection<TopicListing> topicListings = topics.listings().get();
        List<String> topicNames = new ArrayList<>();
        for (TopicListing topicListing : topicListings) {
            topicNames.add(topicListing.name());
        }
        return topicNames;
    }
}
