package com.example.demo.controller;

import com.example.demo.auth.JwtService;
import com.example.demo.controller.MainController;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.TopicDto;
import com.example.demo.model.Topic;
import com.example.demo.service.managementService.QuestionManagementService;
import com.example.demo.service.managementService.TopicManagementService;
import com.example.demo.service.managementService.UserInfoManagementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(MainController.class)
class MainControllerHTTPCodesTest {

    @InjectMocks
    private MainController mainController;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionManagementService questionManagementService;

    @MockBean
    private TopicManagementService topicManagementService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserInfoManagementServiceImpl userInfoManagementService;



    @Test
    @WithMockUser
    void allQuestions() throws Exception {

        mockMvc.perform(get("/allQuestions"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    void addQuestion() throws Exception {
        //QuestionFormDto questionFormDto = new QuestionFormDto("question", "answer", null);

        mockMvc.perform(post("/addQuestion")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"question\":\"test\",\"answer\":\"test\",\"topics\":[\"Java\",\"jezyk\"]}")
                        )
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    void singleQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto("question", "answer");

        //given(questionManagementService.singleQuestion(any(TopicDto.class))).willReturn(questionDto);
        when(questionManagementService.singleQuestion(any(TopicDto.class))).thenReturn(questionDto);

        mockMvc.perform(post("/singleQuestion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content("{\"topics\":[\"Java\",\"jezyk\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
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
    @WithMockUser
    void addTopics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/addTopic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content("Topic"))
                .andExpect(status().isOk());
    }


}
