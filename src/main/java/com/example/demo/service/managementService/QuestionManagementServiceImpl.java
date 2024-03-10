package com.example.demo.service.managementService;


import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.model.Topic;
import com.example.demo.service.dbService.QuestionService;
import com.example.demo.service.dbService.QuestionServiceImpl;
import com.example.demo.service.dbService.TopicService;
import com.example.demo.service.dbService.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionManagementServiceImpl implements QuestionManagementService {


    Random rn = new Random();
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

    //optymalizacja
    @Override
    public QuestionDto singleQuestion(TopicDto topicDto) {
        List<String> tpcs = topicDto.getTopics();
        List<Question> qst = questionService.listQuestions();
        List<Question> res = new ArrayList<>() ;

        System.out.println("Liczba pytan: "+ qst.size());
        System.out.println("Liczba tematow: " + tpcs.size());

        for(Question q : qst) {
            for (String s : tpcs) {
                for(Topic t : q.getTopics()){
                    if(t.getTopicName().equals(s)){
                        res.add(q);
                    }
                }
            }
        }

        if( res.size() == 0) return new QuestionDto("Brak pytan", "Brak odpowiedzi");

        int i = rn.nextInt((int) res.size());

        return new QuestionDto(res.get( i ).getQuestion(), res.get( i ).getAnswer());
    }

}
