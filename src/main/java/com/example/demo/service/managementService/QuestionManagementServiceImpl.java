package com.example.demo.service.managementService;


import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.dto.QuestionFormDto;
import com.example.demo.model.Topic;
import com.example.demo.service.dbService.QuestionService;
import com.example.demo.service.dbService.QuestionServiceImpl;
import com.example.demo.service.dbService.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionManagementServiceImpl implements QuestionManagementService {


    Random rn = new Random();
    final private QuestionService questionService;
    final private TopicService topicService;

    @Autowired
    QuestionManagementServiceImpl(QuestionServiceImpl questionService,
                                  TopicService topicService
    ){
        this.questionService = questionService;
        this.topicService = topicService;
    }

    @Override
    public List<Question> getQuestionsList() {
        return questionService.listQuestions();
    }


    @Override
    public List<Topic> getTopicsFromList(List<String> topicStringList) {
        return topicService.getTopicsByNames(topicStringList);
    }

    @Override
    public void addQuestion(QuestionFormDto questionF) {
        Question question = new Question();
        List<String> topicStringList = questionF.getTopics();
        question.setTopics(getTopicsFromList(topicStringList));
        question.setQuestion(questionF.getQuestion());
        question.setAnswer(questionF.getAnswer());

        questionService.addQuestion(question);
    }

    @Override
    public QuestionDto singleQuestion(TopicDto topicDto) {

        List<Question> questionList = questionService.findQuestionsByTopicNames(topicDto.getTopics());

        if(questionList.isEmpty()) return new QuestionDto("Brak pytan", "Brak odpowiedzi");

        int i = rn.nextInt((int) questionList.size());

        return new QuestionDto(questionList.get( i ).getQuestion(), questionList.get( i ).getAnswer());


    }

}




// old bind topics and question
        /*question.setTopics(questionF.getTopics().stream().map(topicName -> {


            boolean check = topicService.getAllTopics().stream().anyMatch(
                    topicFromDB -> topicFromDB.getTopicName().equals(topicName)
            );
            System.out.println(topicName);
            System.out.println(check);

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

        }).collect(Collectors.toList()));*/


//old random question
        /*List<String> tpcs = topicDto.getTopics();
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

        return new QuestionDto(res.get( i ).getQuestion(), res.get( i ).getAnswer());*/