package com.example.demo.dao;


import com.example.demo.model.Topic;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TopicDao {
    public List<Topic> getAllTopics();
    public void createTopic(Topic topic);
    public List<Topic> findTopicsByNames(List<String> topicNames);

}
