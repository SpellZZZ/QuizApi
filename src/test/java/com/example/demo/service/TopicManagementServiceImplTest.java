package com.example.demo.service;


import com.example.demo.model.Topic;
import com.example.demo.service.dbService.TopicServiceImpl;
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
    void createTopicNotExistTest() throws Exception {
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
        List<Topic> topics = new ArrayList<Topic>(){{
            add(new Topic("topic 1"));
            add(new Topic("topic 2"));
            add(new Topic("topic 3"));
            add(new Topic("topic 4"));
        }};

        List<String> topicsNames = topicManagementService.getOnlyTopicsNames(topics);

        assertEquals("topic 1", topicsNames.get(0));
        assertEquals("topic 2", topicsNames.get(1));
        assertEquals("topic 3", topicsNames.get(2));
        assertEquals("topic 4", topicsNames.get(3));

        assertEquals(4, topicsNames.size());

    }


    @Test
    void getOnlyTopicsNamesTestEmptyList() {
        List<Topic> topics = new ArrayList<Topic>();

        List<String> topicsNames = topicManagementService.getOnlyTopicsNames(topics);


        assertEquals(0, topicsNames.size());

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
