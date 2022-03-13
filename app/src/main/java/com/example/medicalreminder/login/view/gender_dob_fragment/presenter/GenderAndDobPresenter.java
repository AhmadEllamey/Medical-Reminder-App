package com.example.medicalreminder.login.view.gender_dob_fragment.presenter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.medicalreminder.login.model.User;
import com.example.medicalreminder.login.view.gender_dob_fragment.view.GenderAndDobViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class GenderAndDobPresenter implements GenderAndDobPresenterInterface {



    GenderAndDobViewInterface genderAndDobViewInterface ;
    View view ;

    public GenderAndDobPresenter(GenderAndDobViewInterface genderAndDobViewInterface, View view) {
        this.genderAndDobViewInterface = genderAndDobViewInterface;
        this.view = view;
    }


    @Override
    public void createUser(User user) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getEmail()).set(user, SetOptions.merge()).addOnSuccessListener(aVoid -> {
            //Log.d(TAG, "DocumentSnapshot successfully written!");
            genderAndDobViewInterface.showToast("Done");
            genderAndDobViewInterface.goToLoginPage(view);
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Log.w(TAG, "Error writing document", e);
                genderAndDobViewInterface.showToast("Something went wrong");
            }
        });

    }
}
