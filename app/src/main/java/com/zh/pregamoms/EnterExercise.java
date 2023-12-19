package com.zh.pregamoms;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zh.pregamoms.classes.Exercise;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EnterExercise extends AppCompatActivity {
    private TextView date;
    private CheckBox other, rice, dal, bread, roti, egg, fish;
    private EditText hidden, tx1, tx2, tx3, tx4, tx5, tx6, tx7;
    private Button foodsave;
    private Exercise diet;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_exercise);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        StringBuilder result=new StringBuilder();
        StringBuilder exdur=new StringBuilder();
        diet = new Exercise();

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        String sDate = sdf.format(calendar.getTime());



        date = findViewById(R.id.showdate);
        other = findViewById(R.id.cother);
        hidden = findViewById(R.id.htext);
        rice = findViewById(R.id.rice);
        dal = findViewById(R.id.dal);
        bread = findViewById(R.id.bread);
        roti = findViewById(R.id.roti);
        egg = findViewById(R.id.egg);
        fish = findViewById(R.id.fish);
        foodsave = findViewById(R.id.foodsave);
        tx1 = findViewById(R.id.htext1);
        tx2 = findViewById(R.id.htext2);
        tx3 = findViewById(R.id.htext3);
        tx4 = findViewById(R.id.htext4);
        tx5 = findViewById(R.id.htext5);
        tx6 = findViewById(R.id.htext6);
        tx7 = findViewById(R.id.htext7);

        date.setText("Current date: "+sDate);
        //DatabaseReference databaseReference= FirebaseDatabase.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Exercise").push();

        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(other.isChecked()){
                    hidden.setVisibility(View.VISIBLE);
                    tx7.setVisibility(View.VISIBLE);


                }else{
                    hidden.setVisibility(View.GONE);
                    tx7.setVisibility(View.GONE);
                }
            }
        });

        rice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rice.isChecked()){
                    tx1.setVisibility(View.VISIBLE);
                    String fname = tx1.getText().toString().trim();
                    result.append("\nWalking");
                    exdur.append(fname);

                }else{
                    tx1.setVisibility(View.GONE);
                }
            }
        });
        dal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(dal.isChecked()){
                    tx2.setVisibility(View.VISIBLE);
                    String fname = tx2.getText().toString().trim();
                    result.append("\nRunning");
                    exdur.append(fname);

                }else{
                    tx2.setVisibility(View.GONE);
                }
            }
        });
        bread.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(bread.isChecked()){
                    tx3.setVisibility(View.VISIBLE);
                    String fname = tx3.getText().toString().trim();
                    result.append("\nSwimming");
                    exdur.append(fname);

                }else{
                    tx3.setVisibility(View.GONE);
                }
            }
        });
        roti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(roti.isChecked()){
                    tx4.setVisibility(View.VISIBLE);
                    String fname = tx4.getText().toString().trim();
                    result.append("\nBiking/Cycling");
                    exdur.append(fname);

                }else{
                    tx4.setVisibility(View.GONE);
                }
            }
        });
        egg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(egg.isChecked()){
                    tx5.setVisibility(View.VISIBLE);
                    String fname = tx5.getText().toString().trim();
                    result.append("\nYoga and Pilates");
                    exdur.append(fname);

                }else{
                    tx5.setVisibility(View.GONE);
                }
            }
        });
        fish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(fish.isChecked()){
                    tx6.setVisibility(View.VISIBLE);
                    String fname = tx6.getText().toString().trim();
                    result.append("\nHigh Intensity Workouts");
                    exdur.append(fname);

                }else{
                    tx6.setVisibility(View.GONE);
                }
            }
        });

        foodsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rice.isChecked()){
                    //result.append("\nWalking");
                    String fname = tx1.getText().toString().trim();
                    exdur.append("\n"+fname);
                }
                if (dal.isChecked()){
                    //result.append("\nRunning");
                    String fname = tx2.getText().toString().trim();
                    exdur.append("\n"+fname);
                }
                if (bread.isChecked()){
                    //result.append("\nSwimming");
                    String fname = tx3.getText().toString().trim();
                    exdur.append("\n"+fname);
                }
                if (roti.isChecked()){
                   // result.append("\nBiking/Cycling");
                    String fname = tx4.getText().toString().trim();
                    exdur.append("\n"+fname);
                }
                if (egg.isChecked()){
                    //result.append("\nYoga and Pilates");
                    String fname = tx5.getText().toString().trim();
                    exdur.append("\n"+fname);
                }
                if (fish.isChecked()){
                   // result.append("\nHigh Intensity Workouts (HIT)");
                    String fname = tx6.getText().toString().trim();
                    exdur.append("\n"+fname);
                }if(other.isChecked()){
                    String fname = hidden.getText().toString().trim();
                    result.append("\n"+fname);

                    String fname1 = tx7.getText().toString().trim();
                    exdur.append("\n"+fname1);

                }

                diet = new Exercise(result.toString(), sDate, exdur.toString());

                ref.setValue(diet).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EnterExercise.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(EnterExercise.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });


    }
}