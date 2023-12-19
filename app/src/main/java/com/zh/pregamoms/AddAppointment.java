package com.zh.pregamoms;

import android.app.DatePickerDialog;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAppointment extends AppCompatActivity {
    private TextView habck;
    private Button save;
    private EditText title, time, place;
    private TextView tDate;
    private DatabaseReference mDatabase;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        habck = findViewById(R.id.hback);
        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        place = findViewById(R.id.place);
        tDate = findViewById(R.id.date);

        save = findViewById(R.id.save);
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

        tDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddAppointment.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });




        habck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title1 = title.getText().toString().trim();
                String date1 = tDate.getText().toString().trim();
                String time1 = time.getText().toString().trim();
                String place1 = place.getText().toString().trim();

                NewUser newUser = new NewUser(title1, date1, time1, place1);
                //Toast.makeText(AddAppointment.this, date1, Toast.LENGTH_LONG).show();

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Appointment_Info").push();

                ref.setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddAppointment.this, "Added Successful!", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(SignUpSecondPage.this, MainActivity.class));
                            finish();
                        } else {
                            //display a failure message
                            Toast.makeText(AddAppointment.this, "Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        tDate.setText(sdf.format(myCalendar.getTime()));
    }
}
