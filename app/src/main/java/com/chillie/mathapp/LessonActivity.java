package com.chillie.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LessonActivity extends AppCompatActivity {
private TextView mLessonTitle,mLessonCategory;
private CheckBox mLessonStar;
private EditText mInsertGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
    }
}
