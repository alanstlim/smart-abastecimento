package com.example.smartabastecimento.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static FirebaseAuth firebaseAuth;
    private static DatabaseReference firebaseRef;

    // retorna a instancia do FirebaseDatabase
    public static DatabaseReference getFirebaseDatabase(){

        if (firebaseRef == null) {
            firebaseRef = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseRef;

    }

    // retorna a instancia do FireBaseAuth
    public static FirebaseAuth getFirebaseAuth(){

        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;

    }
}
