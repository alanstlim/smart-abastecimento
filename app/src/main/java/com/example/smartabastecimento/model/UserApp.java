package com.example.smartabastecimento.model;

import com.example.smartabastecimento.helper.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class UserApp {

    private String name, email, password,address, userId;


    public UserApp(){

    }

    public UserApp(String name, String email, String password, String address, String userId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.userId = userId;
    }

    public void SaveUser (){

        DatabaseReference firebase = ConfigFirebase.getFirebaseDatabase() ;
        firebase.child("users").child(this.userId).setValue(this);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Exclude
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
