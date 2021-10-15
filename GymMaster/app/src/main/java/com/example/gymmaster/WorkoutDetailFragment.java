package com.example.gymmaster;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {

    private int workoutId;

    public WorkoutDetailFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState == null) {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            StopwatchFragment sf = new StopwatchFragment();
            ft.replace(R.id.flStopwatch, sf);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }

        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view != null) {
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvDesc = view.findViewById(R.id.tvDesc);

            Workout workout = Workout.workouts[workoutId];

            tvName.setText(workout.getName());
            tvDesc.setText(workout.getDesc());
        }
    }

    public void setWorkoutId(int id) {
        this.workoutId = id;
    }
}