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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameManager.getInstance().clear();

        loadQuestions();
    }

    /**
     * Method attached to the button "Start Game"
     * @param view
     */
    public void startGame(View view) {
        startActivity(new Intent(this, CountdownActivity.class));
        finish();
    }

    /**
     * Load all the questions from JSON file
     */
    private void loadQuestions() {
        GameManager gm = GameManager.getInstance();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open("questions.json")))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = bf.readLine()) != null)
                sb.append(line).append("\n");

            JSONObject data = new JSONObject(sb.toString());
            JSONArray questionsJson = data.getJSONArray("data");

            for(int i = 0; i < questionsJson.length(); i++) {
                JSONObject questionObject = questionsJson.getJSONObject(i);
                String questionText = questionObject.getString("text");
                JSONArray answersJson = questionObject.getJSONArray("answers");

                List<Answer> questionAnswers = new ArrayList<>();
                int correctAnswerIndex = -1;
                for(int j = 0; j < answersJson.length(); j++) {
                    JSONObject singleAnswerJson = answersJson.optJSONObject(j);

                    String answerText;

                    if(singleAnswerJson == null) {
                        answerText = answersJson.getString(j);
                    } else {
                        answerText = singleAnswerJson.getString("text");
                        correctAnswerIndex = j;
                    }

                    questionAnswers.add(new Answer(answerText));
                }

                if(correctAnswerIndex == -1)
                    return;

                gm.addQuestion(new Question(questionText, questionAnswers, correctAnswerIndex));
            }
        } catch (Exception e) {
            Log.d("ERRORE", e.getMessage());
        }
    }
}
