package com.example.mymessageapp;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    public List<Contact> contacts;
    public String errorMessage;

    public ContactList(){
        contacts = new ArrayList<>();
    }

    public String getContacts(){
        return contacts.get(0).Name;
    }

    public String getContactId(){
        return contacts.get(0).StudentID;
    }
}
