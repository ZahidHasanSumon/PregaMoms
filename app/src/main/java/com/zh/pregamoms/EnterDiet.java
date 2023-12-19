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
import com.zh.pregamoms.classes.Diet;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EnterDiet extends AppCompatActivity {
    private TextView date;
    private CheckBox other, rice, dal, bread, roti, egg, fish, chicken, beef, mutton, vegetable, milk, junk;
    private EditText hidden;
    private Button foodsave;
    private Diet diet;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_diet);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        StringBuilder result=new StringBuilder();
        diet = new Diet();


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
                .child("Diet").push();

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
                    result.append("\nRice");
                }
                if (dal.isChecked()){
                    result.append("\nDal");
                }
                if (bread.isChecked()){
                    result.append("\nBread");
                }
                if (roti.isChecked()){
                    result.append("\nRoti");
                }
                if (egg.isChecked()){
                    result.append("\nEgg");
                }
                if (fish.isChecked()){
                    result.append("\nFish");
                }
                if (chicken.isChecked()){
                    result.append("\nChicken");
                }
                if (beef.isChecked()){
                    result.append("\nBeef");
                }
                if (mutton.isChecked()){
                    result.append("\nMutton");
                }
                if (vegetable.isChecked()){
                    result.append("\nVegetable");
                }
                if (milk.isChecked()){
                    result.append("\nMilk");
                }
                if (junk.isChecked()){
                    result.append("\nJunk Food");
                }
                if(other.isChecked()){
                    String fname = hidden.getText().toString().trim();
                    result.append("\n"+fname);

                }

                diet = new Diet(result.toString(), sDate);

                ref.setValue(diet).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EnterDiet.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(EnterDiet.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });


    }
}