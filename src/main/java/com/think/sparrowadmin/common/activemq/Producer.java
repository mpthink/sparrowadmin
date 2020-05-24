package com.think.sparrowadmin.common.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * refer to:
 * https://yq.aliyun.com/articles/702540
 * https://www.cnblogs.com/elvinle/p/8457596.html
 * https://blog.csdn.net/e891377/article/details/78747104
 * https://www.cnblogs.com/mysgk/p/9790801.html
 */
@Service
@Slf4j
public class Producer {


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(String desination, String message) {
        log.info("Send message {[}] to desination [{}]", message, desination);
        jmsMessagingTemplate.convertAndSend(desination, message);
    }

    public void sendMessage(Destination desination, String message) {
        log.info("Send message {[}] to desination [{}]", message, desination);
        jmsMessagingTemplate.convertAndSend(desination, message);
    }
}
