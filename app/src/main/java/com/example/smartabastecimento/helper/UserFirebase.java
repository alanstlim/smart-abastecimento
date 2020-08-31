package com.example.smartabastecimento.helper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserFirebase {

    public static FirebaseUser getCurrentUser (){
        FirebaseAuth dbUser = ConfigFirebase.getFirebaseAuth();
        return dbUser.getCurrentUser();
    }

    public static void reloadNameUser(String name) {

        try{
            //Usuario Logado
            FirebaseUser currentUser = getCurrentUser();

            //Configurar objeto pra alteração do perfil
            UserProfileChangeRequest profile = new UserProfileChangeRequest
                    .Builder()
                    .setDisplayName(name)
                    .build();

            currentUser.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(!task.isSuccessful()){

                        Log.d("Perfil", "Erro ao atualizar o nome de perfil");

                    }

                }
            });



        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
