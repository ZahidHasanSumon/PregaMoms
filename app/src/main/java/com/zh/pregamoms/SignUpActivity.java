package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private Button buttonNext;
    private EditText email, username, password, retypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        buttonNext = findViewById(R.id.buttonNext);
        email = findViewById(R.id.editTextTextEmailAddress);
        username = findViewById(R.id.editText);
        password = findViewById(R.id.editTextTextPassword);
        retypePassword = findViewById(R.id.editTextTextPassword1);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail = email.getText().toString().trim();
                final String name = username.getText().toString().trim();
                final String pwd = password.getText().toString().trim();
                String repwd = retypePassword.getText().toString().trim();



                if (mail.isEmpty()) {
                    email.setError("Please enter Email");
                    email.requestFocus();
                    return;
                } else if (name.isEmpty()) {
                    username.setError("Please enter username");
                    username.requestFocus();
                    return;
                } else if (pwd.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                } else if (repwd.isEmpty()) {
                    retypePassword.setError("Retype password");
                    retypePassword.requestFocus();
                    return;
                } else if (!repwd.equals(pwd)) {
                    retypePassword.setError("Password doesn't match");
//                    password.setText("");
//                    retypePassword.setText("");

                    return;

                }
                Intent intent = new Intent(SignUpActivity.this, SignUpSecondPage.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_email", mail);
                extras.putString("EXTRA_Username", name);
                extras.putString("EXTRA_Password", pwd);
                intent.putExtras(extras);
                startActivity(intent);

                //startActivity(new Intent(SignUpActivity.this, SignUpSecondPage.class));
            }
        });
    }
}