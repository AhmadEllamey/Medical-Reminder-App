package com.example.medicalreminder.addhealthtaker;

import java.io.Serializable;

public class RequestModel implements Serializable {
    private  String sender_email;
    private String receiever_email;
    private String sender_name;
    private String reciever_name;
    private String request_status;
    public RequestModel(){}
public RequestModel(String sender_email,String sender_name,String receiever_email,String reciever_name){
    this.sender_email=sender_email;
    this.sender_name=sender_name;
    this.receiever_email=receiever_email;
    this.reciever_name=reciever_name;
}
    public RequestModel(String sender_email,String sender_name,String receiever_email,String reciever_name,String request_status){
        this.sender_email=sender_email;
        this.sender_name=sender_name;
        this.receiever_email=receiever_email;
        this.reciever_name=reciever_name;
        this.request_status=request_status;
    }

    public String getSender_email() {
        return sender_email;
    }

    public void setSender_email(String sender_email) {
        this.sender_email = sender_email;
    }

    public String getReceiever_email() {
        return receiever_email;
    }

    public void setReceiever_email(String receiever_email) {
        this.receiever_email = receiever_email;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReciever_name() {
        return reciever_name;
    }

    public void setReciever_name(String reciever_name) {
        this.reciever_name = reciever_name;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }
}
