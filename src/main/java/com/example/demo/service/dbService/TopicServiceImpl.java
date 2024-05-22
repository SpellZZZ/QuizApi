package com.example.demo.service.dbService;

import com.example.demo.dao.TopicDao;
import com.example.demo.model.Topic;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    final private TopicDao topicDao;

    @Autowired
    public TopicServiceImpl(TopicDao topicDao){
         this.topicDao = topicDao;
    }


    @Override
    @Transactional
    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    @Override
    @Transactional
    public void createTopic(Topic topic) {
        topicDao.createTopic(topic);
    }

    @Override
    @Transactional
    public List<Topic> getTopicsByNames(List<String> topicNames) {
        return topicDao.getTopicsByNames(topicNames);
    }
    @Override
    @Transactional
    public Topic getTopicsByName(String topicName){
        return topicDao.getTopicsByName(topicName);
    }


}
