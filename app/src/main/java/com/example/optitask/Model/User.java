package com.example.optitask.Model;

import androidx.annotation.NonNull;

public class User {
    private String displayname;
    private String email;
    private String phonenumber;
    private String uid;

    public User() {
    }

    public User(String displayname, String email, String uid) {
        this.displayname = displayname;
        this.email = email;
        this.uid = uid;
    }

    public User(String displayname, String email, String phonenumber, String uid) {
        this.displayname = displayname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.uid = uid;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @NonNull
    @Override
    public String toString() {
        return displayname
                +" \n"+email
                +" \n"+phonenumber
                +" \n"+uid;
    }
}
