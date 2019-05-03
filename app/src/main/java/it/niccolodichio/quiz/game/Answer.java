package it.niccolodichio.quiz.game;

/**
 * @author Niccolò Di Chio
 */
public class Answer {

    private String text;
    private boolean correct;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }
}
