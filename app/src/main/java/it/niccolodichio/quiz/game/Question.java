package it.niccolodichio.quiz.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Niccolò Di Chio
 */
public class Question {

    private String text;
    private List<Answer> answers;

    /**
     * Base constructor for a question
     * @param text Content of the question
     * @param answers Array of answers {@link Answer}
     * @throws Exception Throws an exception if less than 2 answers are inserted in the array OR if count of correct answers is more than 1 or equals to 0
     */
    public Question(String text, Answer... answers) throws Exception {
        this.text = text;

        // Check if answer length are more than 1
        if(answers.length < 2)
            throw new Exception("You have to insert at least 2 answers!");

        // Count the number of correct answers
        int correctCount = 0;
        for(Answer a : answers) {
            if(a.isCorrect())
                correctCount++;
        }

        // Throws an exception if count of correct answers is more than 1 or equals to 0
        if(correctCount > 1 || correctCount == 0)
            throw new Exception("You can not put multiple correct answers in a single question");

        // Create a list from the array
        this.answers = Arrays.asList(answers);
        // Shuffle the list to have some random positioning
        Collections.shuffle(this.answers);
    }

    /**
     * @return The content of the question
     */
    public String getText() {
        return text;
    }

    /**
     * @return The list of the answers (shuffled)
     */
    public List<Answer> getAnswers() {
        return answers;
    }

}
