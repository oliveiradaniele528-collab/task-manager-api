package com.example.taskmanager.service;

import com.example.taskmanager.model.Counter;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final MongoTemplate mongoTemplate;

    public CounterService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public long getNextSequence() {
        Query query = new Query(Criteria.where("_id").is("taskId"));
        Update update = new Update().inc("sequence", 1);

        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true);

        Counter counter = mongoTemplate.findAndModify(
                query,
                update,
                options,
                Counter.class,
                "counters"
        );

        return counter.getSequence();
    }
}