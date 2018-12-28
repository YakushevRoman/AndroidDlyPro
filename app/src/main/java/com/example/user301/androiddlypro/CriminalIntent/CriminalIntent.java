package com.example.user301.androiddlypro.CriminalIntent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user301.androiddlypro.R;

import java.util.UUID;

public class CriminalIntent extends SingleFragmentActivity {

    //public static final String EXTRA_CRIME_ID = "crime_id";
    private static final String EXTRA_CRIME_ID = "crime_id";
    @Override
    protected Fragment createFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(uuid);
    }

    public static Intent newIntent (Context context, UUID uuid){
        Intent intent = new Intent(context, CriminalIntent.class);
        intent.putExtra(EXTRA_CRIME_ID, uuid);
        return intent;
    }

}
