package it.niccolodichio.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        GameManager m = GameManager.getInstance();
        try {
            m.addQuestion(new Question("Il fondatore di Amazon si chiama:",
                    new Answer("Jeff bezos", true),
                    new Answer("Topo Gigio", false),
                    new Answer("Stefano Lavori", false)));
            m.addQuestion(new Question("La famosa città di Boston si trova in:",
                    new Answer("Canada", false),
                    new Answer("Namek", false),
                    new Answer("USA", true)));
            m.addQuestion(new Question("Heidi Klum è una: ",
                    new Answer("Atleta", false),
                    new Answer("Pittrice", false),
                    new Answer("Modella", true)));
            m.addQuestion(new Question("Jude Law interpreta uno dei personaggi di un film di quest'anno, qual'è il film?",
                    new Answer("Animali Fantastici", true),
                    new Answer("Sherlock", false),
                    new Answer("Star Trek", false)));
            m.addQuestion(new Question("Qual è il nome della donna alla quale è stata dedicata la poesia 'La pioggia nel pineto'?",
                    new Answer("Elena", false),
                    new Answer("Ermione", false),
                    new Answer("Eleonora", true)));
            m.addQuestion(new Question("Esiste un trattato il cui acronimo è anche un derivato del petrolio che coinvolge:",
                    new Answer("USA, Canada e Messico", true),
                    new Answer("Arabia Saudita, UAE e Bahrein", false),
                    new Answer("Venezuela, Colombia ed Ecuador", false)));
            m.addQuestion(new Question("Dove vivevano gli Illiri?",
                    new Answer("Mesopotamia", false),
                    new Answer("Penisola balcanica", true),
                    new Answer("Africa Settentrionale", false)));
            m.addQuestion(new Question("Quale nazione è l'attuale campione mondiale dell'hockey della sua versione 'verde'?",
                    new Answer("Argentina", false),
                    new Answer("Australia", true),
                    new Answer("Inghilterra", false)));
            m.addQuestion(new Question("Marco Travaglio ha curato la prefazione del libro di quale cantante?",
                    new Answer("Renato Zero", false),
                    new Answer("Vinicio Capossela", false),
                    new Answer("Fabri Fibra", true)));
            m.addQuestion(new Question("Quale di questi elementi ha a che fare con la teoria della relatività?",
                    new Answer("Vettore di Poynting", false),
                    new Answer("Diagramma di Penrose", true),
                    new Answer("Principio di Mach", false)));
            m.addQuestion(new Question("Quale di queste coppie ha condotto il festival di Sanremo?",
                    new Answer("Gianni e Pinotto", false),
                    new Answer("Dr. Jekyll e Mr. Hyde", false),
                    new Answer("Michelle Hunziker e Pippo Baudo", true)));
            m.addQuestion(new Question("Dove è ambientata la sit-com 'Camera Café'?",
                    new Answer("In un ospedale", false),
                    new Answer("In un'azienda", true),
                    new Answer("In una stazione", false)));
            m.addQuestion(new Question("Quando si celebra il 'Columbus Day' negli Stati Uniti",
                    new Answer("Secondo lunedì di ottobre", true),
                    new Answer("Primo martedì di novembre", false),
                    new Answer("Ultima domenica di settembre", false)));
            m.addQuestion(new Question("In che parte dell'Australia si trova la città di Perth?",
                    new Answer("Nord-Est", false),
                    new Answer("Sud-Ovest", true),
                    new Answer("Sud-Est", false)));
            m.addQuestion(new Question("Chi ha scritto 'Morte sol mi darà fama e riposo'?",
                    new Answer("Alessandro Manzoni", false),
                    new Answer("Giacomo Leopardi", false),
                    new Answer("Ugo Foscolo", true)));
            m.addQuestion(new Question("Per quale testata scriveva Malala Yousafzai, premio Nobel per la Pace 2014?",
                    new Answer("BBC", true),
                    new Answer("The New York Times", false),
                    new Answer("The Washington Post", false)));
            m.addQuestion(new Question("Quanto dura la memoria di un comune pesce rosso?",
                    new Answer("Alcune ore", false),
                    new Answer("Decine di giorni", true),
                    new Answer("Pochi secondi", false)));
            m.addQuestion(new Question("In che anno venne presentato il compact disc?",
                    new Answer("1979", true),
                    new Answer("1982", false),
                    new Answer("1983", false)));
            m.addQuestion(new Question("Quale tra queste carte del gioco 'Magic - The Gathering' NON esiste?",
                    new Answer("Vampiro Mago Notturno", true),
                    new Answer("Incubo", false),
                    new Answer("Cavaliere su Pecolepre", false)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private void loadQuestions() {
        GameManager gm = GameManager.getInstance();

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(getAssets().open("questions.txt")));

            String line;
            while((line = bf.readLine()) != null) {
                List<Answer> answers = new ArrayList<>();
                String[] parsed = line.split("§");
                for(int i = 1; i < parsed.length; i++) {
                    String[] answer = parsed[i].split(";");
                    answers.add(new Answer(answer[0], Boolean.parseBoolean(answer[1])));
                }

                gm.addQuestion(new Question(parsed[0], answers));
            }
        } catch(Exception e) {
            Log.d("ERRORE", e.getMessage());
        }
    }*/
}
