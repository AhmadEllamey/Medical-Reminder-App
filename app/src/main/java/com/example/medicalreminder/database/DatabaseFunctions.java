package com.example.medicalreminder.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medicalreminder.Model.Local_Medicine_DB;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;

import java.util.List;


@Dao
public interface DatabaseFunctions {

    @Query("SELECT * FROM medicines_ready_to_view WHERE date = :dateToGet")
    List<MedicineReadyToShow> getCurrentDayMedicines(String dateToGet);

    @Insert
    void insertMedicine(MedicineReadyToShow medicineReadyToShow);

    //ADDLOCAL
    @Insert
    void insertLocalMedicine(Local_Medicine_DB local_medicine_db);
    //Delete
//    @Query("DELETE FROM LocalMedicine WHERE user_name :")
//    void DeleteLocalMedicine(Local_Medicine_DB local_medicine_db);



    @Query("DELETE FROM medicines_ready_to_view WhERE user_name = :userName")
    void clearTheMedicines(String userName);


    // create the function that deletes all medicines for a specific user name
    @Query("DELETE FROM MedicineInfo WHERE user_name = :userName ")
    void clearTheMedicinesInfo(String userName);

    @Insert
    void insertMedicines(Medicine medicine);



    /*
    new Thread(() -> {
        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();
        databaseFunctions.insertMovie(movie);
    }).start();
     */


//hend...............................................................

    @Query("Select * from MedicineInfo")
    List<Medicine> getAllMedications();

    @Query("SELECT * FROM MedicineInfo WHERE  active=1")
    List<Medicine> getActiveMedications();

    @Query("SELECT * FROM MedicineInfo WHERE  active=0")
    List<Medicine> getInactiveMedications();


}
