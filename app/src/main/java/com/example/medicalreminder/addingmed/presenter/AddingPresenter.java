package com.example.medicalreminder.addingmed.presenter;

import androidx.annotation.NonNull;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.addingmed.view.ViewInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddingPresenter implements AddingPresenterInterface {
    ViewInterface viewInterface;


    public AddingPresenter(ViewInterface viewInterface) {
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
                    viewInterface.show_Med(); //no implementaion

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //internet not exist
                saveLocal(medicine);
                System.out.println("passed");
                viewInterface.show_faild(" faild ");

            }
        });
    }

//
//    // todo -- > connect to the fire store database
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    // todo -- > get all the data that belong to the current user
//    Task<QuerySnapshot> documentSnapshots = db.collection("Medicine Info")
//            .whereEqualTo("user_name" , user.getEmail())
//            .get()
//            .addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//
//                    medicines = new ArrayList<>();
//                    // todo -- > clear the current room of medicines to store the new info
//
//                    Repo repo = new Repo(this);
//                    repo.clearAllMedicines(user.getEmail());
//
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        //Log.d(TAG, document.getId() + " => " + document.getData());
//                        Medicine medicine =document.toObject(Medicine.class);
//                        medicines.add(medicine);
//                    }
//                } else {
//                    // todo -- > show toast that the internet is lost
//                    Toast.makeText(Home.getContext(),"Connection Lost",Toast.LENGTH_LONG).show();
//                    homeViewInterface.updateTheUI();
//                }
//            });

    private void saveLocal(Medicine medicine) {
        //DAO
        //insert
        //database absract db extends roomDB

//        Local_Medicine_DB local_medicine_db = new Local_Medicine_DB(medicine);
//        System .out.println("enetered");
//        RepositoryAdding repositoryAdding = new RepositoryAdding(this);
//        repositoryAdding.insert(local_medicine_db);
//        Repo repo= new Repo();
//        repo.in
    }


    @Override
    public void show() {

    }
//functioncs bt7ads el view


}
