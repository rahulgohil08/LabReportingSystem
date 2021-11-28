package com.example.myproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.database.entities.Appointment;
import com.example.myproject.databinding.LayoutAppointmentBinding;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> appointmentList;


    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
     }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Appointment data = appointmentList.get(position);
        holder.binding.name.setText("Booked on: "+data.getDatetime());
//        holder.binding.date.setText(data.getName());


    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LayoutAppointmentBinding binding;

        public ViewHolder(@NonNull LayoutAppointmentBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }




}
