package com.zh.pregamoms;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.WeightPerWeek;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class EditProfileFragment extends Fragment {
    private TextView tx1, tx2, tx3, tx4;
    private Button save;
    private TextView hback;
    FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase database;
    private FirebaseUser user;
    private String email, name, username, age, weight, pweek, password;
    private String yDate=" ";


    public EditProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        user=FirebaseAuth.getInstance().getCurrentUser();
        mFirebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("UserInfo");


        tx1 = v.findViewById(R.id.textView1);
        tx2 = v.findViewById(R.id.textView2);
        tx3 = v.findViewById(R.id.textView3);
        tx4 = v.findViewById(R.id.textView4);
        save = v.findViewById(R.id.save);
        hback = v.findViewById(R.id.hback);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        yDate = sdf.format(calendar.getTime());

        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                NewUser user=dataSnapshot.getValue(NewUser.class);
                tx1.setText(user.getName());
                tx2.setText(user.getEmail());
                tx3.setText(user.getWeight());
                tx4.setText(user.getPweek());
                email = user.getEmail();
                name = user.getName();
                age = user.getAge();
                username = user.getUsername();
                password = user.getPassword();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                weight = tx3.getText().toString().trim();
                pweek = tx4.getText().toString().trim();

                final String userid = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid());
                NewUser user = new NewUser(email, username, password, name, age, weight, pweek, userid);
                WeightPerWeek user1 = new WeightPerWeek(yDate, weight);
                databaseReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Successfully Saved!", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getActivity(),"Please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        hback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProfileFragment fragment = new ProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.addToBackStack(null);
                //fragmentTransaction.remove(EditProfileFragment.this);
                fragmentTransaction.commit();
                //fragmentManager.beginTransaction().remove(EditProfileFragment.this).commitAllowingStateLoss();

            }
        });


        return v;
    }



}