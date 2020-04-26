package com.chillie.mathapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.chillie.mathapp.Lesson;

import java.util.UUID;

public class LessonCursorWrapper extends CursorWrapper {

    public LessonCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Lesson getLesson() {
        String uuidString = getString(getColumnIndex(LessonDbSchema.LessonTable.Cols.UUID));
        String title = getString(getColumnIndex(LessonDbSchema.LessonTable.Cols.TITLE));
        String type = getString(getColumnIndex(LessonDbSchema.LessonTable.Cols.TYPE));
        String grade = getString(getColumnIndex(LessonDbSchema.LessonTable.Cols.GRADE));
        Lesson lesson = new Lesson(UUID.fromString(uuidString));
        lesson.setTitle(title);
        lesson.setCategory(type);
        lesson.setGrade(grade);
        return lesson;
    }
}
