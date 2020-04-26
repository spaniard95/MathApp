package com.chillie.mathapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chillie.mathapp.Lesson;
import com.chillie.mathapp.database.LessonDbSchema.LessonTable;

public class LessonBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DATABASE_NAME="lessonBase.db";

    public LessonBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LessonTable.NAME+ " ("+" _id integer primary key autoincrement, " +
                LessonTable.Cols.UUID + ", " +
                LessonTable.Cols.TITLE + ", " +
                LessonTable.Cols.TYPE + ", " +
                LessonTable.Cols.GRADE +")"    );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
