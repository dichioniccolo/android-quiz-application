package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import it.niccolodichio.quiz.game.GameManager;

/**
 * @author Niccolò Di Chio
 */
public class ResultActivity extends AppCompatActivity {

    /**
     * The last activity shown after the last question
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ((RatingBar) findViewById(R.id.ratingBar)).setRating(GameManager.getInstance().getScore() / 2);
    }

    /**
     * Method bound to the button "Play again"
     * @param view
     */
    public void playAgain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
