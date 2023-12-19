package com.zh.pregamoms;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContactAdpter extends RecyclerView.Adapter<ContactAdpter.Myholder> {
    Context context;
    List<ReadContact> readContact;


    public ContactAdpter(Context context, List<ReadContact> readContact) {
        this.context = context;
        this.readContact = readContact;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contact_info,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdpter.Myholder holder, int position) {
        //readContact = new ArrayList<GetContactDb>();
        String name = readContact.get(position).getName();
        String phone = readContact.get(position).getPhone();

        holder.name.setText(name);
        holder.phone.setText(phone);

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = (String) holder.phone.getText();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                context.startActivity(intent);
            }
        });


        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                    holder.delete.setVisibility(View.VISIBLE);
                    holder.call.setVisibility(View.GONE);

                    holder.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Contact_Info");

                            Query query = ref.orderByChild("phone").equalTo(phone);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    for (DataSnapshot delete : snapshot.getChildren()) {

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

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.delete.setVisibility(View.GONE);
                holder.call.setVisibility(View.VISIBLE);

            }
        });






    }

    @Override
    public int getItemCount() {
        return readContact.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private TextView name, phone;
        private ImageView call, delete;
        private LinearLayout layout;
        private CardView card;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name1);
            phone = itemView.findViewById(R.id.contact1);
            call = itemView.findViewById(R.id.call);
            delete = itemView.findViewById(R.id.delete);
            layout = itemView.findViewById(R.id.layout);
            card = itemView.findViewById(R.id.card11);

        }
    }
}
