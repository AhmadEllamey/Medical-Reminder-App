package com.example.medicalreminder.login.view.forgot_password_fragment.presenter;

import android.view.View;

import com.example.medicalreminder.login.model.User;
import com.example.medicalreminder.login.view.forgot_password_fragment.utillity.GMailSender;
import com.example.medicalreminder.login.view.forgot_password_fragment.view.PasswordFragmentInterface;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PasswordFragmentPresenter implements PasswordFragmentPresenterInterface {


    PasswordFragmentInterface passwordFragmentInterface ;
    View view ;


    public PasswordFragmentPresenter(PasswordFragmentInterface passwordFragmentInterface,View view) {
        this.passwordFragmentInterface = passwordFragmentInterface;
        this.view = view ;
    }


    @Override
    public void sendMailToUserWithHisPassword(String mail) {

        // send the mail with password


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(mail);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String password = document.get("password").toString();
                    GMailSender gMailSender = new GMailSender("mreminder53@gmail.com","ASDFasdf");
                    try {
                        gMailSender.sendMail("your password !","your password is : "+password ,"mreminder53@gmail.com" ,mail);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });





        // finally go back to login screen
        passwordFragmentInterface.goBackToLoginScreen(view);
    }
}
