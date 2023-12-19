package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final Handler handler = new Handler(Looper.myLooper());
        mFirebaseAuth = FirebaseAuth.getInstance();



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                        if( mFirebaseUser != null ){
                           // Toast.makeText(SplashScreen.this,"You are logged in",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(i);
                            finish();

                        }else {
                            startActivity(new Intent(SplashScreen.this, LoginActivity.class));

                        }

                    }
                };
                mFirebaseAuth.addAuthStateListener(mAuthStateListener);



            }
        },1000);//duration of the splash screen


    }

    @Override
    protected void onStart() {
        super.onStart();
      //  mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onStop() {
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
        super.onStop();
    }

}