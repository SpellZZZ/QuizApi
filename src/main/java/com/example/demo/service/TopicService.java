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
    private SessionFactory sessionFactory;

    @Transactional
    public List<Topic> getAllTopics() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Topic", Topic.class).list();

    }

    @Transactional
    public void createTopic(Topic topic) {

        Session session = sessionFactory.getCurrentSession();
        session.save(topic);

    }


}
