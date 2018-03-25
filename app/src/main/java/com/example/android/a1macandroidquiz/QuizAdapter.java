package com.example.android.a1macandroidquiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by  @ameenhazza55 , @Mohammed.Jassim , @rahali
 * on 22/03/2018.
 */

public class QuizAdapter extends ArrayAdapter<Quiz> {
    public QuizAdapter(Context context,  ArrayList<Quiz> quizzes) {
        super(context, 0, quizzes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view(single item in Grid / List)
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.simple_quiz_item, parent, false);
        }

        // Get the {@link Quiz} object located at this position in the list
        final Quiz currentItem = getItem(position);

        // Find the TextView in the simple_quiz_item.xml layout with the ID quiz_title_text_view
        TextView quiz_title = (TextView) itemView.findViewById(R.id.quiz_title_text_view);
        // Get the quiz title from the current Quiz object and
        // set this text on the quiz_title_text_view TextView
        quiz_title.setText(currentItem.getTitle());

        // Find the TextView in the simple_quiz_item.xml layout with the ID question_count_text_view
        TextView question_count = (TextView) itemView.findViewById(R.id.question_count_text_view);
        // Get the quiz count from the Quest object and
        // set this text on the question_count_text_view TextView
        question_count.setText(String.valueOf(currentItem.getQuestions().size()) + " Questions");

        // Find the TextView in the simple_quiz_item.xml layout with the ID total_point_text_view
        TextView total_points = (TextView) itemView.findViewById(R.id.total_point_text_view);
        // Get the quiz count from the Quest object and
        // set this text on the total_point_text_view TextView
        total_points.setText(String.valueOf(currentItem.getTotalPonits()));

        return itemView;
    }
}
