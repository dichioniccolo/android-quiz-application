package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import it.niccolodichio.quiz.game.Answer;
import it.niccolodichio.quiz.game.GameManager;
import it.niccolodichio.quiz.game.Question;

/**
 * @author Niccolò Di Chio
 */
public class QuestionActivity extends AppCompatActivity {

    final private int COUNTDOWN = 11000;

    private int questionIndex;
    private Question question;
    private Map<View, Answer> questionAnswers;
    private CountDownTimer timer;

    /**
     * The question activity called for every game question
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // The current question index
        questionIndex = getIntent().getIntExtra("QUESTION_INDEX", 0);

        // The current question object
        question = GameManager.getInstance().getQuestion(questionIndex);
        questionAnswers = new HashMap<>();

        // Set quetion number
        ((TextView) findViewById(R.id.questionNumber)).setText("Domanda N°" + (questionIndex + 1));
        // Set question content
        ((TextView) findViewById(R.id.questionText)).setText(question.getText());

        // Loads the answers
        loadAnswers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Starts the countdown
        timer = new CountDownTimer(COUNTDOWN, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.countdown)).setText(format(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                checkAnswer();

                Intent intent;
                if(GameManager.getInstance().getQuestion(questionIndex + 1) != null) {
                    intent = new Intent(QuestionActivity.this, CountdownActivity.class);
                    intent.putExtra("QUESTION_INDEX", questionIndex + 1);
                } else {
                    intent = new Intent(QuestionActivity.this, ResultActivity.class);
                }
                startActivity(intent);
                finish();
            }

            private String format(long seconds) {
                return String.format(Locale.ENGLISH, "%02d", seconds / 60)
                        + ":" + String.format(Locale.ENGLISH, "%02d", seconds % 60);
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Cancel the countdown when pause activity
        timer.cancel();
    }

    /**
     * Check the presset answer, called when next/finish button is clicked
     */
    private void checkAnswer() {
        RadioGroup group = findViewById(R.id.answerGroup);
        View view = findViewById(group.getCheckedRadioButtonId());

        if(!questionAnswers.containsKey(view))
            return;

        // The selected answer
        Answer answer = questionAnswers.get(view);
        if(answer == null)
            return;

        // Increments the score (only if answer is correct)
        GameManager.getInstance().incrementScore(answer);
    }

    /**
     * Loads and displays all answers
     */
    private void loadAnswers() {
        // Display the correct amount of radio buttons based on number of answers in the question
        switch(question.getAnswers().size()) {
            case 2:
                findViewById(R.id.answerFour).setVisibility(View.INVISIBLE);
                findViewById(R.id.answerThree).setVisibility(View.INVISIBLE);
                break;
            case 3:
                findViewById(R.id.answerFour).setVisibility(View.INVISIBLE);
                break;
        }


        int answerIndex = 0;

        // Bind an answer to a radio button and shows the answer content
        for(Answer answer : question.getAnswers()) {
            RadioButton radio = null;
            switch(answerIndex++) {
                case 0:
                    radio = findViewById(R.id.answerOne);
                    break;
                case 1:
                    radio = findViewById(R.id.answerTwo);
                    break;
                case 2:
                    radio = findViewById(R.id.answerThree);
                    break;
                case 3:
                    radio = findViewById(R.id.answerFour);
                    break;
                default:
                    return;
            }

            if(radio == null)
                break;

            questionAnswers.put(radio, answer);

            radio.setText(answer.getText());
        }
    }
}
