package com.epam.engx.cleancode.functions.task2;


import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Level;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.NotActivUserException;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Review;
import com.epam.engx.cleancode.functions.task2.thirdpartyjar.User;

import java.util.TreeMap;

public abstract class Account implements User {

    private TreeMap<Integer, Level> levelMap = new TreeMap<>();

    public Level getActivityLevel() {
        validateAccountForLevelCalculation();
        int reviewAnswerCount = countReviewAnswers();
        return findLevelByReviewAnswers(reviewAnswerCount);
    }

    private int countReviewAnswers() {
        int reviewAnswerCount = 0;
        for (Review review : getAllReviews()) {
            reviewAnswerCount += review.getAnswers().size();
        }
        return reviewAnswerCount;
    }

    private Level findLevelByReviewAnswers(int reviewAnswerCount) {
        for (Integer threshold : levelMap.keySet()) {
            if (reviewAnswerCount >= threshold) {
                return levelMap.get(threshold);
            }
        }

        return Level.defaultLevel();
    }

    private void validateAccountForLevelCalculation() {
        if (!isRegistered() || getVisitNumber() <= 0) {
            throw new NotActivUserException();
        }
    }

    public void setLevelMap(TreeMap<Integer, Level> levelMap) {
        this.levelMap = levelMap;
    }
}
