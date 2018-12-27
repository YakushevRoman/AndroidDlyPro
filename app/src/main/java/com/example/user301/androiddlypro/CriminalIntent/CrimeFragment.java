package com.example.user301.androiddlypro.CriminalIntent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user301.androiddlypro.R;

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleFiels;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // generation obj class Crime
        mCrime = new Crime();
    }
    // явное заполение фрагмента
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //настраивается реакция виджета на ввод пользователя
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleFiels = view.findViewById(R.id.crime_title);
        mTitleFiels.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            // метод для возврата введенного пользователем заголовка
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
