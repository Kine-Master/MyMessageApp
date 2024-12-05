package com.example.mymessageapp;


import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ConversationListRecyclerViewAdapter extends RecyclerView.Adapter<ConversationListViewHolder>{
    public List<Conversation> conversations;

    @NonNull
    @Override
    public ConversationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_items, parent, false);
        return new ConversationListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationListViewHolder holder, int position){
        Conversation conversation = conversations.get(position);

        holder.contact.setText(conversation.contact.Name);
        holder.message.setText(conversation.messages.get(0).message);
    }

    @Override
    public int getItemCount(){
        if (conversations == null){
            return 0;
        }
        return conversations.size();
    }
}
