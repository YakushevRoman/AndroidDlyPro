package com.example.user301.androiddlypro;

public class Questions {
    private int question;
    private boolean trueOrFalse;

    public Questions(int question, boolean trueOrFalse) {
        this.question = question;
        this.trueOrFalse = trueOrFalse;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }
}
