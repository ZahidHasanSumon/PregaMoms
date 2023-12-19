package com.zh.pregamoms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    ViewExerciseAdapter contactAdpter;
    List<Exercise> readDiet;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_history);

        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.rdiet);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExerciseHistory.this));

        readDiet = new ArrayList<Exercise>();
        show();
    }

    private void show(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Exercise").getRef();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readDiet.clear();


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Exercise diet = ds.getValue(Exercise.class);

                    readDiet.add(diet);
                    ViewExerciseAdapter dietAdapter = new ViewExerciseAdapter(ExerciseHistory.this, readDiet);
                    recyclerView.setAdapter(dietAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
