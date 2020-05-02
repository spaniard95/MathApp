package com.chillie.mathapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
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
  //  private List<Lesson> mLessons;           //the List contains usercreated Lessons that can be saved and reloaded
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private int categoryCh;

    public static LessonLab get(Context context) {
        if (sLessonLab == null) {
            sLessonLab = new LessonLab(context);
        }
        return sLessonLab;
    }
    private LessonLab(Context context) {
        mContext=context.getApplicationContext();
        mDatabase=new LessonBaseHelper(mContext).getWritableDatabase(); //it creates a db if it doesn't already exists
                                                                        //if first time db call onCreate(SQLite....)and save latest version num
                                                                        //If this is not the first time, check the version number in the database. If the version number
                                                                        //in LessonBaseHelper is higher, call onUpgrade(SQLiteDatabase int, int).
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
    public List<Lesson> getLessons(int category){
      return getLessons();
    }
    public void updateLesson(Lesson lesson) {

        String uuidString = lesson.getId().toString();
        ContentValues values = getContentValues(lesson);
        mDatabase.update(LessonDbSchema.LessonTable.NAME,values,LessonDbSchema.LessonTable.Cols.UUID + " = ?",new String[] { uuidString });
    }
    private LessonCursorWrapper queryLessons(String whereClause, String[] whereArgs) {
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
        mDatabase.insert(LessonDbSchema.LessonTable.NAME, null, values); // The first argument is the table you want to insert into,the last argument is the data you want to put in
    }
    //ContentValues is a key-value store class, like Java’s HashMap but designed for sql data
    //For the keys, you use your column names
     private static ContentValues getContentValues(Lesson lesson) {
        ContentValues values = new ContentValues();
        values.put(LessonDbSchema.LessonTable.Cols.UUID, lesson.getId().toString());
        values.put(LessonDbSchema.LessonTable.Cols.TITLE, lesson.getTitle());
        values.put(LessonDbSchema.LessonTable.Cols.TYPE, lesson.getCategory());
        values.put(LessonDbSchema.LessonTable.Cols.GRADE, lesson.getGrade());
        return values;
    }

    public Lesson getLesson(UUID id) {
        // for (Lesson lesson : mLessons) {
        //     if (lesson.getId().equals(id)) {
        //         return lesson;
        //     }
        // }
        // return null;
        LessonCursorWrapper cursor = queryLessons( LessonDbSchema.LessonTable.Cols.UUID + " = ?", new String[] { id.toString() });
        try {
            if (cursor.getCount() == 0) {
                return null;             //if it doent exist in the list it isnt passed
            }
            cursor.moveToFirst();
            return cursor.getLesson();
        } finally {
            cursor.close();
        }
    }
}
