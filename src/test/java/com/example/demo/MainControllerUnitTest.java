package com.example.demo;

import com.example.demo.controller.MainController;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.QuestionFormDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.model.Topic;
import com.example.demo.service.managementService.QuestionManagementService;
import com.example.demo.service.managementService.TopicManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


class MainControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private QuestionManagementService questionManagementService;

    @Mock
    private TopicManagementService topicManagementService;

    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mainController = new MainController(questionManagementService, topicManagementService);
    }

    @Test
    void testAllQuestions() {
        List<Question> questions = Arrays.asList(new Question(), new Question());
        when(questionManagementService.getQuestionsList()).thenReturn(questions);

        List<Question> result = mainController.allQuestions();

        assertEquals(questions.size(), result.size());
        verify(questionManagementService, times(1)).getQuestionsList();
    }

    @Test
    void testAddQuestion() {
        QuestionFormDto questionFormDto = new QuestionFormDto();
        mainController.addQuestion(questionFormDto);

        verify(questionManagementService, times(1)).addQuestion(questionFormDto);
    }

    @Test
    void testSingleQuestion() {
        TopicDto topicDto = new TopicDto();
        QuestionDto questionDto = new QuestionDto();
        when(questionManagementService.singleQuestion(topicDto)).thenReturn(questionDto);

        QuestionDto result = mainController.singleQuestion(topicDto);

        assertEquals(questionDto, result);
        verify(questionManagementService, times(1)).singleQuestion(topicDto);
    }

    @Test
    void testAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Topic 1"));
        topics.add(new Topic("Topic 2"));

        when(topicManagementService.getAllTopics()).thenReturn(topics);
        when(topicManagementService.getOnlyTopicsNames(topics)).thenReturn(Arrays.asList("Topic 1", "Topic 2"));

        List<String> result = mainController.allTopics();

        assertEquals(2, result.size());
        verify(topicManagementService, times(1)).getAllTopics();
        verify(topicManagementService, times(1)).getOnlyTopicsNames(topics);
    }

    @Test
    void testAddTopic() throws Exception {
        String newTopic = "New Topic";
        mainController.addTopics(newTopic);

        verify(topicManagementService, times(1)).createTopic(newTopic);
    }



}