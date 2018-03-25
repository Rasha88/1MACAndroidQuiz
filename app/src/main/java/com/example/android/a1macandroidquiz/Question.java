package com.example.android.a1macandroidquiz;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.right;

/**
 * Created by  @ameenhazza55 , @Mohammed.Jassim , @rahali
 * on 22/03/2018.
 */

public class Question implements Serializable {
    private static final int NO_IMAGE = -1;
    private int mQuestionId;
    private String mQuestionText;
    private int mQuestionImageId = NO_IMAGE;

    private int mQType ; // valid values : 1 , 2 , 3,4
    private boolean mRightAnswer = false;

    //Question type 1 : (three options)(use CheckBoxes ) , two  correct answer(s)
    //Question type 2 : (three options)(use  RadioButtons) , one correct answer(s)
    private String[] mQType1_OptionArray ;
    private boolean[] mQType1_CorrectAnswers ;

    //Question type 3 : (true or false ,2 options) (use RadioButtons)
    private boolean mQType3_CorrectAnswer;

    //Question type 4 : (Entry by user) (use Edit text) , with maximum length
    private String mQType4_CorrectTextAnswer = "";
    private int mQType4_maxLength = 0;

    //Question type 1,2 constructor
    public Question( int questionId, int qType, String questionText, int questionImageId, String[] optionArray, boolean[] correctAnswers){
        mQuestionId = questionId;
        mQuestionText = questionText;
        mQuestionImageId = questionImageId;
        mQType1_OptionArray = optionArray;
        mQType1_CorrectAnswers = correctAnswers;
        mQType = qType;
    }

    //Question type 3 constructor
    public Question( int questionId,  int qType, String questionText, int questionImageId,  boolean correctAnswer){
        mQuestionId = questionId;
        mQuestionText = questionText;
        mQuestionImageId = questionImageId;
        mQType3_CorrectAnswer = correctAnswer;
        mQType = qType;
    }

    //Question type 4 constructor
    public Question( int questionId,  int qType ,String questionText, int questionImageId,  String correctAnswer, int maxLength){
        mQuestionId = questionId;
        mQuestionText = questionText;
        mQuestionImageId = questionImageId;
        mQType4_CorrectTextAnswer = correctAnswer;
        mQType4_maxLength = maxLength;
        mQType = qType;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public String getQuestionText() {
        return mQuestionText;
    }

    public boolean isRightAnswer() {
        return mRightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.mRightAnswer = rightAnswer;
    }

    /**
     * Returns whether or not there is an image for this question.
     */
    public boolean hasImage() {
        return mQuestionImageId != NO_IMAGE;
     }

    public int getQuestionImageId() {
        return mQuestionImageId;
    }

    public int getQType() {
        return mQType;
    }

    public String[] getQType1_OptionArray() {
        return mQType1_OptionArray;
    }

    public boolean[] getQType1_CorrectAnswers() {
        return mQType1_CorrectAnswers;
    }

    public boolean getQType3_CorrectAnswer() {
        return mQType3_CorrectAnswer;
    }

    public String getQType4_CorrectTextAnswer() {
        return mQType4_CorrectTextAnswer;
    }

    public int getQType4_maxLength() {
        return mQType4_maxLength;
    }
}
