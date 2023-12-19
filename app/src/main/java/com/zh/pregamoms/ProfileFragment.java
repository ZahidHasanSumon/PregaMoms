package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private Button signout;
    private TextView tx1, tx2, tx3, tx4, tx5, tx6;

    FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase database;
    private FirebaseUser user;


    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        signout = v.findViewById(R.id.signout);
        user=FirebaseAuth.getInstance().getCurrentUser();
        mFirebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("UserInfo");

        tx1 = v.findViewById(R.id.textView1);
        tx2 = v.findViewById(R.id.textView2);
        tx3 = v.findViewById(R.id.textView3);
        tx4 = v.findViewById(R.id.textView4);
        tx5 = v.findViewById(R.id.textView5);
        tx6 = v.findViewById(R.id.textView6);


        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                NewUser user=dataSnapshot.getValue(NewUser.class);
                tx1.setText(user.getName());
                tx2.setText(user.getEmail());
                tx3.setText("DOB: "+user.getAge());
                tx4.setText("Weight : "+user.getWeight());
                tx5.setText("Pregnancy Week : "+user.getPweek());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });
        tx6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(getActivity(), EditProfileFragment.class));
                EditProfileFragment fragment = new EditProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        return v;
    }
}