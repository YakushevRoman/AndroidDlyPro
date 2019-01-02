package com.example.user301.androiddlypro.CriminalIntent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.user301.androiddlypro.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataPickerFragment extends DialogFragment {

    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "criminal_intent";

    private DatePicker mDatePicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mount = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_picker, null);

        mDatePicker = view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, mount, day, null);

        return new AlertDialog.Builder(getActivity())
                //.setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int mount = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date1 = new GregorianCalendar(year,mount,day).getTime();
                        sendResult(Activity.RESULT_OK, date1);
                    }
                })
                .setTitle("Date of crime")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    public  static  DataPickerFragment newInstance (Date date){

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DATE, date);

        DataPickerFragment dataPickerFragment = new DataPickerFragment();
        dataPickerFragment.setArguments(bundle);

        return dataPickerFragment;
    }

    private void sendResult (int resultCode, Date date){
        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
