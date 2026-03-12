package com.example.taskmanager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class Counter {

    @Id
    private String id;

    private long sequence;

    public long getSequence() {
        return sequence;
    }
}