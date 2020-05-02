package com.chillie.mathapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.chillie.mathapp.R;
import com.chillie.mathapp.database.LessonDbSchema.LessonTable;

import java.util.UUID;

public class LessonBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lessonBase.db";
    private final Context fContext;

    //onCreate(SQLiteDatabase) to create the initial database,
    //in onUpgrade(SQLiteDatabase, int, int) your code to handle any upgrades
    public LessonBaseHelper(Context context) {
                super(context, DATABASE_NAME, null, VERSION);
                fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LessonTable.NAME + " (" + " _id integer primary key autoincrement, " +
                LessonTable.Cols.UUID + ", " +
                LessonTable.Cols.TITLE + ", " +
                LessonTable.Cols.TYPE + ", " +
                LessonTable.Cols.GRADE + ")");

        ContentValues values = new ContentValues();
        Resources res = fContext.getResources();
        String[] _mytable = res.getStringArray(R.array.table);
        //values.put("title", _mytable[1]);
        values.put(LessonDbSchema.LessonTable.Cols.UUID, ""+UUID.randomUUID() );
        values.put(LessonDbSchema.LessonTable.Cols.TITLE, "ipo");
        values.put(LessonDbSchema.LessonTable.Cols.TYPE, "ipo");
        values.put(LessonDbSchema.LessonTable.Cols.GRADE, "5");
        db.insert(LessonDbSchema.LessonTable.NAME, null, values);
       // db.insert("mytable", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
