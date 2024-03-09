package com.example.demo.service;


import com.example.demo.dao.QuestionDao;
import com.example.demo.dto.QuestionDto;
import com.example.demo.model.Question;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public void setQuestionDAO(QuestionDao questionDAO) {
        this.questionDao = questionDAO;
    }

    @Transactional
    public void addQuestion(Question p) {
        this.questionDao.addQuestion(p);
    }

    @Transactional
    public void updateQuestion(Question p) {
        this.questionDao.updateQuestion(p);
    }

    @Transactional
    public List<Question> listQuestions() {
        return this.questionDao.listQuestions();
    }
    @Transactional
    public List<QuestionDto> listQuestionsQA() {
        return this.questionDao.listQuestionsQA();
    }

    @Transactional
    public Question getQuestionById(int id) {
        return this.questionDao.getQuestionById(id);
    }

    @Transactional
    public void removeQuestion(int id) {
        this.questionDao.removeQuestion(id);
    }

}

