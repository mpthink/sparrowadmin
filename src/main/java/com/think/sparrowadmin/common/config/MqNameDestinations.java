package com.think.sparrowadmin.common.config;

import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

//@Component
//@ConfigurationProperties(prefix = "appconfig")
//@Data
//@Slf4j

/**
 *  This file is not used, jms message can send destination with string
 *  No need to store static destination in one map
 */
public class MqNameDestinations {

    private static final String queue = "queue";
    private static final String topic = "topic";
    private Map<String, String> mqvalues;

    public static Map<String, Destination> queueMap = new HashMap<>();
    public static Map<String, Destination> topicMap = new HashMap<>();

//    @PostConstruct
    private void init(){
        mqvalues.forEach((key,value)->{
            if(key.contains(queue)){
                queueMap.put(value,new ActiveMQQueue(value));
            }else if(key.contains(topic)){
                topicMap.put(value, new ActiveMQQueue(value));
            }
        });
    }
}
