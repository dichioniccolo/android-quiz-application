package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import it.niccolodichio.quiz.game.Answer;
import it.niccolodichio.quiz.game.GameManager;
import it.niccolodichio.quiz.game.Question;

public class QuestionActivity extends AppCompatActivity {

    private Question question;
    private Map<View, Answer> questionAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionAnswers = new HashMap<>();

        final int questionIndex = getIntent().getIntExtra("QUESTION_INDEX", 0);
        final int nextQuestionIndex = questionIndex + 1;

        question = GameManager.getInstance().getQuestion(questionIndex);

        final Button nextButton = findViewById(R.id.nextQuestion);

        if(GameManager.getInstance().getQuestion(nextQuestionIndex) != null) {
            nextButton.setText("Prossima domanda");
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                    Intent intent = new Intent(QuestionActivity.this, QuestionActivity.class);
                    intent.putExtra("QUESTION_INDEX", nextQuestionIndex);
                    startActivity(intent);
                }
            });
        } else {
            nextButton.setText("Fine");
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer();
                    startActivity(new Intent(QuestionActivity.this, ResultActivity.class));
                }
            });
        }

        ((TextView) findViewById(R.id.questionNumber)).setText("Domanda N°" + (nextQuestionIndex));
        ((TextView) findViewById(R.id.questionText)).setText(question.getText());

        loadAnswers();
    }

    private void checkAnswer() {
        RadioGroup group = findViewById(R.id.answerGroup);
        View view = findViewById(group.getCheckedRadioButtonId());

        if(!questionAnswers.containsKey(view))
            return;

        Answer answer = questionAnswers.get(view);
        if(answer == null)
            return;

        if(answer.isCorrect())
            GameManager.getInstance().incrementScore();
    }

    private void loadAnswers() {
        int answerIndex = 0;

        switch(question.getAnswers().size()) {
            case 1:
                findViewById(R.id.answerTwo).setVisibility(View.INVISIBLE);
                findViewById(R.id.answerThree).setVisibility(View.INVISIBLE);
                findViewById(R.id.answerFour).setVisibility(View.INVISIBLE);
                break;
            case 2:
                findViewById(R.id.answerFour).setVisibility(View.INVISIBLE);
                findViewById(R.id.answerThree).setVisibility(View.INVISIBLE);
                break;
            case 3:
                findViewById(R.id.answerFour).setVisibility(View.INVISIBLE);
                break;
        }

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
