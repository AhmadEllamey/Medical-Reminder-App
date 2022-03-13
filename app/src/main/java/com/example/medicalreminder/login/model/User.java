package com.example.medicalreminder.login.model;

import java.io.Serializable;

public class User implements Serializable {


    private String name;
    private String dob;
    private String email;
    private String gender;
    private String password;
    private String phone;


    public User(String name, String dob, String email, String gender, String password, String phone) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.phone = phone;
    }



    public User() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
