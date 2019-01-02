package com.example.user301.androiddlypro.CriminalIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    public static final String ARG_CRIME_ID = "crime_id";
    public static final String DIALOG_DATE = "DialogDate";
    public static final int REQUEST_DATE = 0;

    private Crime mCrime;
    private EditText mTitleFiels;
    // new vidjeys
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    private FragmentManager fragmentManager;
    private DataPickerFragment dataPickerFragment;

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
        updateData();
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                //dataPickerFragment = new DataPickerFragment();
                dataPickerFragment = DataPickerFragment.newInstance(mCrime.getmDate());
                dataPickerFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dataPickerFragment.show(fragmentManager, DIALOG_DATE);
            }
        });

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

    private void updateData() {
        mDateButton.setText(mCrime.getmDate().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DataPickerFragment.EXTRA_DATE);
            mCrime.setmDate(date);
            updateData();
        }
    }

    public static CrimeFragment newInstance (UUID crimeID){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID, crimeID);
        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(bundle);
        return crimeFragment;
    }
}
