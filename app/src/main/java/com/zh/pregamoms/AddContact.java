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

public class AddContact extends AppCompatActivity {
    private TextView habck;
    private EditText name, phon_no;
    private String cname = "", cphone = "";
    private Button save;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        habck = findViewById(R.id.hback);

        name = findViewById(R.id.name);
        phon_no = findViewById(R.id.phone);
        save = findViewById(R.id.save);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        habck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname = name.getText().toString().trim();
                cphone = phon_no.getText().toString().trim();
                ReadContact getContactDb = new ReadContact(cname, cphone);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Contact_Info").push();

                ref.setValue(getContactDb).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddContact.this, "Contact Added Successful!", Toast.LENGTH_LONG).show();
                            //startActivity(new Intent(SignUpSecondPage.this, MainActivity.class));
                            finish();
                        } else {
                            //display a failure message
                            Toast.makeText(AddContact.this,"Please try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}