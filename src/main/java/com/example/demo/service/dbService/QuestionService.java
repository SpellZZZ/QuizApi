package com.example.demo.service.dbService;

import com.example.demo.dto.QuestionDto;
import com.example.demo.model.Question;

import java.util.List;

public interface QuestionService {

    public void addQuestion(Question p);
    public void updateQuestion(Question p);
    public List<Question> listQuestions();
    public List<QuestionDto> listQuestionsQA();
    public Question getQuestionById(int id);
    public void removeQuestion(int id);



}
