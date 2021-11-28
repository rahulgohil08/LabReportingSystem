package com.example.myproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.database.entities.Lab;
import com.example.myproject.databinding.LayoutEmployeeBinding;

import java.util.List;

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.ViewHolder> {

    private Context context;
    private List<Lab> labList;
    private LabInterface labInterface;


    public LabAdapter(Context context, List<Lab> labList, LabInterface labInterface) {
        this.context = context;
        this.labList = labList;
        this.labInterface = labInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Lab lab = labList.get(position);
        holder.binding.name.setText(lab.getName());

        holder.binding.imageEdit.setOnClickListener(view -> {
            labInterface.onEdit(lab);
        });

        holder.binding.imageDelete.setOnClickListener(view -> {
            labInterface.onDelete(lab);
        });

    }

    @Override
    public int getItemCount() {
        return labList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LayoutEmployeeBinding binding;

        public ViewHolder(@NonNull LayoutEmployeeBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }


    public interface LabInterface {
        void onEdit(Lab lab);

        void onDelete(Lab lab);
    }

}
