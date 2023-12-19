package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.zh.ViewReports;

public class OthersFragment extends Fragment {
    private TextView hback;
    private CardView tracker, reports;


    public OthersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_others, container, false);

        hback = v.findViewById(R.id.hback);
        tracker = v.findViewById(R.id.tracker);
        reports = v.findViewById(R.id.reports);

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ViewReports.class));

            }
        });


        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WeeklyTracker.class));

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