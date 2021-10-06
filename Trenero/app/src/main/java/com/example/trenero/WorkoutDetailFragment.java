package com.example.trenero;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState != null)
        {
            workoutId = savedInstanceState.getLong("workoutId");
        }else{
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            ft.replace(R.id.stopWatch_cont, stopwatchFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null)
        {
            TextView nameView = (TextView) view.findViewById(R.id.tvName);
            TextView descView = (TextView) view.findViewById(R.id.tvDesc);

            Workout workout = Workout.workouts[(int) workoutId];

            nameView.setText(workout.getName());
            descView.setText(workout.getDesc());
        }
    }

    public void setWorkoutId(long id)
    {
        this.workoutId = id;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("workoutId", workoutId);
    }
}