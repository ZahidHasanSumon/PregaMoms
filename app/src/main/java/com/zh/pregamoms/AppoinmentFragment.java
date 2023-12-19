package com.zh.pregamoms;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


public class AppoinmentFragment extends Fragment {
    private TextView hback;
    private Button addApp;
    private RecyclerView recyclerView;
    AppointmentAdapter appointmentAdapter;
    List<NewUser> newUser;
    private FirebaseAuth mAuth;
    private CalendarView calendarView;
    long calendarDate;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    private ArrayList<String> dates;
    final Calendar myCalendar = Calendar.getInstance() ;
    private String sDate, checkDate;
    private String yDate=" ";




    public AppoinmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_appoinment, container, false);
        hback = v.findViewById(R.id.hback);
        addApp = v.findViewById(R.id.addapp);
        recyclerView = v.findViewById(R.id.recyclerview1);
        mAuth = FirebaseAuth.getInstance();
        calendarView = v.findViewById(R.id.calendar);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        appointmentAdapter = new AppointmentAdapter();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newUser = new ArrayList<NewUser>();
        dates = new ArrayList<>();
        updateLabel();

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        yDate = sdf.format(calendar.getTime());
        show(yDate);



        hback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                calendarDate = calendarView.getDate();
               // String calendarDate1 = String.valueOf(calendarDate);


                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                Calendar calendar = Calendar.getInstance();
                calendar.set(i, i1, i2);
                String sDate = sdf.format(calendar.getTime());
                //Toast.makeText(getActivity(), sDate, Toast.LENGTH_LONG).show();
                show(sDate);

            }
        });

        addApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddAppointment.class));
            }
        });
        return v;
    }

    private void show(String date){


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Appointment_Info").getRef();

        if(appointmentAdapter.getItemCount() == 0){

            newUser.clear();
            AppointmentAdapter appointmentAdapter = new AppointmentAdapter(getActivity(), newUser);
            recyclerView.setAdapter(appointmentAdapter);
        }
            ref.orderByChild("date").equalTo(date).addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    newUser.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        NewUser user = ds.getValue(NewUser.class);

                        newUser.add(user);
                        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(getActivity(), newUser);
                        recyclerView.setAdapter(appointmentAdapter);

                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    newUser.clear();

                }
            });
        }




    private void scheduleNotification (Notification notification , long delay) {
        Intent notificationIntent = new Intent( getActivity(), MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( getActivity(), 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY , pendingIntent);  //set repeating every 24 hours

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , 0, pendingIntent) ;
    }

    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( getActivity(), default_notification_channel_id ) ;
        builder.setContentTitle( "You have upcoming appointments" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_none_24) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }

    private void updateLabel () {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        final SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        sDate = sdf.format(calendar.getTime());



        Date date = myCalendar .getTime() ;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Appointment_Info").getRef();

        ref.orderByChild("date").equalTo(sDate).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    NewUser user = ds.getValue(NewUser.class);
                    dates.add(user.getDate());
                    //  Toast.makeText(MainActivity.this, "Hell "+ dates.get(0), Toast.LENGTH_SHORT).show();

                }

                if(dates.size() == 0){


                }else if(dates.size() > 0){

                    long mill = 00;
                    String getdate = "01:00:00";
                    // Toast.makeText(MainActivity.this, "not 0 "+ getdate, Toast.LENGTH_SHORT).show();

                    Date date = null;
                    try {
                        date = sdf1.parse(getdate);
                        mill = date.getTime();
                        //Toast.makeText(MainActivity.this, "here "+ mill, Toast.LENGTH_SHORT).show();
                        scheduleNotification(getNotification("On Date "+sDate), mill) ;
                    } catch (ParseException e) {
                        e.printStackTrace();

                    }


                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }




}