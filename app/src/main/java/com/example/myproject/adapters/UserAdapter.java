package com.example.myproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.database.entities.User;
import com.example.myproject.databinding.LayoutUserBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;
    private UserInterface userInterface;


    public UserAdapter(Context context, List<User> userList, UserInterface userInterface) {
        this.context = context;
        this.userList = userList;
        this.userInterface = userInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutUserBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = userList.get(position);
        holder.binding.name.setText(user.getName());

        holder.itemView.setOnClickListener(view -> {
            userInterface.onClick(user);
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LayoutUserBinding binding;

        public ViewHolder(@NonNull LayoutUserBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }


    public interface UserInterface {
        void onClick(User user);
    }

}
