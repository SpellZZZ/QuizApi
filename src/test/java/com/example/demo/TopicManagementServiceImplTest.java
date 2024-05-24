package com.example.demo;


import com.example.demo.model.Question;
import com.example.demo.model.Topic;
import com.example.demo.service.dbService.TopicService;
import com.example.demo.service.dbService.TopicServiceImpl;
import com.example.demo.service.managementService.TopicManagementService;
import com.example.demo.service.managementService.TopicManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class TopicManagementServiceImplTest {


    @InjectMocks
    TopicManagementServiceImpl topicManagementService;
    @Mock
    TopicServiceImpl topicService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTopicDoesntExistTest() throws Exception {
        String topicS = "sample";
        Topic topic = new Topic(topicS);
        //prepare
        when(topicService.getTopicsByName(anyString())).thenReturn(topic);
        doNothing().when(topicService).createTopic(any(Topic.class));

        topicManagementService.createTopic(topicS);

        verify(topicService, times(1)).getTopicsByName(any(String.class));
        verify(topicService, times(1)).createTopic(any(Topic.class));
    }
    @Test
    void createTopicExistTest(){
        String topicS = "sample";
        Topic topic = new Topic(topicS);
        //prepare
        when(topicService.getTopicsByName(anyString())).thenReturn(null);
        doNothing().when(topicService).createTopic(any(Topic.class));

        Exception exception = assertThrows(Exception.class, () -> {
            topicManagementService.createTopic(topicS);
        });

        verify(topicService, times(1)).getTopicsByName(any(String.class));
    }

    @Test
    void getAllTopicsTest() {

        List<Topic> expectedTopic = new ArrayList<>();
        when(topicService.getAllTopics()).thenReturn(expectedTopic);

        List<Topic> result = topicManagementService.getAllTopics();

        assertEquals(expectedTopic, result);


    }

    @Test
    void getOnlyTopicsNamesTest() {

    }

    @Test
    void isUniqueTopicTrueTest(){
        String name = "sample";
        Topic sampleTopic = new Topic(name);

        when(topicService.getTopicsByName(anyString())).thenReturn(null);
        boolean result = topicManagementService.isUniqueTopic(name);
        assertEquals(true, result);

    }

    @Test
    void isUniqueTopicFalseTest(){
        String name = "sample";
        Topic sampleTopic = new Topic(name);

        when(topicService.getTopicsByName(anyString())).thenReturn(sampleTopic);
        boolean result = topicManagementService.isUniqueTopic(name);
        assertEquals(false, result);

    }


}
