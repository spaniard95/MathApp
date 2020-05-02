package com.chillie.mathapp;


import android.content.Context;

public class MatrixLab {

    private static MatrixLab sMatrixLab;
    private Context mContext;
    private int ai;

    private MatrixLab(Context context){
        mContext=context.getApplicationContext();
    }
    public static MatrixLab get(Context context){
        if(sMatrixLab==null) sMatrixLab=new MatrixLab(context);
        return sMatrixLab;
    }

    public int getAi() {
        return ai;
    }

    public void setAi(int ai) {
        this.ai = ai;
    }
}
