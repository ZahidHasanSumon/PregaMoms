package com.zh.pregamoms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.zh.pregamoms.classes.Symptoms;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ViewSymptomsAdapter extends RecyclerView.Adapter<ViewSymptomsAdapter.Myholder> {

    Context context;
    List<Symptoms> symptoms;

    public ViewSymptomsAdapter(Context context, List<Symptoms> symptoms) {
        this.context = context;
        this.symptoms = symptoms;
    }

    @NonNull
    @NotNull
    @Override
    public Myholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.view_sypmtoms_perday,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Myholder holder, int position) {
        String fname = symptoms.get(position).getSymptoms();
        String date = symptoms.get(position).getDate();

        holder.name.setText("Symptoms:"+fname);
        holder.date.setText("Date: "+date);

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                holder.delete.setVisibility(View.VISIBLE);


                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Symptoms");

                        Query query = ref.orderByChild("symptoms").equalTo(fname);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                for(DataSnapshot delete: snapshot.getChildren()){

                                    delete.getRef().removeValue();
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });


                    }
                });
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private TextView date, name;
        private ImageView delete;
        private CardView card;
        public Myholder(@NonNull @NotNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.symptoms);
            delete = itemView.findViewById(R.id.delete);
            card = itemView.findViewById(R.id.dietcard);
        }
    }
}
