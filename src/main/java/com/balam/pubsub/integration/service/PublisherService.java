package com.balam.pubsub.integration.service;

import com.balam.pubsub.integration.model.DemoMessage;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PublisherService {

    @Autowired
    PubSubTemplate pubSubTemplate;

    @Value("${default-client-id}")
    String defaultClientIdValue;

    @Value("${unwanted-headers}")
    List<String> unwantedHeaders;

    static final String CLIENT_ID = "client-id";
    static final String REQUEST_ID = "request-id";
    static final String DEMO_TOPIC = "demo-topic";

    public CompletableFuture<String>  publishToDemoTopic(DemoMessage demoMessage, Map<String, String> headers) {
        alterHeaders(headers);
        try{
            return pubSubTemplate.publish(DEMO_TOPIC, demoMessage, headers);
        } catch (Exception exception) {
            throw new RuntimeException(Arrays.toString(exception.getStackTrace()));
        }
    }

    private void alterHeaders(Map<String, String> headers){
        for (String header : unwantedHeaders) {
            headers.remove(header);
        }

        if(!headers.containsKey(CLIENT_ID)){
            headers.put(CLIENT_ID, defaultClientIdValue);
        }

        if(!headers.containsKey(REQUEST_ID)){
            headers.put(REQUEST_ID, UUID.randomUUID().toString());
        }
    }

}
