package com.example.medicalreminder.addingmed.presenter;

import androidx.annotation.NonNull;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.addingmed.view.ViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Presenter implements PresenterInterface {
    ViewInterface viewInterface;


    public Presenter(ViewInterface viewInterface) {
        this.viewInterface=viewInterface;
    }

    @Override
    public void delete() {

    }

    @Override
    public void insert(Medicine medicine) {
        //fire_store
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Medicine Info").document(medicine.getUser_name()+"-"+medicine.getMed_name())
                .set(medicine).addOnSuccessListener(aVoid -> {
                    //internet exist
                    viewInterface.show_Med();
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //internet not exist
                saveLocal(medicine);
                viewInterface.show_faild(" faild ");

            }
        });
    }

    private void saveLocal(Medicine medicine) {
        //DAO
        //insert
        //database absract db extends roomDB
    }


    @Override
    public void show() {

    }
//functioncs bt7ads el view


}
