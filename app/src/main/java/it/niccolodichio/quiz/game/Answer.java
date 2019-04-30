package it.niccolodichio.quiz.game;

/**
 * @author Niccolò Di Chio
 */
public class Answer {

    private String text;
    private boolean correct;

    /**
     * Basic constructor to create an answer
     * @param text The content of the answer
     * @param correct If it is a correct answer (You can not put more than one answer per question)
     */
    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    /**
     * @return The content of the answer
     */
    public String getText() {
        return text;
    }

    /**
     * @return Answer is correct or not
     */
    public boolean isCorrect() {
        return correct;
    }
}
