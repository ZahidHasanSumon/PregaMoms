package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button ButtonSignIn, ButtonSignUp;
    private EditText email, password;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButtonSignIn = findViewById(R.id.buttonSignIn);
        ButtonSignUp = findViewById(R.id.buttonSignUp);
        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.TextPassword);



        ButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String pwd = password.getText().toString();
                if(mail.isEmpty()){
                    email.setError("Please enter email id");
                    email.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                } else  if(!(mail.isEmpty() && pwd.isEmpty())){

                    mFirebaseAuth.signInWithEmailAndPassword(mail, pwd)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(i);
                                        finish();



                                    }

                                }
                            });
                }
                else{
                    Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }


}