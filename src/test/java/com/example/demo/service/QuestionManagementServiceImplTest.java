package com.example.demo.service;


import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.QuestionFormDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.model.Topic;


import com.example.demo.service.dbService.QuestionServiceImpl;
import com.example.demo.service.dbService.TopicServiceImpl;
import com.example.demo.service.managementService.QuestionManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class QuestionManagementServiceImplTest {

    @Mock
    private QuestionServiceImpl questionService;
    @Mock
    private TopicServiceImpl topicService;
    @Mock
    private Random rn;

    @InjectMocks
    private QuestionManagementServiceImpl questionManagementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetQuestionsList() {
        List<Question> expectedQuestions = new ArrayList<>();
        when(questionService.listQuestions()).thenReturn(expectedQuestions);

        List<Question> result = questionManagementService.getQuestionsList();

        assertEquals(expectedQuestions, result);
    }


    @Test
    void testGetTopicsFromList() {
        List<String> topicStringList = new ArrayList<>();
        List<Topic> expectedTopics = new ArrayList<>();
        when(topicService.getTopicsByNames(topicStringList)).thenReturn(expectedTopics);

        List<Topic> result = questionManagementService.getTopicsFromList(topicStringList);

        assertEquals(expectedTopics, result);
    }


    @Test
    void testAddQuestion() {
        QuestionFormDto questionFormDto = new QuestionFormDto();
        List<String> topicStringList = new ArrayList<>();
        questionFormDto.setQuestion("Test Question");
        questionFormDto.setAnswer("Test Answer");
        questionFormDto.setTopics(topicStringList);

        doNothing().when(questionService).addQuestion(any(Question.class));
        questionManagementService.addQuestion(questionFormDto);

        verify(questionService, times(1)).addQuestion(any(Question.class));
    }


    @Test
    void testSingleQuestion_WhenQuestionListIsEmpty() {
        TopicDto topicDto = new TopicDto();
        topicDto.setTopics(new ArrayList<>());

        when(questionService.findQuestionsByTopicNames(anyList())).thenReturn(new ArrayList<>());

        QuestionDto result = questionManagementService.singleQuestion(topicDto);

        assertEquals("Brak pytan", result.getQuestion());
        assertEquals("Brak odpowiedzi", result.getAnswer());
    }



    @Test
    void testSingleQuestion_WhenQuestionListIsNotEmpty() {



        TopicDto topicDto = new TopicDto();
        List<String> topics = new ArrayList<>();
        topics.add("topic1");
        topicDto.setTopics(topics);

        List<Topic> topicList = new ArrayList<>() {
            {
                add(new Topic("test"));
            }
        };
        List<Question> questionList = new ArrayList<>(){
            {
            add(new Question("Question1", "Answer1", topicList));
            add(new Question("Question2", "Answer2", topicList));

        }};


        when(questionService.findQuestionsByTopicNames(anyList())).thenReturn(questionList);
        when(rn.nextInt(anyInt())).thenReturn(0);


        QuestionDto result = questionManagementService.singleQuestion(topicDto);


        assertEquals("Question1", result.getQuestion());
        assertEquals("Answer1", result.getAnswer());

    }




}
