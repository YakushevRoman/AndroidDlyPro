package com.example.user301.androiddlypro.GeoQuiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user301.androiddlypro.R;

public class ShowAnswerActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "ansver_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "answer_shown";

    TextView textViewShowAnswer;
    Button buttonShowAnswer;

    private boolean rAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);

        rAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);

        textViewShowAnswer = findViewById(R.id.textViewShowAnswer);

        buttonShowAnswer= findViewById(R.id.button_show_answer);
        buttonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rAnswer){
                    textViewShowAnswer.setText(R.string.button_true);
                }else{
                    textViewShowAnswer.setText(R.string.button_false);
                }
                setAnswerResult(true);
            }
        });
    }

    private void setAnswerResult (boolean isAnswerShown){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, intent);
    }

    public static Intent intent (Context context, boolean answer){
        Intent intent = new Intent(context, ShowAnswerActivity.class);
        intent.putExtra(EXTRA_ANSWER, answer);
        return  intent;
    }

    public static boolean getWasAnswerShown ( Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

}
