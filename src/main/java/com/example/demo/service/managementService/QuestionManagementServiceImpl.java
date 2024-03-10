package com.example.demo.service.managementService;


import com.example.demo.model.Question;
import com.example.demo.service.dbService.QuestionService;
import com.example.demo.service.dbService.QuestionServiceImpl;
import com.example.demo.service.dbService.TopicService;
import com.example.demo.service.dbService.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionManagementServiceImpl implements QuestionManagementService {

    final private QuestionService questionService;
    @Autowired
    QuestionManagementServiceImpl(QuestionServiceImpl questionService
    ){
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestionsList() {
        return questionService.listQuestions();
    }
}
