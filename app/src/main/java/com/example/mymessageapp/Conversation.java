package com.example.mymessageapp;


import java.util.ArrayList;
import java.util.List;

public class Conversation {
    public int conversationID;
    public Contact contact;
    public List<Message> messages;
    public String time;

    public Conversation(){
        messages = new ArrayList<>();
    }

    public String getMessages() {
        return messages.get(0).message;
    }

}