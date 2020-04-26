package com.chillie.mathapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chillie.mathapp.database.LessonBaseHelper;
import com.chillie.mathapp.database.LessonCursorWrapper;
import com.chillie.mathapp.database.LessonDbSchema;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LessonLab {

    private static LessonLab sLessonLab;
    //private List<Lesson> mLessons;//the List contains usercreated Lessons that can be saved and reloaded
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static LessonLab get(Context context) {
        if (sLessonLab == null) {
            sLessonLab = new LessonLab(context);
        }
        return sLessonLab;
    }
    public List<Lesson> getLessons() {
        //return mLessons;
       // return new ArrayList();
        List<Lesson> lessons = new ArrayList<>();
        LessonCursorWrapper cursor = queryLessons(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return lessons;

    }
    public void updateCrime(Lesson lesson) {
        String uuidString = lesson.getId().toString();
        ContentValues values = getContentValues(lesson);
        mDatabase.update(LessonDbSchema.LessonTable.NAME,values,LessonDbSchema.LessonTable.Cols.UUID + " = ?",new String[] { uuidString });
    }
    private LessonCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(
            LessonDbSchema.LessonTable.NAME,null, // columns - null selects all columns
             whereClause,
             whereArgs,
            null, // groupBy
             null, // having
             null  // orderBy
             );
             return new LessonCursorWrapper(cursor);
    }
    public void addLesson(Lesson lesson){
        ContentValues values = getContentValues(lesson);
        mDatabase.insert(LessonDbSchema.LessonTable.NAME, null, values);

    }
    public void updateLesson(Lesson lesson) {
        String uuidString = lesson.getId().toString();
    ContentValues values = getContentValues(lesson);
    mDatabase.update(LessonDbSchema.LessonTable.NAME, values, LessonDbSchema.LessonTable.Cols.UUID + " = ?", new String[] { uuidString }); }

    private static ContentValues getContentValues(Lesson lesson) {
        ContentValues values = new ContentValues();
        values.put(LessonDbSchema.LessonTable.Cols.UUID, lesson.getId().toString());
        values.put(LessonDbSchema.LessonTable.Cols.TITLE, lesson.getTitle());
        values.put(LessonDbSchema.LessonTable.Cols.TYPE, lesson.getCategory());
        values.put(LessonDbSchema.LessonTable.Cols.GRADE, lesson.getGrade());
        return values;
    }

    private LessonLab(Context context) {
        mContext=context.getApplicationContext();
        mDatabase=new LessonBaseHelper(mContext).getWritableDatabase(); //it creates a db if it doesn't already exists
                                                                        //if first time db call onCreate(SQLite....)and save latest version num
                                                                        //If this is not the first time, check the version number in the database. If the version number
                                                                        //in LessonBaseHelper is higher, call onUpgrade(SQLiteDatabase int, int).
       // mLessons = new ArrayList<>();
       //  for (int i = 0; i < 36; i++) {
       //      Lesson lesson =new Lesson();
       //     lesson.setTitle("lesson"+i);
       //    lesson.setCategory("ipoxreotiko");
       //    mLessons.add(lesson);
       //   }
 }
    public Lesson getLesson(UUID id) {
       // for (Lesson lesson : mLessons) {
       //     if (lesson.getId().equals(id)) {
       //         return lesson;
       //     }
       // }
        // return null;
        LessonCursorWrapper cursor = queryCrimes( LessonDbSchema.LessonTable.Cols.UUID + " = ?", new String[] { id.toString() });
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLesson();
        } finally {
            cursor.close();
        }
    }
}
