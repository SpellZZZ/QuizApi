package com.example.demo.controller;


import com.example.demo.dao.TopicService;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionForm;
import com.example.demo.model.Topic;

import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class MainController {

    Random rn = new Random();

    QuestionService questionService;
    @Autowired(required=true)
    @Qualifier(value="questionService")
    public void setPersonService(QuestionService ps){
        this.questionService = ps;
    }

    TopicService topicService;
    @Autowired(required=true)
    @Qualifier(value="topicService")
    public void setTopicService(TopicService ts){
        this.topicService = ts;
    }


    @RequestMapping(value = "/allQuestions", method = RequestMethod.GET)
    public List<Question> allQuestions(){
        return questionService.listQuestions();
    }


    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public void addQestion(@RequestBody QuestionForm questionF){


        Question question = new Question();


        question.setTopics(questionF.getTopics().stream().map(topicName -> {


            boolean check = topicService.getAllTopics().stream().anyMatch(
                    topicFromDB -> topicFromDB.getTopicName().equals(topicName)
            );

            if(check == false){
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

    //optymalizacja
    @RequestMapping(value = "/singleQuestion", method = RequestMethod.POST)
    public QuestionDto singleQuestion(@RequestBody TopicDto topicDto) {

        List<String> tpcs = topicDto.getTopics();
        List<Question> qst = questionService.listQuestions();
        List<Question> res = new ArrayList<>() ;

        System.out.println("Liczba pytan: "+ qst.size());
        System.out.println("Liczba tematow: " + tpcs.size());
       // System.out.println(res.size());

        for(Question q : qst) {
            for (String s : tpcs) {
                for(Topic t : q.getTopics()){
                  //  System.out.println(t.getTopicName() + " " + s);
                    if(t.getTopicName().equals(s)){
                        res.add(q);
                    }
                }
                //System.out.println("");
            }
        }

        //int i = rn.nextInt((int) questionService.listQuestionsQA().stream().count());
        //return questionService.listQuestionsQA().get( i );


        System.out.println(res.size());

        if( res.size() == 0) return new QuestionDto("Brak pytan", "Brak odpowiedzi");

        int i = rn.nextInt((int) res.size());

        return new QuestionDto(res.get( i ).getQuestion(), res.get( i ).getAnswer());
    }



    @RequestMapping(value = "/allTopics", method = RequestMethod.GET)
    public List<String> allTopics(){

        List<Topic> topics = topicService.getAllTopics();

        List<String> topicNames = topics.stream()
                .map(Topic::getTopicName)
                .collect(Collectors.toList());

        return topicNames;
    }


    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public void addTopics(@RequestBody String newTopic){
        topicService.createTopic(new Topic(newTopic.substring(1,newTopic.length()-1)));
    }




}
