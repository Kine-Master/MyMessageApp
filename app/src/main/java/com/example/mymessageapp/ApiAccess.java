package com.example.mymessageapp;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiAccess {

    @GET("/getStudentName")
    public Call<Contact> getStudentName(@Query("value") String value);

    //NEW CODES
    @GET("/getContacts")
    public Call<List<Contact>> getContacts(@Query("studentId") String value);

    @POST("/sendMessage")
    Call<Void> sendMessage(@Query("from") String fromId, @Query("to") String toId, @Query("message") String message);

    @GET("/getMessages")
    Call<List<Message>> getMessages(@Query("from") String fromId, @Query("to") String toId);



    //http://192.168.0.3:5000/sendMessage?from=223305&to=223127&message=messages%20ine
    //http://192.168.0.3:5000/getMessages?From=223305&To=223127

    //@POST("/sendMessage")
    //public Call<MessageContent> postMessages(@Query("from") String value);
    //@POST("/sendMessage")
    //public Call<MessageContent> postMessages(@Query("to") String value);
    //@POST("/sendMessage")
    //public Call<MessageSent> postMessages(@Query("message") String value);

    //@GET("getMessages")
    //public Call<List<MessageGot>> getMessages(@Query("From") String value);
    //@GET("getMessages")
    //public Call<List<MessageGot>> getMessages(@Query("to") String value);
    //@GET("getMessages")
    //public Call<List<MessageGot>> getMessages(@Query("message") String value);




}
