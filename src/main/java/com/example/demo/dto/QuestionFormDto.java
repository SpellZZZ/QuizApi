package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionFormDto {

    private String question;
    private String answer;
    private List<String> topics = new ArrayList<>();


    public QuestionFormDto() {
    }

    public QuestionFormDto(String question, String answer, List<String> topics) {
        this.question = question;
        this.answer = answer;
        this.topics = topics;
    }

    public QuestionFormDto(String question, String answer) {
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
