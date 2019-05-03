package it.niccolodichio.quiz.game;

import java.util.Collections;
import java.util.List;

/**
 * @author Niccolò Di Chio
 */
public class Question {

    private String text;
    private List<Answer> answers;

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
        // Shuffle the list to have some random positioning
        Collections.shuffle(this.answers);
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
