package com.example.smartabastecimento.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartabastecimento.R;
import com.example.smartabastecimento.helper.Base64Custom;
import com.example.smartabastecimento.helper.ConfigFirebase;
import com.example.smartabastecimento.helper.UserFirebase;
import com.example.smartabastecimento.model.UserApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {

    private EditText        editRegisterPassword, editRegisterEmail, editRegisterName;
    private Button          buttonRegister;
    private FirebaseAuth    dbUser;
    private UserApp         userApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editRegisterEmail =     findViewById(R.id.editRegisterEmail);
        editRegisterName =      findViewById(R.id.editRegisterName);
        editRegisterPassword =  findViewById(R.id.editRegisterPassword);
        buttonRegister =        findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textName     = editRegisterName.getText().toString();
                String textEmail    = editRegisterEmail.getText().toString();
                String textPassword = editRegisterPassword.getText().toString();

                if (!textName.isEmpty()){
                    if (!textEmail.isEmpty()){
                        if (!textPassword.isEmpty()){

                            userApp = new UserApp();
                            userApp.setName( textName );
                            userApp.setEmail( textEmail );
                            userApp.setPassword( textPassword );
                            registerUser();

                        } else {
                            Toast.makeText(RegisterActivity.this, "Preencha o campo Senha.", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Preencha o campo Email.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "Preencha o campo Nome.", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void registerUser(){

        dbUser = ConfigFirebase.getFirebaseAuth();
        dbUser.createUserWithEmailAndPassword(editRegisterEmail.getText().toString(), editRegisterPassword.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Salvar dados no Firebase
                            String idUser = Base64Custom.encodeBase64(userApp.getEmail());
                            userApp.setUserId( idUser );
                            userApp.SaveUser();

                            //Salvar dados no profile do Firebase
                            UserFirebase.reloadNameUser( userApp.getName());

                            Toast.makeText(RegisterActivity.this, "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {

                            String exception = "";
                            try {
                                throw task.getException();
                            } catch ( FirebaseAuthWeakPasswordException e) {
                                exception = "Digite uma senha mais forte!";
                            } catch ( FirebaseAuthInvalidCredentialsException e) {
                                exception = "Por favor, digite um email válido";
                            } catch ( FirebaseAuthUserCollisionException e){
                                exception = "Email já cadastrado!";
                            } catch (Exception e) {
                                exception = "Erro ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText(RegisterActivity.this, exception, Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}

