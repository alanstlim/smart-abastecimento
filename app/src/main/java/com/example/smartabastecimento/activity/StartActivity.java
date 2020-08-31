package com.example.smartabastecimento.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.smartabastecimento.R;
import com.example.smartabastecimento.helper.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    private FirebaseAuth dbUser;
    private ImageView imageGifStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imageGifStart = findViewById(R.id.imageGifStart);
        checkCurrentUser();

        Glide.with(this)
                .load(R.drawable.gif_logo) // aqui é teu gif
                .into(imageGifStart);

    }

    public void checkCurrentUser() {

        dbUser = ConfigFirebase.getFirebaseAuth();
        //Duração do activity
        final int MILISEGUNDOS = 3000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (dbUser.getCurrentUser() != null) {

                    mainScreen();

                } else {

                    loginScreen();

                }
            }
        }, MILISEGUNDOS);

    }

    public void mainScreen() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginScreen() {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

}