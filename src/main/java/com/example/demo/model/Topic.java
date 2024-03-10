package com.example.demo.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TOPIC")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topic_id;

    @Column(unique=true)
    private String topicName;




    public Topic(int topic_id, String topicName) {
        this.topic_id = topic_id;
        this.topicName = topicName;

    }
    public Topic(String topicName) {
        this.topicName = topicName;
    }
    public Topic() {
    }


    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
