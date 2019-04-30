package it.niccolodichio.quiz.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Singleton
 * @author Niccolò Di Chio
 */
public class GameManager {
    static private final GameManager instance = new GameManager();

    /**
     * @return The instance of the singleton
     */
    static public GameManager getInstance() {
        return instance;
    }

    // Maximum number of playable questions
    private final int MAX_QUESTIONS = 10;

    private List<Question> allQuestions;
    private List<Question> gameQuestions;
    private int score;

    /**
     * Basic constructor
     */
    private GameManager() {
        allQuestions = new ArrayList<>();
        gameQuestions = new ArrayList<>();
        score = 0;
    }

    /**
     * Get the game questions
     * @return Current game questions
     */
    public List<Question> getQuestions() {
        return gameQuestions;
    }

    /**
     * Add a new question to all the present questions and create a game questions list
     * @param question A question {@link Question}
     */
    public void addQuestion(Question question) {
        if(allQuestions.contains(question))
            return;

        allQuestions.add(question);

        // Shuffle the list of all the questions
        Collections.shuffle(allQuestions);

        // Create a list of questions for the game
        loadGameQuestions();
    }

    /**
     * Load game questions randomly
     */
    private void loadGameQuestions() {
        gameQuestions.clear();
        Random random = new Random();
        for(int i = 0; i < MAX_QUESTIONS && i < allQuestions.size(); i++) {
            Question q;
            do {
                q = allQuestions.get(random.nextInt(allQuestions.size()));
            } while(gameQuestions.contains(q));

            gameQuestions.add(q);
        }
    }

    /**
     * Get the question at index
     * @param index Index of the question
     * @return The question {@link Question} if found otherwise returns null
     */
    public Question getQuestion(int index) {
        if(index >= gameQuestions.size())
            return null;

        return gameQuestions.get(index);
    }

    /**
     * Increment the score
     * @param answer The answer to check {@link Answer}
     */
    public void incrementScore(Answer answer) {
        if(answer.isCorrect())
            score++;
    }

    /**
     * @return The current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Clear the questions after a game
     */
    public void clear() {
        allQuestions.clear();
        gameQuestions.clear();
        score = 0;
    }
}
