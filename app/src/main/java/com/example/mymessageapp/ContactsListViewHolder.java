package com.example.mymessageapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsListViewHolder extends RecyclerView.ViewHolder{
    TextView contactsList;
    TextView contactID;

    public ContactsListViewHolder(@NonNull View itemView){
        super(itemView);
        contactsList = itemView.findViewById(R.id.textContacts);
        contactID = itemView.findViewById(R.id.textContactID);
    }
}
