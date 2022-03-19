package com.example.medicalreminder.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class AppDataBaseAdding extends RoomDatabase {


    private static AppDataBaseAdding instance = null;

    //abstract method return sth from DAO (hoa eli hi3ml el implementation)
    public abstract DatabaseFunctions databaseFunctions();

    // one thread at a time to access this method
    public  static  synchronized AppDataBaseAdding getInstance(Context context){
        if(instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(), AppDataBaseAdding.class,"LocalMedicine").build();
        }
        return instance;
    }
}
