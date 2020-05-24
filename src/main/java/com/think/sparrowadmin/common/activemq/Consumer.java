package com.think.sparrowadmin.common.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @JmsListener(destination = "test-queue1")
    public void receiveMessage(String text){
        log.info("Received message: {}",text);
    }

}
