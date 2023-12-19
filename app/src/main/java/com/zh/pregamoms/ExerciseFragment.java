package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class ExerciseFragment extends Fragment {
    private TextView hback;
    private CardView enterex, exhistory, vex, cardothers;


    public ExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exercise, container, false);

        hback = v.findViewById(R.id.hback);
        enterex = v.findViewById(R.id.enterex);
        exhistory = v.findViewById(R.id.exhistory);
        vex = v.findViewById(R.id.vex);
        cardothers = v.findViewById(R.id.cardothers);

        vex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ViewYourEx.class));

            }
        });

        cardothers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EnterWeight.class));

            }
        });

        exhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ExerciseHistory.class));

            }
        });

        enterex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EnterExercise.class));
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