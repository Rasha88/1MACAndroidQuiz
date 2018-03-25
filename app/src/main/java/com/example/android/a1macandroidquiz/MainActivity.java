package com.example.android.a1macandroidquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    ListView quizList;
    ArrayList<Question> questions = new ArrayList<Question>();
    ArrayList<Quiz> quizzes;
    Activity context = this;
    String question_text = "";
    String[] options ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizzes = new ArrayList<Quiz>();

        //add quiz to quizzes list, and add questions foreach quiz
        //-------------initialize the question array of quiz 1 :-----------------//
        /*============ question 1 type 2 ==============*/
        question_text = "Which of the following is not true about <activity> tag in AndroidManifest file?";
        options = new String[] {"Declares an activity that implements part of the application’s visual user interface.",
                                "Contained in <application> tag.",
                                "Declares a single hardware or software feature that is used by the application."};
        questions.add(new Question(1,2 ,question_text, -1 ,options,new boolean[] {false, false,true}));

        /*============ question 2 type 1 ==============*/

        question_text = "Which of these is NOT recommended in the Android Developer’s Guide as a method of creating\n" +
                "an individual View?";
        options = new String[] {"Create by extending the android.view.View class.",
                "Create by copying the source of an already existing View class such as Button or TextView.",
                "Create by extending already existing View classes such as Button or TextView."};

        questions.add(new Question(2,2 ,question_text, -1 ,options,new boolean[] {false, true, false}));

        /*============ question 3 type 3 ==============*/
        questions.add(new Question(3,3 ,"Text of Q3 ", -1 ,true));

        quizzes.add(new Quiz(1, "Building Layouts",questions ));

        //-------------initialize the question array of quiz 2 :-----------------//
        quizzes.add(new Quiz(2, "Making an App Interactive",questions));
        //-------------initialize the question array of quiz 3 :-----------------//
        quizzes.add(new Quiz(3, "Object-Oriented Programming",questions ));
        //-------------initialize the question array of quiz 4 :-----------------//
        quizzes.add(new Quiz(4, "Intents and Activities",questions));
        //-------------initialize the question array of quiz 5 :-----------------//
        quizzes.add(new Quiz(5, "Arrays, Lists, Loops, & Custom Classes" ,questions));
        //-------------initialize the question array of quiz 6 :-----------------//
        quizzes.add(new Quiz(6, "Intents and Activities",questions));

        QuizAdapter adapter = new QuizAdapter(this,quizzes);
        quizList = (ListView) findViewById(R.id.quiz_list);
        quizList.setAdapter(adapter);

        quizList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Quiz selectedQuiz = (Quiz) parent.getItemAtPosition(position);
                Intent i = new Intent(context,QuestionActivity.class).putExtra("quizObj",selectedQuiz);
                startActivity(i);
            }
        });

    }
}
