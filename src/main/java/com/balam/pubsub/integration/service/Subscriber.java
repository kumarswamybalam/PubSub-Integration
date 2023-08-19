package com.balam.pubsub.integration.service;

import org.springframework.stereotype.Service;

@Service
public class Subscriber {

//    @ServiceActivator(inputChannel = "mySubscriptionInputChannel")
//    public void messageReceiver(String payload,
//                                @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
//        System.out.println("Message received from Google Cloud Pub/Sub: " + payload);
//
//        // Acknowledge the message upon successful processing
//        message.ack();
//    }
}
