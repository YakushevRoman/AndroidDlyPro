package com.example.user301.androiddlypro.CriminalIntent;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mID;
    private String mTitle;
    // add two
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        // генерирование уникального идентификатора
        mID = UUID.randomUUID();
        mDate = new Date();
    }
    // генерирование гет - сет методов
    public UUID getmID() {
        return mID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
