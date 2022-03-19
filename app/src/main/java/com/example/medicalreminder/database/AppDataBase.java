package com.example.medicalreminder.database;

import android.content.Context;
//
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medicalreminder.Model.Local_Medicine_DB;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;


@Database(entities = {MedicineReadyToShow.class, com.example.medicalreminder.Model.Medicine.class, Local_Medicine_DB.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public abstract DatabaseFunctions databaseFunctions();

    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "medicines").fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
