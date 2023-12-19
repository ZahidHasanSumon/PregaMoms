package com.zh.pregamoms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.zh.pregamoms.classes.WeightPerWeek;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class EnterWeight extends AppCompatActivity {
    private TextView date;
    private EditText weight;
    private String getWeight = "";
    private String sDate = "";
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_weight);

        date = findViewById(R.id.showdate);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        sDate = sdf.format(calendar.getTime());
        date.setText("Current date: "+sDate);

        weight = findViewById(R.id.weightedit);
        save = findViewById(R.id.save);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("WeightPWeek").push();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeight = weight.getText().toString().trim();

                WeightPerWeek weightPerWeek = new WeightPerWeek(sDate, getWeight);

                ref.setValue(weightPerWeek).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(EnterWeight.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(EnterWeight.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });


    }
}