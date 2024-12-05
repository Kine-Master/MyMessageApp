package com.example.mymessageapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConversationListViewHolder extends RecyclerView.ViewHolder{
    TextView contact;
    TextView message;

    public ConversationListViewHolder(@NonNull View itemView){

        super(itemView);
        contact = itemView.findViewById(R.id.txtViewContact);
        message = itemView.findViewById(R.id.txtViewMessage);

    }

}