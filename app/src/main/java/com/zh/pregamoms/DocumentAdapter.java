package com.zh.pregamoms;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.Myholder>{
    private Context context;
    List<Documents> documents = new ArrayList<Documents>();

    public DocumentAdapter(Context context, List<Documents> documents) {
        this.context = context;
        this.documents = documents;
    }

    public DocumentAdapter() {
    }
    @NonNull
    @NotNull
    @Override
    public DocumentAdapter.Myholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.add_documents,parent,false);

        return new DocumentAdapter.Myholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DocumentAdapter.Myholder holder, int position) {
        String title1 = documents.get(position).getdTitle();
        String date1 = documents.get(position).getdDate();

        if (!documents.get(position).getBitmap().contains("http")) {
            try {
                Bitmap image = decodeFromFirebaseBase64(documents.get(position).getBitmap());
                holder.imageView.setImageBitmap(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.imageView);
        }

        holder.title.setText(title1);
        holder.date.setText(date1);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewImage.class);
                //Bundle extras = new Bundle();
//                intent.putString("EXTRA_title", title1);
//                extras.putString("EXTRA_photo", documents.get(position).getBitmap());

                intent.putExtra("EXTRA_title", title1);
                intent.putExtra("EXTRA_photo", documents.get(position).getBitmap());

                //intent.putExtras(extras);
                //view.getContext().startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                holder.delete.setVisibility(View.VISIBLE);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Documents").child("Prescription");

                Query query = ref.orderByChild("dTitle").equalTo(title1);

                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Documents").child("Reports");

                Query query1 = ref1.orderByChild("dTitle").equalTo(title1);

                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
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
        return documents.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private TextView title, date;
        private ImageView imageView;
        private CardView card;
        private Button delete;
        public Myholder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.dtitle);
            date = itemView.findViewById(R.id.ddate);
            imageView = itemView.findViewById(R.id.dimage);
            card = itemView.findViewById(R.id.card);
            delete = itemView.findViewById(R.id.delete);

        }
    }

    public static Bitmap decodeFromFirebaseBase64(String image) throws IOException {
        byte[] decodedByteArray = android.util.Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
}
