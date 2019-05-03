package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    private RadioGroup answerGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionIndex = getIntent().getIntExtra("QUESTION_INDEX", 0);
        question = GameManager.getInstance().getQuestion(questionIndex);

        answerGroup = findViewById(R.id.answerGroup);

        questionAnswers = new HashMap<>();

        ((TextView) findViewById(R.id.questionNumber)).setText("Domanda N°" + (questionIndex + 1));
        ((TextView) findViewById(R.id.questionText)).setText(question.getText());

        loadAnswers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Start the countdown
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
        timer.cancel();
    }

    /**
     * Check the pressed answer, called when timer has finished
     */
    private void checkAnswer() {
        View view = findViewById(answerGroup.getCheckedRadioButtonId());

        if(!questionAnswers.containsKey(view))
            return;

        Answer answer = questionAnswers.get(view);
        if(answer == null)
            return;

        GameManager.getInstance().incrementScore(question, answer);
    }

    /**
     * Loads and displays radio buttons for answers.
     * It also binds an answer to a radio button and shows the content.
     */
    private void loadAnswers() {
        List<RadioButton> radioButtons = new ArrayList<>();

        for(Answer answer : question.getAnswers()) {
            RadioButton radio = new RadioButton(this);
            radio.setTextSize(14);
            radio.setLayoutParams(new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    1.0f
            ));
            questionAnswers.put(radio, answer);

            radioButtons.add(radio);

            radio.setText(answer.getText());
        }

        Collections.shuffle(radioButtons);

        for(RadioButton radio : radioButtons)
            answerGroup.addView(radio);
    }
}
