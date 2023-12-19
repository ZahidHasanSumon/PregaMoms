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
import com.zh.pregamoms.classes.Diet;

import java.util.ArrayList;
import java.util.List;

public class DietHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    ViewDietAdapter contactAdpter;
    List<Diet> readDiet;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_history);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.rdiet);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DietHistory.this));

        readDiet = new ArrayList<Diet>();
        show();
    }

    private void show(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Diet").getRef();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readDiet.clear();


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Diet diet = ds.getValue(Diet.class);

                    readDiet.add(diet);
                    ViewDietAdapter dietAdapter = new ViewDietAdapter(DietHistory.this, readDiet);
                    recyclerView.setAdapter(dietAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}