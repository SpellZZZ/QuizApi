package com.example.demo.service.managementService;

import com.example.demo.model.Topic;

import java.util.List;

public interface TopicManagementService {
    public void createTopic(String newTopic);
    public List<Topic> getAllTopics();
    List<String> getOnlyTopicsNames(List<Topic> topics);

}
