package com.chillie.mathapp;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class LessonListActivity extends SingleFragmentActivity {

    public static final String EXTRA_CATEGORY_ID = "com.chillie.android.lessonintent.category";
    public static Intent newIntent(Context packageContext, int category) {
        Intent intent = new Intent(packageContext, LessonListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID,category);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return new LessonListFragment();
    }

}
