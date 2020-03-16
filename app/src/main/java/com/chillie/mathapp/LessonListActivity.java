package com.chillie.mathapp;

import androidx.fragment.app.Fragment;

public class LessonListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {

        return new LessonListFragment();
    }
}
