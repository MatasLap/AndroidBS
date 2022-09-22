package com.example.jsonread;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jsonread.MyViewHolder;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    ArrayList<String> firstName;
    ArrayList<String> lastName;
    ArrayList<String> phone;
    ArrayList<String> email;
    Context ctx;

    public CustomAdapter(ArrayList<String> firstName, ArrayList<String> lastName, ArrayList<String> phone, ArrayList<String> email, Context ctx) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.firstName.setText(firstName.get(position));
        holder.lastName.setText(lastName.get(position));
        holder.phone.setText(phone.get(position));
        holder.email.setText(email.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, firstName.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return firstName.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, phone, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);

        }
    }


}