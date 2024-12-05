package com.example.mymessageapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactListRecyclerViewAdapter extends RecyclerView.Adapter<ContactsListViewHolder> {

    private final ContactList contactList;
    private final Context context; // Add context

    public ContactListRecyclerViewAdapter(Context context, ContactList contactList) { // Update constructor
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_items, parent, false);
        return new ContactsListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsListViewHolder holder, int position) {
        if (contactList.errorMessage != null) {
            holder.contactsList.setText(contactList.errorMessage);
            holder.contactID.setText(" ");
        } else if (contactList.contacts.isEmpty()) {
            holder.contactsList.setText("No contacts available.");
            holder.contactID.setText("");
        } else {
            Contact contact = contactList.contacts.get(position);
            holder.contactsList.setText(contact.Name);
            holder.contactID.setText(contact.StudentID);

            // Set an OnClickListener for the entire item view
            holder.itemView.setOnClickListener(v -> {
                // Start the DetailActivity and pass the contact details
                Intent intent = new Intent(context, ContactDetail.class);
                intent.putExtra("contactName", contact.Name);
                intent.putExtra("contactID", contact.StudentID);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        // Return 1 if there are no contacts to display the error message, otherwise return the size of the list.
        return (contactList.contacts == null || contactList.contacts.isEmpty()) ? 1 : contactList.contacts.size();
    }
}
