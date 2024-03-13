package com.example.demo.dao;


import com.example.demo.model.Topic;
import com.example.demo.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl  implements UserInfoDao{

    private static final Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);
    final private SessionFactory sessionFactory;

    @Autowired
    UserInfoDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public UserInfo getUserByName(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM UserInfo WHERE username = :username", UserInfo.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    @Override
    public void addUser(UserInfo userInfo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(userInfo);
        logger.info("User saved successfully");
    }
}
