package com.example.smartabastecimento.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.smartabastecimento.R;
import com.example.smartabastecimento.fragment.CalculatorFragment;
import com.example.smartabastecimento.fragment.ProfileFragment;
import com.example.smartabastecimento.fragment.StationFragment;
import com.example.smartabastecimento.helper.ConfigFirebase;
import com.example.smartabastecimento.model.Station;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configBottomNagivationView();

    }

    //Configurações do Bottom Navigation
    private void configBottomNagivationView (){

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation);

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        bottomNavigationViewEx.setTextVisibility(true);
        bottomNavigationViewEx.setIconSize(30);
        bottomNavigationViewEx.setTextSize(12);
        bottomNavigationViewEx.setIconsMarginTop(8);

        //Habilitar Navegação
        enableNavigation(bottomNavigationViewEx);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new StationFragment()).commit();

        //Configurar item selecionado incialmente
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

    private void enableNavigation (BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                        R.anim.enter_left_to_right, R.anim.exit_left_to_right);

                switch (menuItem.getItemId()){
                    case R.id.ic_stations :
                        fragmentTransaction.replace(R.id.viewPager, new StationFragment()).commit();
                        return true;
                    case R.id.ic_calculator :
                        fragmentTransaction.replace(R.id.viewPager, new CalculatorFragment()).commit();
                        return true;
                    case R.id.ic_profile :
                        fragmentTransaction.replace(R.id.viewPager, new ProfileFragment()).commit();
                        return true;

                }

                return false;
            }
        });
    }
}