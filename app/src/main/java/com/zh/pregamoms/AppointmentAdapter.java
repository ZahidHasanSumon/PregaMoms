package com.zh.pregamoms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.Myholder> {
    Context context;
    List<NewUser> newUser = new ArrayList<NewUser>();

    public AppointmentAdapter(Context context, List<NewUser> newUser) {
        this.context = context;
        this.newUser = newUser;
    }

    public AppointmentAdapter() {
    }

    @NonNull
    @NotNull
    @Override
    public AppointmentAdapter.Myholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.appointment_info,parent,false);

        return new AppointmentAdapter.Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AppointmentAdapter.Myholder holder, int position) {
        String title1 = newUser.get(position).getTitle();
        String time1 = newUser.get(position).getTime();
        String place1 = newUser.get(position).getPlace();

        holder.title.setText(title1);
        holder.time.setText(time1);
        holder.place.setText(place1);

    }

    @Override
    public int getItemCount() {
        return newUser.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        private TextView title, time, place;
        public Myholder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title10);
            time = itemView.findViewById(R.id.time);
            place = itemView.findViewById(R.id.location);
        }
    }
}
