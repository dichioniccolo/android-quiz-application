package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CountdownActivity extends AppCompatActivity {

    final private int COUNTDOWN = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int questionIndex = getIntent().getIntExtra("QUESTION_INDEX", 0);

        new CountDownTimer(COUNTDOWN, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.countdown)).setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(CountdownActivity.this, QuestionActivity.class);
                intent.putExtra("QUESTION_INDEX", questionIndex);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
