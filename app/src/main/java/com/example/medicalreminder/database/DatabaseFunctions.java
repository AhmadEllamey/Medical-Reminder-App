package com.example.medicalreminder.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medicalreminder.home.view.home_fragment.model.Medicine;

import java.util.List;


@Dao
public interface DatabaseFunctions {

    @Query("SELECT * FROM medicines_ready_to_view WHERE date = :dateToGet")
    List<Medicine> getCurrentDayMedicines(String dateToGet);

    @Insert
    void insertMedicine(Medicine medicine);


    @Query("DELETE FROM medicines_ready_to_view")
    void clearTheMedicines();




    /*
    new Thread(() -> {
        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();
        databaseFunctions.insertMovie(movie);
    }).start();
     */

}
