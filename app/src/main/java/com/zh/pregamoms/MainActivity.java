package com.zh.pregamoms;


import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.MyNotificationPublisher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private MeowBottomNavigation bnv_Main;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    private ArrayList<String> dates;
    final Calendar myCalendar = Calendar.getInstance() ;
    private String sDate, checkDate;
    List<NewUser> newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dates = new ArrayList<>();
        newUser = new ArrayList<NewUser>();


        bnv_Main = findViewById(R.id.bnv_Main);
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home_black_24dp));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.ic_description_black_24dp));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.ic_person_black_24dp));
        bnv_Main.add(new MeowBottomNavigation.Model(4,R.drawable.ic_phone_black_24dp));
       // bnv_Main.add(new MeowBottomNavigation.Model(5,R.drawable.ic_settings_black_24dp));

        bnv_Main.show(1,true);
        replace(new HomeFragment());
        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new HomeFragment());
                        break;

                    case 2:
                        replace(new DocumentsFragment());
                        break;

                    case 3:
                        replace(new ProfileFragment());
                        break;

                    case 4:
                        replace(new ContactFragment());
                        break;


                }
                return null;
            }
        });




    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Prega Moms")
                .setMessage("Do you want to close the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}