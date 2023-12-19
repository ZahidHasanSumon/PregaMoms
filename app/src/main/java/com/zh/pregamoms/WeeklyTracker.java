package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.WeightPerWeek;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class WeeklyTracker extends AppCompatActivity {
    private Button foodbutton, symbutton, foodview, symview;
    private FirebaseAuth mAuth;
    private CalendarView calendarView;
    long calendarDate;
    private String sDate=" ";
    private String yDate=" ";
    private TextView cweight, pweight;

    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_tracker);

        foodbutton = findViewById(R.id.foodbutton);
        symbutton = findViewById(R.id.symbutton);
        symbutton = findViewById(R.id.symbutton);
        mAuth = FirebaseAuth.getInstance();
        foodview = findViewById(R.id.foodview);
        calendarView = findViewById(R.id.calendar);
        symview = findViewById(R.id.symview);
        cweight = findViewById(R.id.cweight);
        pweight = findViewById(R.id.pweight);


        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        final SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyy");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        yDate = sdf.format(calendar.getTime());
        show();



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                calendarDate = calendarView.getDate();
                // String calendarDate1 = String.valueOf(calendarDate);

                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                sDate = sdf.format(calendar.getTime());
                show();


            }
        });

        symview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(WeeklyTracker.this,ViewSymptoms.class);
                i.putExtra("YourValueKey",sDate);
                if (sDate == " "){
                    i.putExtra("YourValueKey",yDate);
                    startActivity(i);

                }else{
                    i.putExtra("YourValueKey",sDate);
                    startActivity(i);

                }


            }
        });

        foodview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(WeeklyTracker.this,WeeklyTrackerDietHistory.class);
                i.putExtra("YourValueKey",sDate);
                if (sDate == " "){
                    i.putExtra("YourValueKey",yDate);

                }else{
                    i.putExtra("YourValueKey",sDate);

                }
                startActivity(i);

            }
        });

        symbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeeklyTracker.this, SymptomsPerDay.class));

            }
        });

        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeeklyTracker.this, EnterDiet.class));
            }
        });
    }

    private void show(){

        DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("WeightPWeek");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

             for(DataSnapshot ds: dataSnapshot.getChildren()){

                    WeightPerWeek sy = ds.getValue(WeightPerWeek.class);

                    if(sy.getDate().equals(sDate)) {

                        cweight.setText(sy.getWeight());
                        //pweight.setText();
                        list.add(sy.getWeight());

                    }else if(sy.getDate().equals(yDate)){
                        cweight.setText(sy.getWeight());
                        //pweight.setText();
                        list.add(sy.getWeight());

                    }else{
                        cweight.setText("Not found");

                    }


                }

                if (list != null && !list.isEmpty()) {

                    if(list.size() > 1){
                        String pweek = list.get(list.size()-2);
                        pweight.setText(pweek);

                    }else{
                        pweight.setText("Not found");

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}