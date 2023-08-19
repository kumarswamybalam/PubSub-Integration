package com.balam.pubsub.integration.service;

import com.balam.pubsub.integration.model.DemoMessage;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class PublisherService {

    @Autowired
    PubSubTemplate pubSubTemplate;

    public String  publishToDemoTopic(DemoMessage demoMessage, Map<String, String> headers) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture;
        try{
            completableFuture = pubSubTemplate.publish("demo-topic", demoMessage, headers);
        } catch (Exception exception) {
            throw new RuntimeException(Arrays.toString(exception.getStackTrace()));
        }
        return completableFuture.get();
    }
}
