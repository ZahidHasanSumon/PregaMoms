package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class DocumentsFragment extends Fragment {
    private Button addPR;
    private CardView card1, card2;



    public DocumentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_documents, container, false);
        addPR = v.findViewById(R.id.addPR);
        card1 = v.findViewById(R.id.card_pres);
        card2 = v.findViewById(R.id.card_reports);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ViewPrescription.class ));
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ViewReport.class ));

            }
        });

        addPR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddDocuments.class));
            }
        });

       return v;
    }
}