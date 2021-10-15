package com.example.gymmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_WORKOUT_ID = "workoutId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);

        WorkoutDetailFragment wdf = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.fDetailWorkout);
        wdf.setWorkoutId(workoutId);
    }
}