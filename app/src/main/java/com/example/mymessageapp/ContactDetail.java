package com.example.mymessageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBackToContacts = findViewById(R.id.btnBackToContacts);
        btnBackToContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to go back to the Contacts activity
                Intent intent = new Intent(ContactDetail.this, Contacts.class);
                startActivity(intent);
                finish(); // Optional: call finish() if you want to remove DetailActivity from the back stack
            }
        });

        String contactName = getIntent().getStringExtra("contactName");
        String contactID = getIntent().getStringExtra("contactID");

        TextView nameTextView = findViewById(R.id.contactNameTextView);
        TextView idTextView = findViewById(R.id.contactIDTextView);

        nameTextView.setText(contactName);
        idTextView.setText(contactID);

    }
}