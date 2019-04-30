package it.niccolodichio.quiz.game;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {

    private String text;
    private List<Answer> answers;

    public Question(@NonNull String text, @NonNull Answer... answers) {
        this.text = text;

        int correctCount = 0;

        for(Answer a : answers) {
            if(a.isCorrect())
                correctCount++;
        }

        if(correctCount > 1 || correctCount == 0)
            throw new IndexOutOfBoundsException("You can not put multiple correct answers in a single question");

        this.answers = Arrays.asList(answers);

        Collections.shuffle(this.answers);
    }

    public boolean isAnswerCorrect(int answerIndex) {
        if(answerIndex < 0)
            answerIndex = 0;
        else if(answerIndex >= answers.size())
            answerIndex = answers.size() - 1;

        return answers.get(answerIndex).isCorrect();
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

}
