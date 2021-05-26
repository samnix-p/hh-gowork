package ru.gowork.dao;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.gowork.entity.Step;
import ru.gowork.entity.User;
import ru.gowork.entity.UserAnswer;

public class UserAnswerDao {
    private final SessionFactory sessionFactory;


    public UserAnswerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void saveAnswer(User user, Step step, Object userAnswer) {
        UserAnswer entity = new UserAnswer();
        entity.setUser(user);
        entity.setStep(step);
        entity.setUserAnswer(userAnswer);
        sessionFactory.getCurrentSession().save(entity);
    }
}
