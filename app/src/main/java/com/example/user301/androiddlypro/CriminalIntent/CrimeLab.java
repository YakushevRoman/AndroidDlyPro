package com.example.user301.androiddlypro.CriminalIntent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.user301.androiddlypro.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private  static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    // начальная инициалицация
    private CrimeLab (Context context){
        mCrimes = new ArrayList<>();
        //инициализуруем список
        /*for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setmTitle(context.getString(R.string.crime_list_res_one) + i );
            crime.setmSolved(i%2 == 0);
            mCrimes.add(crime);*/
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
        }
    // возврат обьекта если он еще не находится в памяти
    public static CrimeLab get (Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    // возвращение списка
    public List<Crime> getmCrimes (){
        return mCrimes;
    }
    //
    public Crime getCtime (UUID uuid){
        for (Crime crime : mCrimes){
            if (crime.getmID().equals(uuid)){
                return crime;
            }
        }
        return null;
    }

    public void addCrime (Crime c){
        mCrimes.add(c);
    }

}
