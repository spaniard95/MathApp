package com.chillie.mathapp;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;


public class LessonFragment extends Fragment {
    private Lesson mLesson;
    private TextView mLessonTitle, mLessonCategory, mLessonGrade;
    private ImageView mLessonStar;
    private EditText mInsertGrade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID lessonId = (UUID) getActivity().getIntent().getSerializableExtra(LessonActivity.EXTRA_LESSON_ID);
        mLesson=LessonLab.get(getActivity()).getLesson(lessonId);
    }
    @Override public void onPause() {
        super.onPause();
        LessonLab.get(getActivity()).updateCrime(mLesson); }
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_lesson, container, false);

        mLessonTitle=(TextView)v.findViewById(R.id.lesson_title_activity);
        mLessonCategory=(TextView)v.findViewById(R.id.lesson_category_activity);
        mLessonGrade=(TextView)v.findViewById(R.id.lesson_saved_grade);
        mLessonStar=(ImageView) v.findViewById(R.id.lesson_passed_star);
        mInsertGrade=(EditText)v.findViewById(R.id.lesson_grade_activity);
        mInsertGrade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLesson.setGrade(s.toString());
                mLessonGrade.setText("Saved Grade"+mLesson.getGrade());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mLessonTitle.setText(mLesson.getTitle());
        mLessonCategory.setText(mLesson.getCategory());
        mLessonGrade.setText("Saved Grade  "+mLesson.getGrade());
        mLessonStar.setVisibility(mLesson.isPassed() ? View.VISIBLE : View.GONE);
       return v;
    }
}
