package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import it.niccolodichio.quiz.game.Answer;
import it.niccolodichio.quiz.game.GameManager;
import it.niccolodichio.quiz.game.Question;

/**
 * @author Niccolò Di Chio
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The main activity called when the app is launched
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameManager.getInstance().clear();

        loadQuestions();
    }

    /**
     * Method bound to the button "Start Game"
     * @param view
     */
    public void startGame(View view) {
        startActivity(new Intent(this, CountdownActivity.class));
        finish();
    }

    /**
     * Load all the questions
     */
    private void loadQuestions() {
        GameManager gm = GameManager.getInstance();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open("questions.json")))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = bf.readLine()) != null)
                sb.append(line).append("\n");

            JSONObject data = new JSONObject(sb.toString());
            JSONArray questionsArray = data.getJSONArray("data");

            for(int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionObject = questionsArray.getJSONObject(i);
                String questionText = questionObject.getString("text");
                JSONArray answers = questionObject.getJSONArray("answers");

                List<Answer> questionAnswers = new ArrayList<>();
                for(int j = 0; j < answers.length(); j++) {
                    JSONObject answer = answers.getJSONObject(j);

                    String answerText = answer.getString("text");
                    boolean answerCorrect = answer.getBoolean("correct");
                    questionAnswers.add(new Answer(answerText, answerCorrect));
                }

                gm.addQuestion(new Question(questionText, questionAnswers));
            }
        } catch (Exception e) {
            Log.d("ERRORE", e.getMessage());
        }
    }
}
