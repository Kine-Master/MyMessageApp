package com.example.mymessageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Messages extends AppCompatActivity {

    Button btnLogout;
    Button btnContacts;
    TextView textViewUserName;
    RecyclerView listReceiveMessages;
    SharedPreferences account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_messages);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //LOGOUT
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SharedPreferences.Editor editor = account.edit();
                 editor.remove("StudentId");
                 editor.remove("Name");
                 editor.apply();
                 Intent intent = new Intent(Messages.this, MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         }
        );
        //
        btnContacts = findViewById(R.id.btnContacts);
        btnContacts.setOnClickListener(view -> startActivity
                (new Intent(Messages.this, Contacts.class))
        );

        //Get saved account name inside the SharedPreferences in the file named "UserInfo"
        account = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String savedName = account.getString("Name", "");

        //New codes
        textViewUserName = findViewById(R.id.textViewUserName);
        textViewUserName.setText(savedName);

        //Old codes from activity 3
        listReceiveMessages = findViewById(R.id.recyclerViewMessages);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        lm.setItemPrefetchEnabled(true);
        listReceiveMessages.setLayoutManager(lm);


        ConversationListRecyclerViewAdapter adapter = new ConversationListRecyclerViewAdapter();

        adapter.conversations = new ArrayList<>();
        adapter.conversations = GetConversations();

        listReceiveMessages.setAdapter(adapter);
    }

    private List<Conversation> GetConversations(){
        List<Conversation> conversations = new ArrayList<>();

        //First Contact
        Contact contact1 = new Contact();
        contact1.Name = "Dave Vincent Alumberts";
        contact1.StudentID = "101262";

        Message message1 = new Message();
        message1.message = "Scatter, pagnaka cash-out Jollibee rekta!";
        message1.messageID = 1;
        message1.time = new GregorianCalendar(2024, 10,1);

        Conversation conversation1 = new Conversation();
        conversation1.contact = contact1;
        conversation1.messages.add(message1);


        //Second Contact
        Contact contact2 = new Contact();
        contact2.Name = "Battosai";
        contact2.StudentID = "101626";

        Message message2 = new Message();
        message2.message = "the pen is not mightier than the sword, saksakin kita dyan eh";
        message2.messageID = 2;
        message2.time = new GregorianCalendar(2024, 10,1);

        Conversation conversation2 = new Conversation();
        conversation2.contact = contact2;
        conversation2.messages.add(message2);


        //Add Contacts to Conversations
        conversations.add(conversation1);
        conversations.add(conversation2);

        return conversations;
    }

}