package com.example.medcontroller.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {
    private static DatabaseReference reference;

    public static DatabaseReference getReference()
    {
        if (reference == null)
        {
            reference = FirebaseDatabase.getInstance().getReference();
        }
        return reference;
    }
}
