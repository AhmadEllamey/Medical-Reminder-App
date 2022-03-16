package com.example.medicalreminder.database;

import android.os.Handler;
import android.os.Message;

import com.example.medicalreminder.MainActivity;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.home.view.home_fragment.presnter.HomePresenterInterface;
import java.util.List;

public class Repo {



    List<MedicineReadyToShow> medicineReadyToShows;
    DatabaseFunctions databaseFunctions;


    HomePresenterInterface homePresenterInterface ;

    com.example.medicalreminder.home.presenter.HomePresenterInterface homePresenter ;

    public Repo(HomePresenterInterface homePresenterInterface) {
        this.homePresenterInterface = homePresenterInterface;
    }

    public Repo(com.example.medicalreminder.home.presenter.HomePresenterInterface homePresenter) {
        this.homePresenter = homePresenter;
    }

    public Repo() {
    }

    // home operations

    public void clearAllMedicines(String user_name){

        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();

        Handler handler =  new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //  Do SomeThings
                homePresenter.loadMedicineInfo();

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                //databaseFunctions.insertMedicine(new Medicine("panadol","06:00","03/13/2022","1","Active","Waiting"));
                databaseFunctions.clearTheMedicinesInfo(user_name);
                handler.sendEmptyMessage(1);
            }
        }).start();


    }

    public void insertMedicineInfo(List<Medicine> medicines){

        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();

        Handler handler =  new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //  Do SomeThings
                homePresenter.clearTheReadyToShowData();

            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                // Using enhanced for loop(for-each) for iteration
                for (Medicine i : medicines) {
                    databaseFunctions.insertMedicines(i);
                }
                handler.sendEmptyMessage(1);
            }
        }).start();


    }

    public void clearReadyToShowForTheCurrentUser(String userName){

        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();

        Handler handler =  new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                //  Do SomeThings
                homePresenter.loadMedicineToShow();

            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {
                // Using enhanced for loop(for-each) for iteration
                databaseFunctions.clearTheMedicines(userName);
                handler.sendEmptyMessage(1);
            }
        }).start();




    }

    public void insertTheRecordToShow(MedicineReadyToShow medicineReadyToShow){
        AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
        databaseFunctions = appDataBase.databaseFunctions();
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseFunctions.insertMedicine(medicineReadyToShow);
            }
        }).start();
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
                homePresenterInterface.getTheMovies(medicineReadyToShows);

            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                //databaseFunctions.insertMedicine(new Medicine("panadol","06:00","03/13/2022","1","Active","Waiting"));
                System.out.println("we are here !!!!!!");
                medicineReadyToShows = databaseFunctions.getCurrentDayMedicines(date);
                handler.sendEmptyMessage(1);
            }
        }).start();



    }




}
