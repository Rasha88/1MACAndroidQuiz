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
        ArrayList<Question> questions = new ArrayList<Question>();
        /*============ question 1 type 2 ==============*/

        question_text = " Which company developed android?";
        options = new String[] {" Apple .",
                " Google .",
                " Android Inc."};

        questions.add(new Question(1,2 ,question_text, -1 ,options,new boolean[] {false, false,true}));

        /*============ question 2 type 2 ==============*/
        question_text = "Which of the following is not true about <activity> tag in AndroidManifest file?";
        options = new String[] {"Declares an activity that implements part of the application’s visual user interface.",
                                "Contained in <application> tag.",
                                "Declares a single hardware or software feature that is used by the application."};
        questions.add(new Question(2,2 ,question_text, -1 ,options,new boolean[] {false, false,true}));

        /*============ question 3 type 2 ==============*/

        question_text = "Which of these is NOT recommended in the Android Developer’s Guide as a method of creating\n" +
                "an individual View?";
        options = new String[] {"Create by extending the android.view.View class.",
                "Create by copying the source of an already existing View class such as Button or TextView.",
                "Create by extending already existing View classes such as Button or TextView."};

        questions.add(new Question(3,2 ,question_text, -1 ,options,new boolean[] {false, true, false}));

        /*============ question 4 type 3 ==============*/
        questions.add(new Question(4,3 ,"An Android SDK is required to develop the application for Android.", -1 ,true));

        /*============ question 5 type 2 ==============*/

        question_text = "Select the attribute you need to use to position \"Hi\" in RelativeLayout parent";
        options = new String[] {"android:layout_centerInParent = \"true\"",
                "android:layout_centerVertical = \"true\"",
                "android:layout_centerHorizontal = \"true\""};

        questions.add(new Question(5,2 ,question_text, R.drawable.q4 ,options,new boolean[] {true, false,false}));

        /*============ question 6 type 2 ==============*/

        question_text = "What is an activity in Android?";
        options = new String[] {" Manage the Application content .",
                " Activity performs the actions on the screen .",
                " Screen UI."};

        questions.add(new Question(6,2 ,question_text, -1 ,options,new boolean[] {false, true,false}));


        quizzes.add(new Quiz(1, "Building Layouts",questions ));

        //-------------initialize the question array of quiz 2 :-----------------//
        ArrayList<Question> questions1 = new ArrayList<Question>();
        /*============ question 1 type 2 ==============*/

        question_text = "Which of the following holds Java source code for the application?";
        options = new String[] {" res/",
                " assets/",
                " src/"};

        questions1.add(new Question(1,2 ,question_text, -1 ,options,new boolean[] {false, false,true}));

        /*============ question 2 type 2 ==============*/

        question_text = "ADB stands for";
        options = new String[] {" Application Debug Bridge.",
                " Android Debug Bridge.",
                " Android data bridge."};

        questions1.add(new Question(2,2 ,question_text, -1 ,options,new boolean[] {false, true,false}));


        quizzes.add(new Quiz(2, "Making an App Interactive",questions1));

        //-------------initialize the question array of quiz 3 :-----------------//
        quizzes.add(new Quiz(3, "Object-Oriented Programming",questions1 ));


        //-------------initialize the question array of quiz 4 :-----------------//
        ArrayList<Question> questions2 = new ArrayList<Question>();
        /*============ question 1 type 2 ==============*/

        question_text = "In ___________, sender specifies type of receiver.";
        options = new String[] {" Implicit intent.",
                " Explicit intent.",
                "  none of these."};

        questions2.add(new Question(1,2 ,question_text, -1 ,options,new boolean[] {true, false,false}));

        /*============ question 2 type 2 ==============*/

        question_text = "ADB stands for";
        options = new String[] {" Application Debug Bridge.",
                " Android Debug Bridge.",
                " Android data bridge."};

        questions2.add(new Question(2,2 ,question_text, -1 ,options,new boolean[] {false, true,false}));

        quizzes.add(new Quiz(4, "Intents and Activities",questions2));
        //-------------initialize the question array of quiz 5 :-----------------//
        quizzes.add(new Quiz(5, "Arrays, Lists, Loops, & Custom Classes" ,questions2));
        //-------------initialize the question array of quiz 6 :-----------------//

        ArrayList<Question> questions3 = new ArrayList<Question>();
        /*============ question 1 type 2 ==============*/

        question_text = "Explain android activity life cycle?";
        options = new String[] {" onCreate −> onStart −> onActivityStarted −> onResume −> onPause −> onStop −> onActivityDistroy −> onDestroy ",
                " OnCreate −> onStart −>onResume −> onPause −> onStop −> onRestart −> onDestroy",
                " OnCreate −> onStart −> onPause −> onResume −> onStop −> onDestroy"};

        questions3.add(new Question(1,2 ,question_text, -1 ,options,new boolean[] {false, true,false}));

        quizzes.add(new Quiz(6, "Activity Lifecycle and Audio playback",questions3));

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
