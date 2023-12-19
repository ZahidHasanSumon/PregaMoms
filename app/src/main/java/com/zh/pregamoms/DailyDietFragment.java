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


public class DailyDietFragment extends Fragment {
    private TextView hback;
    private CardView importantnu, pfood, diet, diethistory;
    private Info info;
    private String val = " ";



    public DailyDietFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_diet, container, false);

        hback = v.findViewById(R.id.hback);
        importantnu = v.findViewById(R.id.importantnu);
        diet = v.findViewById(R.id.diet);
        diethistory = v.findViewById(R.id.diethistory);

        pfood = v.findViewById(R.id.pfood);

        info = new Info();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("info");

        diethistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DietHistory.class));

            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EnterDiet.class));
            }
        });

        importantnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getImportantNu();
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

        pfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        info = snapshot.getValue(Info.class);
                        //String value = snapshot.getValue(getActivity());
                        val = info.getProhibatedFood();
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