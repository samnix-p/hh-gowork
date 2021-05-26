package ru.gowork.service;

import ru.gowork.dao.UserAnswerDao;
import ru.gowork.dao.UserDao;
import ru.gowork.entity.Step;
import ru.gowork.entity.User;

import java.util.Optional;

public class SaveAndCheckAnswerService {
    private final UserDao dao;
    private final UserAnswerDao userAnswerDao;

    public SaveAndCheckAnswerService(UserDao dao, UserAnswerDao userAnswerDao) {
        this.dao = dao;
        this.userAnswerDao = userAnswerDao;
    }

    public Object getThis(String token, Object ans) {
        Optional<User> user = dao.getUserBySessionId(token);
        System.out.println("*****");
        User usr = user.get();
        System.out.println(usr.getId());
        System.out.println(usr.getPasswordHash());
        System.out.println(usr.getCurrentStep());
        Step step = usr.getCurrentStep();
        System.out.println("----------------------");
        System.out.println(step.getId());
        System.out.println(step.getAnswersExplanations());
        System.out.println("*****");

        userAnswerDao.saveAnswer(usr, step, ans);

        return step.getAnswersExplanations();
    }
}
