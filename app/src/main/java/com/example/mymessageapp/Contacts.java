package com.example.mymessageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Contacts extends AppCompatActivity {

    Button btnBack;
    RecyclerView listReceiveContacts;
    SharedPreferences account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        account = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String savedStudentId = account.getString("StudentId", "");
        //String savedName = account.getString("Name", "");

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> startActivity
                (new Intent(Contacts.this, Messages.class))
        );

        listReceiveContacts = findViewById(R.id.recyclerViewContacts);
        listReceiveContacts.setLayoutManager(new LinearLayoutManager(this));



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:5000/")  // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiAccess apiAccess = retrofit.create(ApiAccess.class);

        Call<List<Contact>> call = apiAccess.getContacts(savedStudentId);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                ContactList contactList = new ContactList();

                if (response.isSuccessful() && response.body() != null) {
                    // Create a ContactList and populate it with the contacts
                    List<Contact> contacts = response.body();
                    contactList.contacts.clear();
                    contactList.contacts.addAll(contacts);

                    // Set the adapter with the fetched contacts
                    ContactListRecyclerViewAdapter adapter = new ContactListRecyclerViewAdapter(Contacts.this, contactList);
                    listReceiveContacts.setAdapter(adapter);
                } else {
                    contactList.errorMessage = "Failed to fetch contacts. Please try again.";
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                ContactList contactList = new ContactList();
                //contactList.contacts.clear();
                contactList.errorMessage = "Error";
                ContactListRecyclerViewAdapter adapter = new ContactListRecyclerViewAdapter(Contacts.this, contactList);
                listReceiveContacts.setAdapter(adapter);
            }
        });




    }
}