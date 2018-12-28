package com.example.user301.androiddlypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user301.androiddlypro.CriminalIntent.CrimeListActivity;
import com.example.user301.androiddlypro.CriminalIntent.CriminalIntent;
import com.example.user301.androiddlypro.GeoQuiz.GeoQuiz;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    Button buttonGeoQuiz;
    Button buttonCriminalIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGeoQuiz = findViewById(R.id.buttonGeoQuiz);
        buttonGeoQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, GeoQuiz.class);
                startActivity(intent);
            }
        });

        buttonCriminalIntent = findViewById(R.id.buttonCriminalIntent);
        buttonCriminalIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CrimeListActivity.class);
                startActivity(intent);
            }
        });

    }
}
