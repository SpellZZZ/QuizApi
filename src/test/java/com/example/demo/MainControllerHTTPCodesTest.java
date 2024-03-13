package com.example.demo;

import com.example.demo.controller.MainController;
import com.example.demo.dto.QuestionFormDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Topic;
import com.example.demo.service.managementService.QuestionManagementService;
import com.example.demo.service.managementService.TopicManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MainController.class)
public class MainControllerHTTPCodesTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionManagementService questionManagementService;

    @MockBean
    private TopicManagementService topicManagementService;

    @Test
    void allQuestions() throws Exception {
        when(questionManagementService.getQuestionsList()).thenReturn(null);
        this.mockMvc.perform(get("/allQuestions"))
                .andExpect(status().isOk());
    }

    @Test
    void addQuestion() throws Exception {
        QuestionFormDto questionFormDto = null;
        this.mockMvc.perform(post("/addQuestion")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void singleQuestion() throws Exception {
        TopicDto topicDto = null;
        when(questionManagementService.singleQuestion(topicDto)).thenReturn(null);

        this.mockMvc.perform(post("/singleQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void allTopics() throws Exception {
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Topic 1"));
        topics.add(new Topic("Topic 2"));

        when(topicManagementService.getAllTopics()).thenReturn(topics);
        when(topicManagementService.getOnlyTopicsNames(topics)).thenReturn(Arrays.asList("Topic 1", "Topic 2"));

        mockMvc.perform(MockMvcRequestBuilders.get("/allTopics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("Topic 1"))
                .andExpect(jsonPath("$[1]").value("Topic 2"));
    }

    @Test
    void addTopics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/addTopic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Topic"))
                .andExpect(status().isOk());
    }

}
