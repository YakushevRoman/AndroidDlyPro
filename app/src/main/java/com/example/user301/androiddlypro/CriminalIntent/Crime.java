package com.example.user301.androiddlypro.CriminalIntent;

import java.util.UUID;

public class Crime {

    private UUID mID;
    private String mTitle;

    public Crime() {
        // генерирование уникального идентификатора
        mID = UUID.randomUUID();
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
}
