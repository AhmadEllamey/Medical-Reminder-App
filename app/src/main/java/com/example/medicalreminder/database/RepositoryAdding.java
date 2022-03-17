package com.example.medicalreminder.database;

import com.example.medicalreminder.MainActivity;
import com.example.medicalreminder.Model.Local_Medicine_DB;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.addingmed.presenter.AddingPresenterInterface;

public class RepositoryAdding {

    DatabaseFunctions databaseFunctions;
    AddingPresenterInterface addingPresenterInterface;

    public RepositoryAdding(AddingPresenterInterface addingPresenterInterface) {
        this.addingPresenterInterface = addingPresenterInterface;
    }

    void InsertMedLocal(Medicine medicine){
        AppDataBaseAdding appDataBaseAdding = AppDataBaseAdding.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBaseAdding.databaseFunctions();
    }

    public void  insert(Local_Medicine_DB local_medicine_db){
        System.out.println("insert");
        databaseFunctions.insertLocalMedicine( local_medicine_db );
        System.out.println("end");

    }

}
