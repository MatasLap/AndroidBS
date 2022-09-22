package com.example.jsonread;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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