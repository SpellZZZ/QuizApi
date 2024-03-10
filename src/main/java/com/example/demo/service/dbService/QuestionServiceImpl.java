package com.example.demo.service.dbService;


import com.example.demo.dao.QuestionDao;
import com.example.demo.dto.QuestionDto;
import com.example.demo.model.Question;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    final private QuestionDao questionDao;

    @Autowired
    QuestionServiceImpl(QuestionDao questionDAO) {
        this.questionDao = questionDAO;
    }
    @Override
    @Transactional
    public void addQuestion(Question p) {
        this.questionDao.addQuestion(p);
    }
    @Override
    @Transactional
    public void updateQuestion(Question p) {
        this.questionDao.updateQuestion(p);
    }
    @Override
    @Transactional
    public List<Question> listQuestions() {
        return this.questionDao.listQuestions();
    }
    @Override
    @Transactional
    public List<QuestionDto> listQuestionsQA() {
        return this.questionDao.listQuestionsQA();
    }
    @Override
    @Transactional
    public Question getQuestionById(int id) {
        return this.questionDao.getQuestionById(id);
    }

    @Override
    @Transactional
    public void removeQuestion(int id) {
        this.questionDao.removeQuestion(id);
    }

    @Override
    @Transactional
    public List<Question> findQuestionsByTopicNames(List<String> topicNames) {
        return this.questionDao.findQuestionsByTopicNames(topicNames);
    }

}

