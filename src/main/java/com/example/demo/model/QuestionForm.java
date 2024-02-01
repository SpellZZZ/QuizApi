package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionForm {

    private String question;
    private String answer;
    private List<String> topics = new ArrayList<>();


    public QuestionForm() {
    }

    public QuestionForm(String question, String answer, List<String> topics) {
        this.question = question;
        this.answer = answer;
        this.topics = topics;
    }

    public QuestionForm(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
