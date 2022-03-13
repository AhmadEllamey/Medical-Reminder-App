package com.example.medicalreminder.database;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.medicalreminder.MainActivity;
import com.example.medicalreminder.home.view.home_fragment.model.Medicine;
import com.example.medicalreminder.home.view.home_fragment.presnter.HomePresenterInterface;
import java.util.List;

public class Repo {



    List<Medicine> medicines ;
    DatabaseFunctions databaseFunctions;


    HomePresenterInterface homePresenterInterface ;

    public Repo(HomePresenterInterface homePresenterInterface) {
        this.homePresenterInterface = homePresenterInterface;




    }




    public void getAllMedicines(String date){

        System.out.println("we are here again");

        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();

        Handler handler =  new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //  Do SomeThings
                homePresenterInterface.getTheMovies(medicines);

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                //databaseFunctions.insertMedicine(new Medicine("panadol","06:00","03/13/2022","1","Active","Waiting"));
                System.out.println("we are here !!!!!!");
                medicines = databaseFunctions.getCurrentDayMedicines(date);
                handler.sendEmptyMessage(1);
            }
        }).start();







    }




}
