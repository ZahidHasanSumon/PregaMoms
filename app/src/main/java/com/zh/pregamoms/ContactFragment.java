package com.zh.pregamoms;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {
    private Toolbar toolbar;
    private FloatingActionButton fbutton;
    private RecyclerView recyclerView;
    ContactAdpter contactAdpter;
    List<ReadContact> readContact;
    private FirebaseAuth mAuth;


    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
       // toolbar.setTitle("Search Contact");
        fbutton = v.findViewById(R.id.fbutton);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = v.findViewById(R.id.contactList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        readContact = new ArrayList<ReadContact>();
        show();



        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddContact.class));
            }
        });





        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        inflater.inflate(R.menu.search_bar, menu);

        MenuItem item=menu.findItem(R.id.action_search);

        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query.trim())){
                    //Toast.makeText(getActivity(),query,Toast.LENGTH_LONG).show();
                    searchUsers(query);



                }else{
                   show();

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText.trim())){
                   searchUsers(newText);


                }else{
                   show();

                }
                return false;
            }
        });



        super.onCreateOptionsMenu(menu, inflater);
    }
    private void searchUsers(final String newText) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Contact_Info").getRef();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readContact.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ReadContact modelUser = ds.getValue(ReadContact.class);

                    /*if(!modelUser.getUid().equals(fuser.getUid())){
                        userList.add(modelUser);


                    }*/

                    if (modelUser.getName().toLowerCase().contains(newText.toLowerCase())||
                            modelUser.phone.toLowerCase().contains(newText.toLowerCase())){

                        readContact.add(modelUser);

                    }

                    contactAdpter =new ContactAdpter(getActivity(),readContact);
                    contactAdpter.notifyDataSetChanged();
                    recyclerView.setAdapter(contactAdpter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void show(){

         DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Contact_Info").getRef();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readContact.clear();


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ReadContact contact = ds.getValue(ReadContact.class);

                        readContact.add(contact);
                        ContactAdpter contactAdpter = new ContactAdpter(getActivity(), readContact);
                        recyclerView.setAdapter(contactAdpter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}