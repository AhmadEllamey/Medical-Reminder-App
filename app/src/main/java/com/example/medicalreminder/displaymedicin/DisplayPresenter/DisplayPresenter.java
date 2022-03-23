package com.example.medicalreminder.displaymedicin.DisplayPresenter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.displaymedicin.DisplayView.DisplayInterface;
import com.example.medicalreminder.editmedicin.EditView.EditViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DisplayPresenter implements DisplayPresenterInterface {

    DisplayInterface displayInterface;

    public DisplayPresenter(DisplayInterface displayInterface) {
        this.displayInterface = displayInterface;
    }



    //updating to the fire_store

    @Override
    public void update(Medicine medicine) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Medicine Info").document(medicine.getUser_name()+"-"+medicine.getMed_name())
                .set(medicine).addOnSuccessListener(aVoid -> {
            //internet exist
            displayInterface.ShowToast();

        }).addOnFailureListener(new OnFailureListener() {
            @Override

            public void onFailure(@NonNull Exception e) {
                //internet not exist
                //update the Medicine Info (AHMED)

            }
        });

    }

    @Override
    public void delete(Medicine medicine) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference deletedmed = db.collection("Medicine Info").document(medicine.getUser_name()+"-"+medicine.getMed_name());
        deletedmed.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    displayInterface.deletesuccess();

                    // update the database and delete it from the home
                }
                else{
                    displayInterface.deletefailed();
                }

            }
        });

    }

    @Override
    public void SendRequest(Medicine medicine) {
        Repo repo = new Repo(displayInterface);
        repo.getMedicineFor(medicine.getMed_name(),medicine.getUser_name());
    }


}
