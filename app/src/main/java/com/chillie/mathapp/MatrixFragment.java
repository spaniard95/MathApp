package com.chillie.mathapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MatrixFragment extends Fragment {
    private RecyclerView matrixRecyclerView;
    private MatrixAdapter adapter;

    @Override //method of fragments like onCreate activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        matrixRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        matrixRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //matrixRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.HORIZONTAL)); doesnt work

        updateUI(0);

        ((Grid)getActivity()).setFragmentRefreshListener(new Grid.FragmentRefreshListener() {
            @Override
            public void onRefresh(int ch) {
              updateUI(ch);

            }
        });
        return view;
    }

    private void updateUI(int ch) {
        LessonLab lessonLab = LessonLab.get(getActivity());
        //List<Integer> success=lessonLab.getSuccess();
        adapter=new MatrixAdapter();
        matrixRecyclerView.setAdapter(adapter);
        adapter.setCh(ch);
    }

    private class BlockHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         private TextView TitleTextView,SuccessTextView,CategoryTextView;
        private String [] titles={"ΥΠ","ΠΚΘΜ","ΠΚΕΜ","ΚΘΜ","ΚΕΜ","ΔΔΜ","ΔΦ","ΕΛ","ΔΠΤ"};
        private String[][] limits={{"=14",">=6","OK",">=8",">=2",">=3",">=2","OK","OK"},{"=14","OK",">=5",">=2",">=8",">=3",">=2","OK","OK"},{"14",">=6",">=5",">=8",">=8",">=3",">=2","OK","OK"}};
         private int position;

        public BlockHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.recyclerview_block, parent, false));
            itemView.setOnClickListener(this);

            TitleTextView=(TextView)itemView.findViewById(R.id.category_title);
            SuccessTextView=(TextView)itemView.findViewById(R.id.category_success);
            CategoryTextView=(TextView)itemView.findViewById(R.id.kateuthinsi);

        }
        public void bind(int position,int success,int limit){
            this.position=position;
           TitleTextView.setText(titles[position]);
           SuccessTextView.setText(""+success);
           CategoryTextView.setText(limits[limit][position]);
           //if(title.equals("ΥΠ")) CategoryTextView.setBackgroundColor(Color.GREEN);
        }
        @Override
        public void onClick(View view){
            Intent intent=LessonListActivity.newIntent(getActivity(),position);
            startActivity(intent);
        }
    }
    private class MatrixAdapter extends RecyclerView.Adapter<BlockHolder>{
        int ch;
        public void setCh(int ch){
            this.ch=ch;
        }

        public MatrixAdapter(){

        }
        @Override
        public BlockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BlockHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BlockHolder holder, int position) {
            holder.bind(position,10,ch);
    }
        @Override
        public int getItemCount() {
            return 9;
        }

    }
}
