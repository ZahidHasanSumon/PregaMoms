package com.zh.pregamoms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUpSecondPage extends AppCompatActivity {
    private Button buttonSave;
    private EditText yname, yweight, ypregweek;
    private TextView yage;

    private FirebaseAuth firebaseAuth;

    private NewUser user;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_second_page);
        buttonSave = findViewById(R.id.buttonSave);
        yname = findViewById(R.id.yourname);
        yage = findViewById(R.id.age);
        yweight = findViewById(R.id.weight);
        ypregweek = findViewById(R.id.pregweek);


        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        final String email_string = extras.getString("EXTRA_email");
        final String username_string = extras.getString("EXTRA_Username");
        final String password_string = extras.getString("EXTRA_Password");

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        yage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SignUpSecondPage.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });




        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String name = yname.getText().toString().trim();
                final String age = yage.getText().toString().trim();
                final String weight = yweight.getText().toString().trim();
                final String pregnancyWeek = ypregweek.getText().toString().trim();
                if (name.isEmpty()) {
                    yname.setError("Enter your name");
                    yname.requestFocus();
                    return;
                } else if (age.isEmpty()) {
                    yage.setError("Enter your age");
                    yage.requestFocus();
                    return;
                } else if (weight.isEmpty()) {
                    yweight.setError("Enter your weight");
                    yweight.requestFocus();
                    return;
                } else if (pregnancyWeek.isEmpty()) {
                    ypregweek.setError("Enter pregnancy week");
                    ypregweek.requestFocus();
                    return;
                }else {

                    firebaseAuth.createUserWithEmailAndPassword(email_string, password_string)
                            .addOnCompleteListener(SignUpSecondPage.this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {


                                        final String userid=String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                        user = new NewUser(email_string, username_string, password_string, name, age, weight, pregnancyWeek, userid);

                                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("UserInfo")
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(SignUpSecondPage.this, "Successful!", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(SignUpSecondPage.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    //display a failure message
                                                    Toast.makeText(SignUpSecondPage.this,"Please try again",Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(SignUpSecondPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }


                            });
                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        yage.setText(sdf.format(myCalendar.getTime()));
    }



}