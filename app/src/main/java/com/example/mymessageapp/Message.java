package com.example.mymessageapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Message {
    public int messageID;
    public String message;
    public Calendar time;
    public boolean isReceived;

    public Message(){
        time = new GregorianCalendar(2024, 10,1);
    }

}
