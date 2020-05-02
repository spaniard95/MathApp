package com.chillie.mathapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Grid extends AppCompatActivity {
    private TextView categorySelect, lessonsCategory, lessonsSuccess;
    private TextView sumTitle, grade, sumSuccess;
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] items={"ΘΕΩΡΗΤΙΚΩΝ ΜΑΘΗΜΑΤΙΚΩΝ","ΕΦΑΡΜΟΣΜΕΝΩΝ ΜΑΘΗΜΑΤΙΚΩΝ","ΘΕΩΡΗΤΙΚΩΝ ΚΑΙ ΕΦΑΡΜΟΣΜΕΝΩΝ ΜΑΘΗΜΑΤΙΚΩΝ"};
    int dialogCh;
    private FragmentRefreshListener fragmentRefreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_matrix);

        categorySelect=(TextView)findViewById(R.id.category_choise);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.matrix_fragment);

        if (fragment == null) {
            fragment = new MatrixFragment();
            fm.beginTransaction()
                    .add(R.id.matrix_fragment, fragment)
                    .commit();

        }
        showDialog();
        categorySelect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showDialog();
              }
        });
    }
    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    public interface FragmentRefreshListener{
        void onRefresh(int ch);
    }

    private void showDialog(){
        builder=new AlertDialog.Builder(Grid.this);
        builder.setTitle("Επέλεξε κατεύθυνση");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialogCh=which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               categorySelect.setText(items[dialogCh]);
                if(getFragmentRefreshListener()!= null){
                    getFragmentRefreshListener().onRefresh(dialogCh);
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialogCh=-1;
            }
        });
        dialog=builder.create();
        dialog.show();
    }
}


