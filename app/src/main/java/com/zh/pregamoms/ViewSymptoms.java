package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
import com.zh.pregamoms.classes.Symptoms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewSymptoms extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ViewSymptomsAdapter symptomsAdapter;
    List<Symptoms> readsymptoms;
    private FirebaseAuth mAuth;
    private String data = " ";
    private TextView nothing;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_symptoms);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.rdiet);
        nothing = findViewById(R.id.nothing);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewSymptoms.this));

        readsymptoms= new ArrayList<Symptoms>();
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.rdiet);
        nothing = findViewById(R.id.nothing);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewSymptoms.this));

        readsymptoms= new ArrayList<Symptoms>();

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        String sDate = sdf.format(calendar.getTime());
        show();

        Intent intent = getIntent();
        data = intent.getExtras().getString("YourValueKey").trim();
    }

    private void show(){

        DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Symptoms");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readsymptoms.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Symptoms sy = ds.getValue(Symptoms.class);

                    if(sy.getDate().equals(data)) {
                        counter++;

                        readsymptoms.add(sy);
                        symptomsAdapter = new ViewSymptomsAdapter(ViewSymptoms.this, readsymptoms);
                        recyclerView.setAdapter(symptomsAdapter);
                        //Toast.makeText(WeeklyTrackerDietHistory.this, dietAdapter.getItemCount(), Toast.LENGTH_SHORT).show();

                    }


                }

                //Toast.makeText(WeeklyTrackerDietHistory.this, counter, Toast.LENGTH_SHORT).show();
                if (counter == 0){
                    //Toast.makeText(WeeklyTrackerDietHistory.this, "I am here", Toast.LENGTH_SHORT).show();
                    nothing.setVisibility(View.VISIBLE);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}