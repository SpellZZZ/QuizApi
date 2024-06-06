package com.example.demo.dao;


import com.example.demo.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicDaoImpl implements TopicDao{

    private static final Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);
    final private SessionFactory sessionFactory;

    @Autowired
    TopicDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

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


    @Override
    public List<Topic> getTopicsByNames(List<String> topicNames) {
        try (Session session = sessionFactory.openSession()) {
            Query<Topic> query = session.createQuery("SELECT t FROM Topic t WHERE t.topicName IN :topicNames", Topic.class);
            query.setParameterList("topicNames", topicNames);
            return query.getResultList();
        }
    }
    @Override
    public Topic getTopicByName(String topicName) {
        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("FROM Topic WHERE topicName = :topicName", Topic.class)
                    .setParameter("topicName", topicName)
                    .uniqueResult();
        }
    }



}
