package com.example.m14x.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.startButton)
    Button startButton;
    @Bind(R.id.sumTextView)
    TextView sumTextView;
    @Bind(R.id.resultTextView)
    TextView resultTextView;
    @Bind(R.id.pointsTextView)
    TextView pointsTextView;
    @Bind(R.id.timerTextView)
    TextView timerTextView;
    @Bind(R.id.button0)
    Button button0;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.playAgainButton)
    Button playAgainButton;
    @Bind(R.id.gameLayout)
    RelativeLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctAnswerLocation;
    int score = 0;
    int numberOfQuestions = 0;
    boolean gameIsActive = true;

    @OnClick(R.id.playAgainButton)
    public void playAgain(View view){

            gameIsActive = true;
            score = 0;
            numberOfQuestions = 0;
            timerTextView.setText("30s");
            pointsTextView.setText("0/0");
            resultTextView.setText("");
            playAgainButton.setVisibility(View.INVISIBLE);
            generateQuestions();
            new CountDownTimer(30100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0s");
                    resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                    playAgainButton.setVisibility(View.VISIBLE);
                    gameIsActive = false;
                }
            }.start();
        }

    @OnClick(R.id.startButton)
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void generateQuestions(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        correctAnswerLocation = rand.nextInt(4);
        int incorrectAnswer = 0;
        answers.clear();

        for(int i = 0; i < 4; i++){
            if(i == correctAnswerLocation){
                answers.add(a + b);
            }else{

                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


        //sdfaffffffffff


    }



    public void chooseAnswer(View view){
        if(gameIsActive) {
            if (view.getTag().toString().equals(Integer.toString(correctAnswerLocation))) {
                score++;
                resultTextView.setText("Correct!");
            } else {
                resultTextView.setText("Wrong!");
            }
            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generateQuestions();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
