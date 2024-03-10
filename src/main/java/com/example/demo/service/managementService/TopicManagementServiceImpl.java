package com.example.demo.service.managementService;

import com.example.demo.model.Topic;
import com.example.demo.service.dbService.QuestionServiceImpl;
import com.example.demo.service.dbService.TopicService;
import com.example.demo.service.dbService.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicManagementServiceImpl implements TopicManagementService {

    final private TopicService topicService;
    @Autowired
    TopicManagementServiceImpl(TopicServiceImpl topicService
    ){
        this.topicService = topicService;
    }


    @Override
    public void createTopic(String newTopic) {
        String topicName = newTopic.substring(1,newTopic.length()-1);
        Topic topic = new Topic(topicName);
        topicService.createTopic(topic);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @Override
    public List<String> getOnlyTopicsNames(List<Topic> topics) {
        return topics.stream()
                    .map(Topic::getTopicName)
                    .collect(Collectors.toList());
    }
}
