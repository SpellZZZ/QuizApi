package com.example.demo.controller;



import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionForm;
import com.example.demo.model.Topic;

import com.example.demo.service.managementService.QuestionManagementService;
import com.example.demo.service.managementService.TopicManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {



    final private QuestionManagementService questionManagementService;
    final private TopicManagementService topicManagementService;

    @Autowired
    MainController(QuestionManagementService questionManagementService,
                   TopicManagementService topicManagementService
    ){
        this.questionManagementService = questionManagementService;
        this.topicManagementService = topicManagementService;
    }





    @GetMapping(value = "/allQuestions")
    public List<Question> allQuestions(){
        return questionManagementService.getQuestionsList();
    }

    @PostMapping(value = "/addTopic")
    public void addTopics(@RequestBody String newTopic){
        topicManagementService.createTopic(newTopic);
    }





    //refactor
    @PostMapping(value = "/addQuestion")
    public void addQuestion(@RequestBody QuestionForm questionF){


        Question question = new Question();


        question.setTopics(questionF.getTopics().stream().map(topicName -> {


            boolean check = topicService.getAllTopics().stream().anyMatch(
                    topicFromDB -> topicFromDB.getTopicName().equals(topicName)
            );

            if(!check){
                Topic topic = new Topic();
                topic.setTopicName(topicName);
                return topic;
            } else {
                return topicService.getAllTopics()
                        .stream()
                        .filter(topicFromDB -> topicFromDB.getTopicName().equals(topicName))
                        .findFirst()
                        .orElse(null);
            }

        }).collect(Collectors.toList()));


        question.setQuestion(questionF.getQuestion());
        question.setAnswer(questionF.getAnswer());

        questionService.addQuestion(question);
    }

    @PostMapping(value = "/singleQuestion")
    public QuestionDto singleQuestion(@RequestBody TopicDto topicDto) {
        return questionManagementService.singleQuestion(topicDto);
    }


    @GetMapping(value = "/allTopics")
    public List<String> allTopics(){

        List<Topic> topics = topicManagementService.getAllTopics();
        List<String> topicNames = topicManagementService.getOnlyTopicsNames(topics);

        return topicNames;
    }






}
