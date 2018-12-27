package com.example.user301.androiddlypro.CriminalIntent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user301.androiddlypro.R;

public class CriminalIntent extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_intent);
        //получаем фрагмент менеджер
        FragmentManager fragmentManager = getSupportFragmentManager();
        //импортирование фрагмента в код
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null){
            fragment = new CrimeFragment();
            fragmentManager.beginTransaction()
                    //создание и закрепление транцакции
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
