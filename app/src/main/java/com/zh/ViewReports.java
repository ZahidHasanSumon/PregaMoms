package com.zh;

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
import com.zh.pregamoms.R;
import com.zh.pregamoms.ViewDietAdapter;
import com.zh.pregamoms.ViewExerciseAdapter;
import com.zh.pregamoms.ViewSymptomsAdapter;
import com.zh.pregamoms.classes.Diet;
import com.zh.pregamoms.classes.Exercise;
import com.zh.pregamoms.classes.Symptoms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewReports extends AppCompatActivity {

    private String yDate=" ", hhDate = " ", date0=" ", date1=" ", date2=" ", date3=" ", date4=" ", date5=" ", date6=" ";
    private String date[];
    private String day=" ", month=" ", year=" ";
    private int Date0=0, month0=0, year0=0;
    private final int moo=31;

    private RecyclerView recyclerView1, recyclerView2, recyclerView3;
    private ViewSymptomsAdapter symptomsAdapter;
    List<Symptoms> readsymptoms;
    private FirebaseAuth mAuth;
    private ViewDietAdapter dietAdapter;
    List<Diet> readDiet;
    List<Exercise> exercises;

    ViewExerciseAdapter contactAdpter;
    ArrayList<String> list;
    final SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        recyclerView1 = findViewById(R.id.food11);
        recyclerView2 = findViewById(R.id.exercise11);
        recyclerView3 = findViewById(R.id.symptoms11);
        date = new String[7];

        mAuth = FirebaseAuth.getInstance();
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(ViewReports.this));

        readsymptoms= new ArrayList<Symptoms>();
        readDiet = new ArrayList<Diet>();
        exercises = new ArrayList<Exercise>();
        list=new ArrayList<String>();

        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(ViewReports.this));

        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(ViewReports.this));
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        final SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yy");
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        final SimpleDateFormat sdf3 = new SimpleDateFormat("yy");


        day = sdf1.format(calendar.getTime());
        month = sdf2.format(calendar.getTime());
        year = sdf3.format(calendar.getTime());

        Date0 = Integer.parseInt(day);
        month0 = Integer.parseInt(month);
        showSymptoms();

        if(Date0 > 6){
            if(Date0 == 7){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);

            }else if(Date0 == 8){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);

            }else if(Date0 == 9){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 10){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 11){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf(Date0 -1)+"/"+month+"/"+year;
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 12){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf(Date0 -1)+"/"+month+"/"+year;
                date[2] = String.valueOf(Date0 -2)+"/"+month+"/"+year;
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 13){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf(Date0 -1)+"/"+month+"/"+year;
                date[2] = String.valueOf(Date0 -2)+"/"+month+"/"+year;
                date[3] = String.valueOf(Date0 -3)+"/"+month+"/"+year;
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 14){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf(Date0 -1)+"/"+month+"/"+year;
                date[2] = String.valueOf(Date0 -2)+"/"+month+"/"+year;
                date[3] = String.valueOf(Date0 -3)+"/"+month+"/"+year;
                date[4] = String.valueOf(Date0 -4)+"/"+month+"/"+year;
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 15){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf(Date0 -1)+"/"+month+"/"+year;
                date[2] = String.valueOf(Date0 -2)+"/"+month+"/"+year;
                date[3] = String.valueOf(Date0 -3)+"/"+month+"/"+year;
                date[4] = String.valueOf(Date0 -4)+"/"+month+"/"+year;
                date[5] = String.valueOf(Date0 -5)+"/"+month+"/"+year;
                date[6] = 0+(String.valueOf((Date0 -6))+"/"+month+"/"+year);


            }else if(Date0 == 16){
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf(Date0 -1)+"/"+month+"/"+year;
                date[2] = String.valueOf(Date0 -2)+"/"+month+"/"+year;
                date[3] = String.valueOf(Date0 -3)+"/"+month+"/"+year;
                date[4] = String.valueOf(Date0 -4)+"/"+month+"/"+year;
                date[5] = String.valueOf(Date0 -5)+"/"+month+"/"+year;
                date[6] = String.valueOf(Date0 -6)+"/"+month+"/"+year;


            }else{
                date[0] = String.valueOf(Date0)+"/"+month+"/"+year;
                date[1] = String.valueOf((Date0 -1))+"/"+month+"/"+year;
                date[2] = String.valueOf((Date0 -2))+"/"+month+"/"+year;
                date[3] = String.valueOf((Date0 -3))+"/"+month+"/"+year;
                date[4] = String.valueOf((Date0 -4))+"/"+month+"/"+year;
                date[5] = String.valueOf((Date0 -5))+"/"+month+"/"+year;
                date[6] = String.valueOf((Date0 -6))+"/"+month+"/"+year;

            }

            showSymptoms();
            showDiet();
            showExercise();

        }else if(Date0 < 7){

            if (Date0 == 1){

                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = String.valueOf(moo)+"/"+String.valueOf(month0-1)+"/"+year;
                date[2] = String.valueOf((moo -1))+"/"+String.valueOf(month0-1)+"/"+year;
                date[3] = String.valueOf((moo -2))+"/"+String.valueOf(month0-1)+"/"+year;
                date[4] = String.valueOf((moo -3))+"/"+String.valueOf(month0-1)+"/"+year;
                date[5] = String.valueOf((moo -4))+"/"+String.valueOf(month0-1)+"/"+year;
                date[6] = String.valueOf((moo -5))+"/"+String.valueOf(month0-1)+"/"+year;
                showSymptoms();
                showDiet();
                showExercise();


            }else if(Date0 == 2){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = String.valueOf((moo))+"/"+String.valueOf(month0-1)+"/"+year;
                date[3] = String.valueOf((moo -1))+"/"+String.valueOf(month0-1)+"/"+year;
                date[4] = String.valueOf((moo -2))+"/"+String.valueOf(month0-1)+"/"+year;
                date[5] = String.valueOf((moo -3))+"/"+String.valueOf(month0-1)+"/"+year;
                date[6] = String.valueOf((moo -4))+"/"+String.valueOf(month0-1)+"/"+year;
                showSymptoms();
                showDiet();
                showExercise();



            }else if(Date0 == 3){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = String.valueOf((moo -1))+"/"+String.valueOf(month0-1)+"/"+year;
                date[4] = String.valueOf((moo -2))+"/"+String.valueOf(month0-1)+"/"+year;
                date[5] = String.valueOf((moo -3))+"/"+String.valueOf(month0-1)+"/"+year;
                date[6] = String.valueOf((moo -4))+"/"+String.valueOf(month0-1)+"/"+year;
                showSymptoms();
                showDiet();
                showExercise();



            }else if(Date0 == 4){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = String.valueOf((moo -1))+"/"+String.valueOf(month0-1)+"/"+year;
                date[5] = String.valueOf((moo -2))+"/"+String.valueOf(month0-1)+"/"+year;
                date[6] = String.valueOf((moo -3))+"/"+String.valueOf(month0-1)+"/"+year;
                showSymptoms();
                showDiet();
                showExercise();



            }else if(Date0 == 5){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = String.valueOf((moo -1))+"/"+String.valueOf(month0-1)+"/"+year;
                date[6] = String.valueOf((moo -2))+"/"+String.valueOf(month0-1)+"/"+year;
                showSymptoms();
                showDiet();
                showExercise();



            }else if(Date0 == 6){
                date[0] = 0+(String.valueOf(Date0)+"/"+month+"/"+year);
                date[1] = 0+(String.valueOf((Date0 -1))+"/"+month+"/"+year);
                date[2] = 0+(String.valueOf((Date0 -2))+"/"+month+"/"+year);
                date[3] = 0+(String.valueOf((Date0 -3))+"/"+month+"/"+year);
                date[4] = 0+(String.valueOf((Date0 -4))+"/"+month+"/"+year);
                date[5] = 0+(String.valueOf((Date0 -5))+"/"+month+"/"+year);
                date[6] = String.valueOf((moo -1))+"/"+String.valueOf(month0-1)+"/"+year;
                showSymptoms();
                showDiet();
                showExercise();



            }

        }


    }

    private void showExercise(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Exercise");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                exercises.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Exercise ex = ds.getValue(Exercise.class);

                    for(int i = 0; i < 7; i++) {
                       // Toast.makeText(ViewReports.this, "Date "+diet.getDate()+"Date "+date[6], Toast.LENGTH_SHORT).show();
                        if (ex.getDate().equals(date[i])) {


                            exercises.add(ex);
                            ViewExerciseAdapter dietAdapter = new ViewExerciseAdapter(ViewReports.this, exercises);
                            recyclerView2.setAdapter(dietAdapter);


                       }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void showDiet(){

        DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Diet");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readDiet.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Diet diet = ds.getValue(Diet.class);
                    for(int i = 0; i < 7; i++) {
                        if (diet.getDate().equals(date[i])) {

                            readDiet.add(diet);
                            dietAdapter = new ViewDietAdapter(ViewReports.this, readDiet);
                            recyclerView1.setAdapter(dietAdapter);
                            //Toast.makeText(WeeklyTrackerDietHistory.this, dietAdapter.getItemCount(), Toast.LENGTH_SHORT).show();

                        }
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showSymptoms(){

        DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Symptoms");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readsymptoms.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Symptoms sy = ds.getValue(Symptoms.class);


                    for(int i = 0; i < 7; i++) {
                        if (sy.getDate().equals(date[i])) {

                            readsymptoms.add(sy);
                            symptomsAdapter = new ViewSymptomsAdapter(ViewReports.this, readsymptoms);
                            recyclerView3.setAdapter(symptomsAdapter);
                            //Toast.makeText(ViewReports.this, date[i], Toast.LENGTH_SHORT).show();

                        }
                     }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}