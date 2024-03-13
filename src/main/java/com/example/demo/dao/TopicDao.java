package com.example.demo.dao;


import com.example.demo.model.Topic;

import java.util.List;


public interface TopicDao {
    public List<Topic> getAllTopics();
    public void createTopic(Topic topic);
    public List<Topic> getTopicsByNames(List<String> topicNames);
    public Topic getTopicsByName(String topicName);

}
