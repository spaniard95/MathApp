package com.chillie.mathapp;

import java.util.UUID;

public class Lesson {

    private String mTitle,mCategory;
    private String mGrade="  null";
    private UUID mId;

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public Lesson(){
        mId = UUID.randomUUID();
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
        return true;
    }
}
