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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.user301.androiddlypro.R;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    public static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mTitleFiels;
    // new vidjeys
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // generation obj class Crime
        //UUID crimeID = (UUID) getActivity().getIntent().getSerializableExtra(CriminalIntent.EXTRA_CRIME_ID);
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCtime(crimeID);
    }
    // явное заполение фрагмента
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //настраивается реакция виджета на ввод пользователя
        final View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleFiels = view.findViewById(R.id.crime_title);
        mTitleFiels.setText(mCrime.getmTitle());
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
        mDateButton = view.findViewById(R.id.crime_data);
        mDateButton.setText(mCrime.getmDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = view.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });

        return view;
    }

    public static CrimeFragment newInstance (UUID crimeID){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID, crimeID);
        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(bundle);
        return crimeFragment;
    }
}
