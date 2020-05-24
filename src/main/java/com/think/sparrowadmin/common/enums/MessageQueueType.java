package com.think.sparrowadmin.common.enums;

import lombok.Getter;
import lombok.Setter;

public enum  MessageQueueType {
    QUEUE("queue"),
    TOPIC("topic");

    @Getter
    @Setter
    private String name;

    MessageQueueType(String name) {
        this.name = name;
    }

}
