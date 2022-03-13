package com.example.medicalreminder.login.view.name_email_phone_fragment.presenter;

import android.view.View;


import com.example.medicalreminder.login.view.name_email_phone_fragment.view.AskingForNameEmailPhoneViewInterface;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AskingForNameEmailPhonePresenter implements AskingForNameEmailPhonePresenterInterface {



    AskingForNameEmailPhoneViewInterface askingForNameEmailPhoneViewInterface ;
    View view ;

    public AskingForNameEmailPhonePresenter(AskingForNameEmailPhoneViewInterface askingForNameEmailPhoneViewInterface, View view) {
        this.askingForNameEmailPhoneViewInterface = askingForNameEmailPhoneViewInterface;
        this.view = view;
    }


    @Override
    public void checkIfTheUserExists(String email, String name, String phone) {


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(email);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    askingForNameEmailPhoneViewInterface.showToast("E-mail already exists !");
                } else {
                    // go to the next screen with the current data .
                    askingForNameEmailPhoneViewInterface.goToTheNextPage(view,name,email,phone);
                }
            } else {
                // todo print something went wrong
                askingForNameEmailPhoneViewInterface.showToast("something went wrong");
            }
        });

    }




}
