package it.niccolodichio.quiz.game;

import java.util.List;

/**
 * @author Niccolò Di Chio
 */
public class Question {

    private String text;
    private List<Answer> answers;
    private int correctAnswerIndex;

    public Question(String text, List<Answer> answers, int correctAnswerIndex) {
        this.text = text;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isCorrect(Answer answer) {
        int index = 0;

        for(Answer a : answers) {
            if(a.equals(answer))
                return index == correctAnswerIndex;
            index++;
        }
        return false;
    }
}
