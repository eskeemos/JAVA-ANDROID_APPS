package com.example.gymmaster;

public class Workout {
    private final String name;
    private final String desc;

    public static Workout[] workouts = {
        new Workout("Beginner gym workout for strength","Barbell push press (6 reps x 4 sets)\nGoblet squat (6 reps x 4 sets)\nDumbbell single arm row (6 reps x 4 sets)"),
        new Workout("Beginner gym workout for fat loss","Plate thrusters (15 reps x 3 sets)\nMountain climbers (20 reps x 3 sets)\nBox jumps (10 reps x 3 sets)"),
        new Workout("Beginner gym circuit programme","2 min rower\nAlternating side plank (45 secs)\nBicep curl to shoulder press (45 secs)"),
        new Workout("Beginner gym workout for cardio equipment","5 min treadmill brisk walk (optional incline)\n5 min rower (steady)\n1 min run/1 min walk treadmill x 10(easy)/15(medium)/20(hard)")
    };

    public Workout(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
