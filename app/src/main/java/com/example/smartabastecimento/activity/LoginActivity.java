package com.example.smartabastecimento.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartabastecimento.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    
    private EditText        editPassword, editEmail;
    private Button          buttonLogin;
    private TextView        textRegister;
    private FirebaseAuth    dbUser = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textRegister =  findViewById(R.id.textRegister);
        editEmail =     findViewById(R.id.editLoginEmail);
        editPassword =  findViewById(R.id.editLoginPassword);
        buttonLogin =   findViewById(R.id.buttonLogin);
        
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logar usuario
                dbUser.signInWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    //Ação ao logar com sucesso
                                    Intent intentLogin = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intentLogin);
                                    finish();
                                } else {
                                    //ação se der erro
                                    Toast.makeText(LoginActivity.this, "Erro ao fazer login, tente novamente.", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);

            }
        });


    }
}