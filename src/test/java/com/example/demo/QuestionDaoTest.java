package com.example.demo;


import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuestionDaoImpl;
import com.example.demo.dto.QuestionDto;
import com.example.demo.model.Question;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class QuestionDaoTest {

    @MockBean
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private QuestionDaoImpl questionDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question();
        doNothing().when(session).persist(question);

        questionDao.addQuestion(question);

        verify(session).persist(question);
    }

    @Test
    public void testUpdateQuestion() {
        Question question = new Question();
        doNothing().when(session).update(question);

        questionDao.updateQuestion(question);

        verify(session).update(question);
    }

    @Test
    public void testListQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        questions.add(new Question());
        when(session.createQuery("from Question").list()).thenReturn(questions);

        List<Question> result = questionDao.listQuestions();

        verify(session).createQuery("from Question");
        // Add more assertions here to validate the result
    }

    @Test
    public void testListQuestionsQA() {
        List<Object[]> questionAnswerList = new ArrayList<>();
        questionAnswerList.add(new Object[]{"Question 1", "Answer 1"});
        questionAnswerList.add(new Object[]{"Question 2", "Answer 2"});
        when(session.createQuery("select q.question, q.answer from Question q").list()).thenReturn(questionAnswerList);

        List<QuestionDto> result = questionDao.listQuestionsQA();

        verify(session).createQuery("select q.question, q.answer from Question q");
        // Add more assertions here to validate the result
    }

    // Add more test cases for other methods as needed
}
