package com.chillie.mathapp;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.UUID;

public class LessonFragment extends Fragment {
    private Lesson mLesson;
    private TextView mLessonTitle, mLessonCategory, mLessonGrade;
    private ImageView mLessonStar;
    private EditText mInsertGrade;
    private Button mSaveButton;
    private CharSequence passToSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID lessonId = (UUID) getActivity().getIntent().getSerializableExtra(LessonActivity.EXTRA_LESSON_ID);
        mLesson=LessonLab.get(getActivity()).getLesson(lessonId);
    }
    @Override
    public void onPause() {
        super.onPause();
        LessonLab.get(getActivity()).updateLesson(mLesson);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_lesson, container, false);

        mLessonTitle=(TextView)v.findViewById(R.id.lesson_title_activity);
        mLessonCategory=(TextView)v.findViewById(R.id.lesson_category_activity);
        mLessonGrade=(TextView)v.findViewById(R.id.lesson_saved_grade);
        mLessonStar=(ImageView) v.findViewById(R.id.lesson_passed_star);
        mInsertGrade=(EditText)v.findViewById(R.id.lesson_grade_activity);
        mSaveButton=(Button)v.findViewById(R.id.save_button);
        mInsertGrade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              passToSave=s;
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLesson.setGrade(passToSave.toString());
                mLessonGrade.setText(""+mLesson.getGrade());
                mLessonStar.setVisibility(mLesson.isPassed() ? View.VISIBLE : View.GONE);
            }
        });
        mLessonTitle.setText(mLesson.getTitle());
        mLessonCategory.setText(mLesson.getCategory());
        mLessonGrade.setText(""+mLesson.getGrade());

       return v;
    }
}
