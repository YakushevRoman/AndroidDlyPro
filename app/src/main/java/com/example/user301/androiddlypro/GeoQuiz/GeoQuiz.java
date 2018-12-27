package com.example.user301.androiddlypro.GeoQuiz;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user301.androiddlypro.R;

public class GeoQuiz extends AppCompatActivity {
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final String KEY_INDEX = "index";

    TextView textViewQuestions;

    Button buttonPrevQuestion;
    Button buttonNextQuestion;
    Button buttonFirstAnswer;
    Button buttonSecondAnswer;

    Button buttonCheap;

    private Questions[] rQuestions;
    private int rCurrentIndex = 0;
    private boolean mIsCheater;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz);

        if (savedInstanceState != null ){
            rCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        //updateQuestion();
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

        buttonCheap = findViewById(R.id.buttonCheap);
        buttonCheap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answer = rQuestions[rCurrentIndex].isTrueOrFalse();
                intent = ShowAnswerActivity.intent(GeoQuiz.this, answer);
                //startActivity(intent);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK){
            return;
        }else {
            if (intent == null){
                return;
            }
            mIsCheater = ShowAnswerActivity.getWasAnswerShown(intent);
        }

    }

    private void updateQuestion (){
        int rQuestion = rQuestions[rCurrentIndex].getQuestion();
        textViewQuestions.setText(rQuestion);
    }

    private void checkedAnswer (boolean correctAnswer){
        boolean answerIsTrue = rQuestions[rCurrentIndex].isTrueOrFalse();
        int messageResId = 0;
        if (mIsCheater){
            messageResId = R.string.judgment_toast;
        }else {
            if (correctAnswer == rQuestions[rCurrentIndex].isTrueOrFalse()){
                messageResId = R.string.button_true;
            }else{
                messageResId = R.string.button_false;
            }
        }
        Toast.makeText(GeoQuiz.this, messageResId,Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, rCurrentIndex);
    }
}
