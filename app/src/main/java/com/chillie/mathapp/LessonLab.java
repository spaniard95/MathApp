package com.chillie.mathapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
            lesson.setCategory("ipoxreotiko");
            mLessons.add(lesson);
        }
    }
    public Lesson getLesson(UUID id) {
        for (Lesson lesson : mLessons) {
            if (lesson.getId().equals(id)) {
                return lesson;
            }
        }

        return null;
    }
}
