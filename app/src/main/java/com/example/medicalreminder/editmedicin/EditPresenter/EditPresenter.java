package com.example.medicalreminder.editmedicin.EditPresenter;

import androidx.annotation.NonNull;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.editmedicin.EditView.EditViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditPresenter implements EditPresenterInterface{

    EditViewInterface editViewInterface;

    public EditPresenter(EditViewInterface editViewInterface) {
        this.editViewInterface = editViewInterface;
    }

    //updating to the fire_store

    @Override
    public void update(Medicine medicine) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Medicine Info").document(medicine.getUser_name()+"-"+medicine.getMed_name())
                .set(medicine).addOnSuccessListener(aVoid -> {
            //internet exist
            editViewInterface.ShowToast();

        }).addOnFailureListener(new OnFailureListener() {
            @Override

            public void onFailure(@NonNull Exception e) {
                //internet not exist
                //update the Medicine Info (AHMED)

            }
        });

    }



}
