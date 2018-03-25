package com.example.android.a1macandroidquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.a1macandroidquiz.R.id.rounds_list;
import static com.example.android.a1macandroidquiz.R.id.total_point_text_view;

public class ResultActivity extends AppCompatActivity {

    Quiz quizObj;
    ArrayList<Question> questions;
    String msg_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        quizObj= (Quiz) intent.getSerializableExtra("quizObj");
        questions = quizObj.getQuestions();

        int total_point = quizObj.getTotalPonits();
        TextView total_point_text_view = (TextView) findViewById(R.id.total_point_text_view);
        total_point_text_view.setText(String.valueOf(total_point));

        if (total_point == questions.size()){
            msg_result = "congratulation \n on having passed the quiz successfully!";
        }else if(total_point < questions.size() &&  total_point !=0 ){
            msg_result = "Good!";
        }else{
            // total_point == 0
            msg_result = "Good Luck in next time!";
        }
        TextView result_msg_text_view = (TextView) findViewById(R.id.msg_result);
        result_msg_text_view.setText(msg_result);

        //create the result table
        LinearLayout rounds_list = (LinearLayout) findViewById(R.id.rounds_list);
        LinearLayout icons_list = (LinearLayout) findViewById(R.id.icons_list);
        int i = 0;
        Question currentQ;
        while (i< questions.size()){
            currentQ = questions.get(i);

            TextView textView = new TextView(this);
            textView.setText(" Round " + String.valueOf(currentQ.getQuestionId()));
            textView.setTextSize(18);
            textView.setPadding(0,0,16,16);
            textView.setGravity(Gravity.END);
            rounds_list.addView(textView);

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(64,64);
            imageView.setLayoutParams(parms);
            imageView.setPadding(16,0,0,16);

            if (currentQ.isRightAnswer()){

                imageView.setImageResource(R.mipmap.right);

            }else {

                imageView.setImageResource(R.mipmap.wrong);
            }
            icons_list.addView(imageView);


            i++;
        }
    }
    public void viewCorrectAnswers(View view){
        Intent i = new Intent(this,CorrectAnswersActivity.class).putExtra("quizObj",quizObj);
        startActivity(i);
    }
}
