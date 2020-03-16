package com.chillie.mathapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonListFragment extends Fragment {
    private RecyclerView mLessonRecyclerView;
    private CrimeAdapter mAdapter;

    @Override //method of fragments like onCreate activity
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        mLessonRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mLessonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        LessonLab crimeLab = LessonLab.get(getActivity());
        List<Lesson> crimes = crimeLab.getLessons();
        mAdapter = new CrimeAdapter(crimes);
        mLessonRecyclerView.setAdapter(mAdapter);
    }





    private class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       private Lesson mLesson;

        private TextView mTitleTextView;
        private TextView mDateTextView;

        public LessonHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recyclerview_row, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.lesson_title);

        }

        public void bind(Lesson lesson) {
            mLesson = lesson;
            mTitleTextView.setText(mLesson.getTitle());

        }

       @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), mLesson.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }








    private class CrimeAdapter extends RecyclerView.Adapter<LessonHolder> {

        private List<Lesson> mLessons;

        public CrimeAdapter(List<Lesson> lessons) {
            mLessons = lessons;
        }

        @Override
        public LessonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new LessonHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(LessonHolder holder, int position) {
            Lesson lesson = mLessons.get(position);
            holder.bind(lesson);
        }

        @Override
        public int getItemCount() {
            return mLessons.size();
        }
    }


}
