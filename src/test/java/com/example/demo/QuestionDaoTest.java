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
        q.setAnswer("Odpowiedz 1");

        questionDao.addQuestion(q);

    }


    @Test
    public void updateQuestion(){
        Question q = new Question();
        q.setQuestion("Pytanie 1");
        q.setAnswer("Odpowiedz 1");

        questionDao.updateQuestion(q);

    }
    @Test
    public void listQuestions() {
        Query q = Mockito.mock(Query.class);
        List<Question> temp = new ArrayList<>(){{
            add(new Question(1, "1", "1", null));
            add(new Question(2, "2", "2", null));
        }};
        when(session.get)
        when(q.getResultList()).thenReturn(temp);


        List<Question> questions = questionDao.listQuestions();
        Assertions.assertEquals(questions.size(), temp.size());
    }


    @Test
    public void listQuestionsQA(){}
    @Test
    public void getQuestionById(){}
    @Test
    public void removeQuestion(int id){}
    @Test
    public void findQuestionsByTopicNames(){}




}
