package ru.gowork.service;

import ru.gowork.dao.UserAnswerDao;
import ru.gowork.dao.UserDao;
import ru.gowork.entity.Step;
import ru.gowork.entity.User;
import ru.gowork.entity.UserAnswer;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class AnswerService {
    private final UserAnswerDao userAnswerDao;
    private final UserDao userDao;

    public AnswerService(UserAnswerDao userAnswerDao, UserDao userDao) {
        this.userAnswerDao = userAnswerDao;
        this.userDao = userDao;
    }

    public Object saveAnswerGetExplanation(String session, String answer) {
        Optional<User> userOptional = userDao.getUserBySessionId(session);
        User user = userOptional.get();
        Step step = user.getCurrentStep();
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user);
        userAnswer.setStep(step);
        userAnswer.setAnswer(answer);
        userAnswerDao.saveAnswer(userAnswer);
        return step.getAnswersExplanations();
    }
}
