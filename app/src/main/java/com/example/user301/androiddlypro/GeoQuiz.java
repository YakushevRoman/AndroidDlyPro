package com.example.user301.androiddlypro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz extends AppCompatActivity {

    public static final String KEY_INDEX = "index";

    TextView textViewQuestions;

    Button buttonPrevQuestion;
    Button buttonNextQuestion;
    Button buttonFirstAnswer;
    Button buttonSecondAnswer;

    private Questions[] rQuestions;
    private int rCurrentIndex;
    private int rQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz);
        if (savedInstanceState != null ){
            rCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();
        // заполнем массив вопросов
        rQuestions = new Questions[]{
            new Questions(R.string.question_oceans, true),
            new Questions(R.string.question_mideast, false),
            new Questions(R.string.question_africa, false),
            new Questions(R.string.question_americas, true),
            new Questions(R.string.question_asia, true),
        };
        //индекс для начало массива

        textViewQuestions = findViewById(R.id.textViewQuestions);
        textViewQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rCurrentIndex = (rCurrentIndex + 1) % rQuestions.length;
                updateQuestion();
            }
        });
        // выбираем вопрос по индексу
        updateQuestion();

        buttonFirstAnswer = findViewById(R.id.buttonFirstAnswer);
        buttonFirstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedAnswer(true);
            }
        });

        buttonSecondAnswer = findViewById(R.id.buttonSecondAnswer);
        buttonSecondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedAnswer(false);
            }
        });

        buttonNextQuestion = findViewById(R.id.buttonNextQuestion);
        buttonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rCurrentIndex = (rCurrentIndex + 1) % rQuestions.length;
                updateQuestion();
            }
        });

        buttonPrevQuestion = findViewById(R.id.buttonPrevQuestion);
        buttonPrevQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rCurrentIndex = (rCurrentIndex - 1) % rQuestions.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion (){
        rQuestion = rQuestions[rCurrentIndex].getQuestion();
        textViewQuestions.setText(rQuestion);
    }

    private void checkedAnswer (boolean correctAnswer){
        if (correctAnswer == rQuestions[rCurrentIndex].isTrueOrFalse()){
            Toast.makeText(GeoQuiz.this, R.string.button_true,Toast.LENGTH_SHORT)
                    .show();
        }else{
            Toast.makeText(GeoQuiz.this, R.string.button_false,Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, rCurrentIndex);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
