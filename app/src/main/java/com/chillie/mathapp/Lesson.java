package com.chillie.mathapp;

import java.util.UUID;

public class Lesson {

    private String mTitle,mCategory;
    private String mGrade="";
    private UUID mId;

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public Lesson(){
        this(UUID.randomUUID());
        // mId = UUID.randomUUID();
        }
    public Lesson(UUID id) {
        mId = id;
     }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public boolean isPassed() { //theli kai alli dulia
        if(mGrade.equals("5"))return true;
        else return false;
    }
}
