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
    TopicServiceImpl(TopicDao topicDao){
         this.topicDao = topicDao;
    }


    @Transactional
    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    @Transactional
    public void createTopic(Topic topic) {
        topicDao.createTopic(topic);
    }


}
