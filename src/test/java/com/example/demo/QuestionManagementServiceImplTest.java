package com.example.demo;

import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.QuestionFormDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Question;
import com.example.demo.model.Topic;

import com.example.demo.service.dbService.QuestionService;
import com.example.demo.service.dbService.TopicService;
import com.example.demo.service.managementService.QuestionManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionManagementServiceImplTest {

    @Mock
    private QuestionService questionService;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private QuestionManagementServiceImpl questionManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetQuestionsList() {
        List<Question> expectedQuestions = new ArrayList<>();

        Mockito.when(questionService.listQuestions()).thenReturn(expectedQuestions);



        List<Question> result = questionManagementService.getQuestionsList();

        assertEquals(expectedQuestions, result);
    }

    @Test
    public void testGetTopicsFromList() {
        List<String> topicStringList = new ArrayList<>();
        List<Topic> expectedTopics = new ArrayList<>();
        when(topicService.getTopicsByNames(topicStringList)).thenReturn(expectedTopics);

        List<Topic> result = questionManagementService.getTopicsFromList(topicStringList);

        assertEquals(expectedTopics, result);
    }

    @Test
    public void testAddQuestion() {
        QuestionFormDto questionFormDto = new QuestionFormDto();
        List<String> topicStringList = new ArrayList<>();
        questionFormDto.setQuestion("Test Question");
        questionFormDto.setAnswer("Test Answer");
        questionFormDto.setTopics(topicStringList);

        questionManagementService.addQuestion(questionFormDto);

        verify(topicService).getTopicsByNames(topicStringList);
        verify(questionService).addQuestion(any(Question.class));
    }

    @Test
    public void testSingleQuestion() {
        TopicDto topicDto = new TopicDto();
        List<String> topics = new ArrayList<>();
        topics.add("Test Topic");
        topicDto.setTopics(topics);

        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question());
        when(questionService.findQuestionsByTopicNames(topics)).thenReturn(questionList);

        QuestionDto result = questionManagementService.singleQuestion(topicDto);

        assertEquals("Question 1", result.getQuestion());
        assertEquals("Answer 1", result.getAnswer());
    }
}
