package com.example.android.a1macandroidquiz;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.android.a1macandroidquiz.R.id.bottom;
import static com.example.android.a1macandroidquiz.R.id.entry_answer_edit_text;
import static com.example.android.a1macandroidquiz.R.id.option1_chk_box;
import static com.example.android.a1macandroidquiz.R.id.option1_rado_btn;
import static com.example.android.a1macandroidquiz.R.id.option2_chk_box;
import static com.example.android.a1macandroidquiz.R.id.option2_rado_btn;
import static com.example.android.a1macandroidquiz.R.id.option3_chk_box;
import static com.example.android.a1macandroidquiz.R.id.option3_rado_btn;
import static com.example.android.a1macandroidquiz.R.id.rg_three_options;
import static com.example.android.a1macandroidquiz.R.id.rg_two_options;
import static com.example.android.a1macandroidquiz.R.id.true_rado_btn;

public class QuestionActivity extends AppCompatActivity {

    private int currentQuestionIndx = 0 , shortAnimationDuration;
    private ArrayList<Question> questions = new ArrayList<Question>();

    private EditText entry_answer_edit_text;
    TextView question_title,question_text;
    private RadioGroup rg_three_options ,rg_two_options;
    private LinearLayout chk_box_container,question_main_container,overlay_view;

    private RadioButton option1_rado_btn ,option2_rado_btn ,option3_rado_btn ,true_rado_btn ,false_rado_btn;

    private CheckBox option1_chk_box ,option2_chk_box ,option3_chk_box;

    private MediaPlayer mediaPlayer;
    Button submit_anwser_button;
    Question currentQuestion;
    Quiz quizObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        quizObj= (Quiz) intent.getSerializableExtra("quizObj");
        questions = quizObj.getQuestions();

        overlay_view = (LinearLayout) findViewById(R.id.overlay_view);
        // Initially hide the overlay_view
        overlay_view.setVisibility(View.GONE);
        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = 1500;//getResources().getInteger(android.R.integer.config_longAnimTime);

        question_main_container = (LinearLayout) findViewById(R.id.question_main_container);
        question_title = (TextView) findViewById(R.id.question_title);
        question_text = (TextView) findViewById(R.id.question_text_view);
        submit_anwser_button = (Button) findViewById(R.id.submit_anwser_button);
        submit_anwser_button.setClickable(false);

        //Random r = new Random();
        //currentQuestionIndx = r.nextInt(questions.size());

        //Answer Type elements
        entry_answer_edit_text = (EditText) findViewById(R.id.entry_answer_edit_text);
        rg_three_options = (RadioGroup) findViewById(R.id.rg_three_options);
        rg_two_options = (RadioGroup) findViewById(R.id.rg_two_options);
        chk_box_container = (LinearLayout) findViewById(R.id.chk_box_container);

        option1_rado_btn = (RadioButton) findViewById(R.id.option1_rado_btn);
        option2_rado_btn = (RadioButton) findViewById(R.id.option2_rado_btn);
        option3_rado_btn = (RadioButton) findViewById(R.id.option3_rado_btn);

        option1_chk_box = (CheckBox) findViewById(R.id.option1_chk_box);
        option2_chk_box = (CheckBox) findViewById(R.id.option2_chk_box);
        option3_chk_box = (CheckBox) findViewById(R.id.option3_chk_box);

        //start to display question 1
        displayQuestion(currentQuestionIndx);

    }

    /*
    >> display the content of current question
     */
    public void displayQuestion(int currQuestionIndx){
        //get data from question object
        //and set the xml element
        currentQuestion = (Question) questions.get(currQuestionIndx);
        int qType = currentQuestion.getQType();

        //Question data
        question_title.setText("Round " + String.valueOf(currentQuestion.getQuestionId()));
        question_text.setText(currentQuestion.getQuestionText());

        ImageView question_image = (ImageView) findViewById(R.id.img_question);
        if (currentQuestion.hasImage()){
            // If an image is available, display the provided image based on the resource ID
            question_image.setImageResource(currentQuestion.getQuestionImageId());
            // Make sure the view is visible
            question_image.setVisibility(View.VISIBLE);
        }else {
            // Otherwise hide the ImageView (set visibility to GONE)
            question_image.setVisibility(View.GONE);
        }

        // By default hide all answers options element (set visibility to GONE)
        entry_answer_edit_text.setVisibility(View.GONE);
        rg_three_options.setVisibility(View.GONE);
        rg_two_options.setVisibility(View.GONE);
        chk_box_container.setVisibility(View.GONE);

        String[] options_text;
        switch (qType){
            case 1 :
                //Question type 1 : (three options)(use CheckBoxes ) , two  correct answer(s)
                chk_box_container.setVisibility(View.VISIBLE);

                options_text = currentQuestion.getQType1_OptionArray();

                option1_chk_box.setChecked(false);
                option2_chk_box.setChecked(false);
                option3_chk_box.setChecked(false);

                option1_chk_box.setText(options_text[0]);
                option2_chk_box.setText(options_text[1]);
                option3_chk_box.setText(options_text[2]);

                option1_chk_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (compoundButton.isChecked())
                           submit_anwser_button.setClickable(true);
                    }
                });

                option2_chk_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (compoundButton.isChecked())
                            submit_anwser_button.setClickable(true);
                    }
                });

                option3_chk_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (compoundButton.isChecked())
                            submit_anwser_button.setClickable(true);
                    }
                });
                break;

            case 2 :
                //Question type 2 : (three options)(use  RadioButtons) , one correct answer(s)
                rg_three_options.setVisibility(View.VISIBLE);

                if(rg_three_options.getCheckedRadioButtonId() != -1){
                    //((RadioButton)findViewById(rg_three_options.getCheckedRadioButtonId())).setChecked(false);
                    rg_three_options.clearCheck();
                }

                options_text = currentQuestion.getQType1_OptionArray();

                option1_rado_btn.setText(options_text[0]);
                option2_rado_btn.setText(options_text[1]);
                option3_rado_btn.setText(options_text[2]);

                rg_three_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        submit_anwser_button.setClickable(true);
                    }
                });

                break;

            case 3 :
                //Question type 3 : (true or false ,2 options) (use RadioButtons)
                rg_two_options.setVisibility(View.VISIBLE);

                if(rg_two_options.getCheckedRadioButtonId() != -1){
                    //((RadioButton)findViewById(rg_two_options.getCheckedRadioButtonId())).setChecked(false);
                    rg_two_options.clearCheck();
                }

                rg_two_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        submit_anwser_button.setClickable(true);
                    }
                });
                break;

            case 4 :
                //Question type 4 : (Entry by user) (use Edit text) , with maximum length
                entry_answer_edit_text.setVisibility(View.VISIBLE);

                entry_answer_edit_text.setText("");

                entry_answer_edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        submit_anwser_button.setClickable(true);
                    }
                });
                break;
        }

    }

    /*
    >> this method checks if the selected answer is right
     */
    public void checkAnswer(View view){
        boolean correct = false;

        boolean[] correctAnswer;
        boolean correctAnswerQType3;
        String correctAnswer4;

        switch (currentQuestion.getQType()){
            case 1 :
                correctAnswer = currentQuestion.getQType1_CorrectAnswers();
                if (correctAnswer[0] == option1_chk_box.isChecked() && correctAnswer[1] == option2_chk_box.isChecked() && correctAnswer[2] == option3_chk_box.isChecked()){
                    correct = true;
                }
                break;
            case 2 :
                correctAnswer = currentQuestion.getQType1_CorrectAnswers();
                if (correctAnswer[0] == option1_rado_btn.isChecked() && correctAnswer[1] == option2_rado_btn.isChecked() && correctAnswer[2] == option3_rado_btn.isChecked()){
                    correct = true;
                }
                break;
            case 3 :
                if (currentQuestion.getQType3_CorrectAnswer()){
                    if(rg_two_options.getCheckedRadioButtonId() == R.id.true_rado_btn)
                        correct = true;
                } else{
                    if(rg_two_options.getCheckedRadioButtonId() == R.id.false_rado_btn)
                        correct = true;
                }
                break;
            case 4 :
                if (currentQuestion.getQType4_CorrectTextAnswer().toLowerCase() == entry_answer_edit_text.getText().toString().toLowerCase())
                    correct = true;
                break;
        }

        //display the overlay view which with overlay_view ID (set visibility to VISIBLE)
        //then hide it again during the animation
        TextView result_text = (TextView) findViewById(R.id.result_text);
        if(correct){
            //increment total points by 1
            quizObj.setTotalPonits();
            //update the rightAnswer value to be true
            currentQuestion.setRightAnswer(true);

            result_text.setText("Correct!");
            result_text.setTextColor(getResources().getColor(R.color.colorPrimary));
            mediaPlayer = MediaPlayer.create(this,R.raw.correct);

        }else {
            //update the rightAnswer value to be false
            currentQuestion.setRightAnswer(false);
            result_text.setText("Wrong!");
            result_text.setTextColor(getResources().getColor(R.color.wrong_text));
            mediaPlayer = MediaPlayer.create(this,R.raw.wrong);
        }
        question_main_container.setVisibility(View.GONE);
        mediaPlayer.start();
        crossfade();

        currentQuestionIndx++;
        if (currentQuestionIndx < questions.size()){

            submit_anwser_button.setClickable(false);
            //display the next question
            displayQuestion(currentQuestionIndx);
        }
        else{
            // if all questions have been passed,then negative to Result activity
            Intent i = new Intent(this,ResultActivity.class).putExtra("quizObj",quizObj);
            startActivity(i);
        }


    }

    /*
    >> fade effect animation
     */
    private void crossfade() {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        overlay_view.setAlpha(0f);
        overlay_view.setVisibility(View.VISIBLE);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        overlay_view.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        overlay_view.setVisibility(View.GONE);
                        question_main_container.setVisibility(View.VISIBLE);
                    }
                });
    }
}
