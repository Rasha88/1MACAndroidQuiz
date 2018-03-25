package com.example.android.a1macandroidquiz;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by  @ameenhazza55 , @Mohammed.Jassim , @rahali
 * on 22/03/2018.
 */

public class Quiz implements Serializable {
    private int mQuizId;
    private String mTitle;
    private ArrayList<Question> mQuestions = new ArrayList<Question>();
    private int mTotalPonits = 0;

    public Quiz(int quizId, String title,ArrayList<Question> questions){
        mQuizId = quizId;
        mTitle = title;
        mQuestions = questions;
    }

    public int getQuizId() {
        return mQuizId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
         mTitle = title;
    }

    public ArrayList<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        mQuestions = questions;
    }

    public int getTotalPonits() {
        return mTotalPonits;
    }

    public void setTotalPonits() {
        mTotalPonits++;
    }
}
