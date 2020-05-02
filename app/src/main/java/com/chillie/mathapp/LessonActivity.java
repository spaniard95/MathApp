package com.chillie.mathapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class LessonActivity extends SingleFragmentActivity {
    public static final String EXTRA_LESSON_ID = "com.chillie.android.lessonintent.lesson_id";
    public static Intent newIntent(Context packageContext, UUID lessonId) {
        Intent intent = new Intent(packageContext, LessonActivity.class);
        intent.putExtra(EXTRA_LESSON_ID, lessonId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {

        return new LessonFragment();
    }


}
