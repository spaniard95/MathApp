package com.chillie.mathapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class LessonLab {

    private static LessonLab sLessonLab;
    private List<Lesson> mLessons;   //the List contains usercreated Lessons that can be saved and reloaded

    public static LessonLab get(Context context) {
        if (sLessonLab == null) {
            sLessonLab = new LessonLab(context);
        }
        return sLessonLab;
    }
    public List<Lesson> getLessons() {
        return mLessons;
    }
    private LessonLab(Context context) {
        mLessons = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            Lesson lesson =new Lesson();
            lesson.setTitle("lesson"+i);
            mLessons.add(lesson);
        }
    }
}
