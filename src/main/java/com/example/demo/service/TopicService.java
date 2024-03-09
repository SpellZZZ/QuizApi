package com.example.demo.service;

import com.example.demo.model.Topic;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicService topicService;

    @Transactional
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @Transactional
    public void createTopic(Topic topic) {
        topicService.createTopic(topic);
    }


}
