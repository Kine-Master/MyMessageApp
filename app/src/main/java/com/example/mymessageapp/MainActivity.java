package com.example.mymessageapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText studentID;
    ApiAccess apiAccess;
    SharedPreferences account;
    String studentIdInput;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        studentID = findViewById(R.id.enterStudentNumber);

        // Initialize Retrofit and API Access
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.3:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiAccess = retrofit.create(ApiAccess.class);

        // Initialize SharedPreferences
        account = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String savedStudentID = account.getString("StudentId", "");

        // If StudentId is saved, skip login and go directly to Messages activity
        if (!savedStudentID.isEmpty()) {
            CallMessages();
            return;
        }

        // Button action for logging in
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentIdInput = studentID.getText().toString();

                if (!studentIdInput.isEmpty()) {
                    Call<Contact> call = apiAccess.getStudentName(studentIdInput);
                    call.enqueue(new Callback<Contact>() {
                        @Override
                        public void onResponse(Call<Contact> call, Response<Contact> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                Contact contact = response.body();

                                // Save login info in SharedPreferences
                                SharedPreferences.Editor editor = account.edit();
                                editor.putString("StudentId", contact.StudentID);
                                editor.putString("Name", contact.Name); // Save additional info if needed
                                editor.apply();

                                CallMessages();
                            } else {
                                Log.e(TAG, "Response unsuccessful or empty body");
                            }
                        }

                        @Override
                        public void onFailure(Call<Contact> call, Throwable t) {
                            Log.e(TAG, "API call failed: " + t.getMessage());
                        }
                    });
                } else {
                    Log.e(TAG, "Student ID input is empty");
                }
            }
        });
    }

    private void CallMessages() {
        // Redirect to Messages activity
        startActivity(new Intent(MainActivity.this, Messages.class));
        finish(); // Close MainActivity so it does not remain in the back stack
    }
}
