package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.Info;

import org.jetbrains.annotations.NotNull;

public class HealthFragment extends Fragment {
    private TextView hback;
    private CardView cardView, bodych, postpreg, mentalhealth, physicalcom, emergencycon;
    private Info info;
    private String val = " ";


    public HealthFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health, container, false);
        hback = v.findViewById(R.id.hback);
        bodych = v.findViewById(R.id.bodych);
        postpreg = v.findViewById(R.id.postpreg);
        mentalhealth = v.findViewById(R.id.mentalhealth);
        physicalcom = v.findViewById(R.id.physicalcom);
        emergencycon = v.findViewById(R.id.emergencycon);
        info = new Info();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("info");

        emergencycon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getEmergencyCon();
                        Intent i = new Intent(getActivity(),ViewHealthTab.class);
                        i.putExtra("YourValueKey",val);
                        startActivity(i);



                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        physicalcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getPhysicalCom();
                        Intent i = new Intent(getActivity(),ViewHealthTab.class);
                        i.putExtra("YourValueKey",val);
                        startActivity(i);



                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        mentalhealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getMentalHealth();
                        Intent i = new Intent(getActivity(),ViewHealthTab.class);
                        i.putExtra("YourValueKey",val);
                        startActivity(i);



                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });

        bodych.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getBodyChanges();
//                        Intent i = new Intent(getActivity(),ViewHealthTab.class);
//                        i.putExtra("YourValueKey",val);
//                        startActivity(i);
                        startActivity(new Intent(getActivity(),BodyChanges.class));



                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
             //startActivity(new Intent(getActivity(), ViewHealthTab.class));

            }
        });

        postpreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getPostPreg();
                        Intent i = new Intent(getActivity(),ViewHealthTab.class);
                        i.putExtra("YourValueKey",val);
                        startActivity(i);



                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

            }
        });


        hback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();

            }
        });


        return v;
    }






}