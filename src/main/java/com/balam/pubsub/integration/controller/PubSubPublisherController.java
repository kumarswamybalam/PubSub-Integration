package com.balam.pubsub.integration.controller;

import com.balam.pubsub.integration.model.DemoMessage;
import com.balam.pubsub.integration.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class PubSubPublisherController {

    @Autowired
    PublisherService publisherService;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody DemoMessage demoMessage,
                                                    @RequestHeader Map<String, String> headers) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = publisherService.publishToDemoTopic(demoMessage, headers);
        if(cf.get() != null) {
            return "Message published, MessageId: "+cf.get();
        }

        throw new RuntimeException("Failed to publish message \n"+demoMessage);
    }
}
