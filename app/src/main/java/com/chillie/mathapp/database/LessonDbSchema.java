package com.chillie.mathapp.database;

public class LessonDbSchema {
    public static final class LessonTable{
        public static final String NAME="lessons";
         public static final class Cols{
             public static final String UUID = "uuid";
             public static final String TITLE = "title";
             public static final String TYPE = "type";
             public static final String GRADE = "grade";
         }
    }
}
