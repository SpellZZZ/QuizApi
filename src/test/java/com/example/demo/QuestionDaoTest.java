package com.example.demo;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuestionDaoImpl;
import com.example.demo.model.Question;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class QuestionDaoTest {

    @MockBean
    private SessionFactory sessionFactory;

    @MockBean
    private Session session;


    private QuestionDao questionDao;


    @BeforeEach
    public void setup() throws Exception {
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        questionDao = new QuestionDaoImpl(sessionFactory);
    }


    @Test
    void addQuestion() {
        Question q = new Question();
        q.setQuestion("Pytanie 1");
        q.setAnswer("Odpowiedz");

        questionDao.addQuestion(q);

    }




}
