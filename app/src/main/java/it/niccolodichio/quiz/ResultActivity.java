package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import it.niccolodichio.quiz.game.GameManager;

/**
 * @author Niccolò Di Chio
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ((TextView) findViewById(R.id.gameScore)).setText(String.format(
                        Locale.ENGLISH,
                        "%d/%d",
                        GameManager.getInstance().getScore(),
                        GameManager.getInstance().getQuestions().size()));
    }

    /**
     * Method attached to the button "Play Again"
     * @param view
     */
    public void playAgain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
