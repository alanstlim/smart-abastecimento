package com.example.smartabastecimento.activity.menuprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartabastecimento.R;
import com.example.smartabastecimento.activity.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MenuLogoutActivity extends AppCompatActivity {

    private FirebaseAuth dbUser = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        dbUser.signOut();
        Intent i = new Intent (getApplicationContext(), StartActivity.class);
        startActivity(i);
        finish();


    }
}