package com.example.demo.dao;


import com.example.demo.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao{

    private static final Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Topic> getAllTopics() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Topic", Topic.class).list();
    }

    @Override
    public void createTopic(Topic topic) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(topic);
    }
}
