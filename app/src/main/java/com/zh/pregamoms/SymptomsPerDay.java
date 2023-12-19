package com.zh.pregamoms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zh.pregamoms.classes.Symptoms;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SymptomsPerDay extends AppCompatActivity {
    private TextView date;
    private CheckBox other, rice, dal, bread, roti, egg, fish, chicken, beef, mutton, vegetable, milk, junk;
    private EditText hidden;
    private Button foodsave;
    private Symptoms symptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_per_day);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        Calendar calendar = Calendar.getInstance();
        String sDate = sdf.format(calendar.getTime());

        StringBuilder result=new StringBuilder();
        symptoms = new Symptoms();

        date = findViewById(R.id.showdate);
        other = findViewById(R.id.cother);
        hidden = findViewById(R.id.htext);
        rice = findViewById(R.id.rice);
        dal = findViewById(R.id.dal);
        bread = findViewById(R.id.bread);
        roti = findViewById(R.id.roti);
        egg = findViewById(R.id.egg);
        fish = findViewById(R.id.fish);
        chicken = findViewById(R.id.chicken);
        beef = findViewById(R.id.beef);
        mutton = findViewById(R.id.mutton);
        vegetable = findViewById(R.id.vegetable);
        milk = findViewById(R.id.milk);
        junk = findViewById(R.id.junk);
        foodsave = findViewById(R.id.foodsave);

        date.setText("Current date: "+sDate);
        //DatabaseReference databaseReference= FirebaseDatabase.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Symptoms").push();

        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(other.isChecked()){
                    hidden.setVisibility(View.VISIBLE);
                    String fname = hidden.getText().toString().trim();
                    result.append(fname);

                }else{
                    hidden.setVisibility(View.GONE);
                }
            }
        });

        foodsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rice.isChecked()){
                    result.append("\nNausea and vomiting");
                }
                if (dal.isChecked()){
                    result.append("\nFatigue");
                }
                if (bread.isChecked()){
                    result.append("\nHemorrhoids");
                }
                if (roti.isChecked()){
                    result.append("\nVaricose veins");
                }
                if (egg.isChecked()){
                    result.append("\nHeartburn and indigestion");
                }
                if (fish.isChecked()){
                    result.append("\nBleeding gums");
                }
                if (chicken.isChecked()){
                    result.append("\nSwelling or fluid retention");
                }
                if (beef.isChecked()){
                    result.append("\nSkin changes");
                }
                if (mutton.isChecked()){
                    result.append("\nStretch marks");
                }
                if (vegetable.isChecked()){
                    result.append("\nCongested or bloody nose");
                }
                if (milk.isChecked()){
                    result.append("\nBackache");
                }
                if (junk.isChecked()){
                    result.append("\nConstipation");
                }
                if(other.isChecked()){
                    String fname = hidden.getText().toString().trim();
                    result.append("\n"+fname);

                }

                symptoms = new Symptoms(result.toString(), sDate);

                ref.setValue(symptoms).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SymptomsPerDay.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(SymptomsPerDay.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });


    }
}