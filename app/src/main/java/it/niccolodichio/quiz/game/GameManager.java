package it.niccolodichio.quiz.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Niccolò Di Chio
 */
public class GameManager {
    static private final GameManager instance = new GameManager();

    static public GameManager getInstance() {
        return instance;
    }

    final private int MAX_QUESTIONS = 5;

    private List<Question> allQuestions;
    private List<Question> gameQuestions;
    private int score;

    private GameManager() {
        allQuestions = new ArrayList<>();
        gameQuestions = new ArrayList<>();
        score = 0;
    }

    public List<Question> getQuestions() {
        return gameQuestions;
    }

    public void addQuestion(Question question) {
        if(allQuestions.contains(question))
            return;

        allQuestions.add(question);

        Collections.shuffle(allQuestions);

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

    public Question getQuestion(int index) {
        if(index >= gameQuestions.size())
            return null;

        return gameQuestions.get(index);
    }

    public void incrementScore(Question question, Answer answer) {
        if(question.isCorrect(answer))
            score++;
    }

    public int getScore() {
        return score;
    }

    /**
     * Clear the questions and reset the score after a game
     */
    public void clear() {
        allQuestions.clear();
        gameQuestions.clear();
        score = 0;
    }
}
