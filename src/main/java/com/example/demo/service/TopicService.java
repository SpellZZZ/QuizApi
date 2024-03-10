package com.example.demo.service;

import com.example.demo.model.Topic;

import java.util.List;

public interface TopicService {
    public List<Topic> getAllTopics();
    public void createTopic(Topic topic);
}
