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



    /*@ManyToMany(mappedBy = "topics")
    private List<Question> question = new ArrayList<>();
*/

    public Topic(int topic_id, String topicName/*, List<Question> question*/) {
        this.topic_id = topic_id;
        this.topicName = topicName;
       // this.question = question;
    }
    public Topic(String topicName) {
        this.topicName = topicName;
        //this.question = question;
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

    /*public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }*/
}
