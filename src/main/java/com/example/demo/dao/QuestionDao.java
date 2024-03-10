package com.example.demo.dao;

import com.example.demo.dto.QuestionDto;
import com.example.demo.model.Question;

import java.util.List;

public interface QuestionDao {
    public void addQuestion(Question q);
    public void updateQuestion(Question q);
    public List<Question> listQuestions();
    public List<QuestionDto> listQuestionsQA();
    public Question getQuestionById(int id);
    public void removeQuestion(int id);
    public List<Question> findQuestionsByTopicNames(List<String> topicNames);
}
