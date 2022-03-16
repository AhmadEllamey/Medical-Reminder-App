package com.example.medicalreminder.home.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.login.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.List;

import static android.content.ContentValues.TAG;


public class HomePresenter {


    public void loadTheMedicinesDataFromTheServer(User user){


        // todo -- > connect to the fire store database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // todo -- > get all the data that belong to the current user
        Task<QuerySnapshot> documentSnapshots = db.collection("Medicine Info").whereEqualTo("user_name" ,user.getEmail() ).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Log.d(TAG, document.getId() + " => " + document.getData());
                        QueryDocumentSnapshot documentx = (QueryDocumentSnapshot) document.getData();
                        Medicine medicine =documentx.toObject(Medicine.class);

                        System.out.println("---------------------- user name -------------------------");
                        System.out.println(medicine);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        // todo -- > clear the current room of medicines to store the new info



        // todo -- > load the current data to the room of medicines



        // todo -- > call the function that load the room of ready to show data
    }
    

    public void loadTheReadyToShowData(){

        // todo -- > clear the data of the ready to show data


        // todo -- > calculate the new data info and load them into ready to show room


        // todo -- > notify the changed data to the adapter


    }










}
