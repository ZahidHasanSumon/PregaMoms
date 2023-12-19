package com.zh.pregamoms;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPrescription extends AppCompatActivity {
    private RecyclerView drecyclerview;
    AddDocuments addDocuments;
    List<Documents> documents;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private TextView hback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescription);
        drecyclerview = findViewById(R.id.drecyclerview);
//        toolbar = findViewById(R.id.toolbar);
//        ((AppCompatActivity)ViewPrescription.this).setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        drecyclerview.setHasFixedSize(true);
        drecyclerview.setLayoutManager(new LinearLayoutManager(ViewPrescription.this));

        documents = new ArrayList<Documents>();
        hback =  findViewById(R.id.hback);

        hback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        show();
    }


    private void show(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Documents").child("Prescription").getRef();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                documents.clear();


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Documents document = ds.getValue(Documents.class);

                    documents.add(document);
                    DocumentAdapter documentAdapter = new DocumentAdapter(getApplicationContext(), documents);
                    drecyclerview.setAdapter(documentAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



}