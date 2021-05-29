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

    public Optional<Object> saveAnswerGetExplanation(String userEmail, String answer) {
        Optional<User> userOptional = userDao.getUserByEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Step step = user.getCurrentStep();
            UserAnswer userAnswer = new UserAnswer(user, step, answer);
            userAnswerDao.saveAnswer(userAnswer);
            return Optional.of(step.getAnswersExplanations());
        }
        return Optional.empty();
    }

}
