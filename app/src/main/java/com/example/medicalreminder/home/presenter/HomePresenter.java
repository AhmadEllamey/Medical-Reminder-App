package com.example.medicalreminder.home.presenter;


import android.os.Build;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.home.view.home_fragment.view.HomeViewInterface;
import com.example.medicalreminder.login.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HomePresenter implements HomePresenterInterface {

    List<Medicine> medicines ;

    HomeViewInterface homeViewInterface ;

    public HomePresenter(HomeViewInterface homeViewInterface) {
        this.homeViewInterface = homeViewInterface;
    }

    // load the data from the internet
    @Override
    public void loadTheMedicinesDataFromTheServer(User user){
        // todo -- > connect to the fire store database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // todo -- > get all the data that belong to the current user
        Task<QuerySnapshot> documentSnapshots = db.collection("Medicine Info")
                .whereEqualTo("user_name" , user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        medicines = new ArrayList<>();
                        // todo -- > clear the current room of medicines to store the new info

                        Repo repo = new Repo(this);
                        repo.clearAllMedicines(user.getEmail());

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            Medicine medicine =document.toObject(Medicine.class);
                            medicines.add(medicine);
                        }
                    } else {
                        // todo -- > show toast that the internet is lost
                        Toast.makeText(Home.getContext(),"Connection Lost",Toast.LENGTH_LONG).show();
                        homeViewInterface.updateTheUI();
                    }
                });
    }

    // load the data into app room
    @Override
    public void loadMedicineInfo() {
        Repo repo = new Repo(this);
        repo.insertMedicineInfo(medicines);
    }
    

    @Override
    public void clearTheReadyToShowData(){
        // todo -- > clear the data of the ready to show data
        Repo repo = new Repo(this);
        repo.clearReadyToShowForTheCurrentUser(Home.getTheCurrentUser().getEmail());
    }



    // process the data to set the medicines that will be available
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void loadMedicineToShow() {

        // todo -- > calculate the new data info and load them into ready to show room

        // date before one month from now
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date start = cal.getTime();

        // date after one month from now
        Calendar calForEnd = Calendar.getInstance();
        calForEnd.add(Calendar.MONTH, 1);
        Date end = calForEnd.getTime();

        System.out.println("------------------------we entered here ------------------------");


        for (Medicine i : medicines) {
             // find the info to show and save them into the table of ready to show

            System.out.println("------------------------we entered here ------------------------");
            System.out.println(i.getFlag());
            try {
                Date startDateForTheMed = new SimpleDateFormat("dd/MM/yyyy").parse(i.getStart_date());
                Date lastTimeForTheMed = new SimpleDateFormat("dd/MM/yyyy").parse(i.getLast_time_taken());
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date actualStartDate ;


                System.out.println(start);
                System.out.println(end);
                System.out.println(startDateForTheMed);

                if(startDateForTheMed.before(end)){

                    if(startDateForTheMed.after(start) || startDateForTheMed.equals(start)){
                        // start date will be startDateForTheMed
                        actualStartDate = startDateForTheMed ;
                    }else {
                        if(lastTimeForTheMed.after(start) || lastTimeForTheMed.equals(start)){
                            // start date will be the last time taken
                            actualStartDate = lastTimeForTheMed ;
                        }else {
                            // start date will be the start
                            actualStartDate = start ;
                        }

                    }
                    // by that we have the start date and the end date

                    // perform switch to do what's required

                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(actualStartDate);

                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(end);



                    switch (i.getFlag()){

                        // todo -- > load the current data to the room of medicines

                        case "Everyday" :
                            while(!cal1.after(cal2)) {

                                if(i.getHour_of_Morning()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Morning()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if (i.getHour_of_Noon()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Noon()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");

                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if(i.getHour_of_Evening()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Evening()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if(i.getHour_of_Night()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Night()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                cal1.add(Calendar.DATE, 1);
                            }
                            break;

                        case "Every_two_days" :

                            while(!cal1.after(cal2)) {

                                if(i.getHour_of_Morning()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Morning()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if (i.getHour_of_Noon()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Noon()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");

                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if(i.getHour_of_Evening()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Evening()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if(i.getHour_of_Night()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Night()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                cal1.add(Calendar.DATE, 2);
                            }
                            break;

                        case "Specific_Days" :

                            // get all days
                            List<String> days = new ArrayList<>();
                            if(i.isSaturday()){days.add("Saturday");}
                            if(i.isSunday()){days.add("Sunday");}
                            if(i.isMonday()){days.add("Monday");}
                            if(i.isTuesday()){days.add("Tuesday");}
                            if(i.isWedensday()){days.add("Wednesday");}
                            if(i.isThursday()){days.add("Thursday");}
                            if(i.isFriday()){days.add("Friday");}

                            Format f = new SimpleDateFormat("EEEE");
                            String str ;
                            while(!cal1.after(cal2)) {
                                str = f.format(cal1.getTime());
                                if(days.contains(str)){

                                    if(i.getHour_of_Morning()!=null) {
                                        MedicineReadyToShow medicineReadyToShow =
                                                new MedicineReadyToShow(i.getMed_name()
                                                        , i.getHour_of_Morning()
                                                        , dateFormat.format(cal1.getTime())
                                                        , i.getUser_name()
                                                        , i.getS_Unit()
                                                        , i.getActive().toString()
                                                        , "waiting");
                                        // todo save this record
                                        saveTheRecord(medicineReadyToShow);
                                    }

                                    if (i.getHour_of_Noon()!=null) {
                                        MedicineReadyToShow medicineReadyToShow =
                                                new MedicineReadyToShow(i.getMed_name()
                                                        , i.getHour_of_Noon()
                                                        , dateFormat.format(cal1.getTime())
                                                        , i.getUser_name()
                                                        , i.getS_Unit()
                                                        , i.getActive().toString()
                                                        , "waiting");

                                        // todo save this record
                                        saveTheRecord(medicineReadyToShow);
                                    }

                                    if(i.getHour_of_Evening()!=null) {
                                        MedicineReadyToShow medicineReadyToShow =
                                                new MedicineReadyToShow(i.getMed_name()
                                                        , i.getHour_of_Evening()
                                                        , dateFormat.format(cal1.getTime())
                                                        , i.getUser_name()
                                                        , i.getS_Unit()
                                                        , i.getActive().toString()
                                                        , "waiting");
                                        // todo save this record
                                        saveTheRecord(medicineReadyToShow);
                                    }

                                    if(i.getHour_of_Night()!=null) {
                                        MedicineReadyToShow medicineReadyToShow =
                                                new MedicineReadyToShow(i.getMed_name()
                                                        , i.getHour_of_Night()
                                                        , dateFormat.format(cal1.getTime())
                                                        , i.getUser_name()
                                                        , i.getS_Unit()
                                                        , i.getActive().toString()
                                                        , "waiting");
                                        // todo save this record
                                        saveTheRecord(medicineReadyToShow);
                                    }
                                }

                                cal1.add(Calendar.DATE, 1);
                            }
                            break;

                        case "period_of_days" :

                            Date endDateForThisMed = new SimpleDateFormat("dd/MM/yyyy").parse(i.getEnd_date());
                            while(!cal1.after(endDateForThisMed)) {

                                if(i.getHour_of_Morning()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Morning()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if (i.getHour_of_Noon()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Noon()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");

                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if(i.getHour_of_Evening()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Evening()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                if(i.getHour_of_Night()!=null) {
                                    MedicineReadyToShow medicineReadyToShow =
                                            new MedicineReadyToShow(i.getMed_name()
                                                    , i.getHour_of_Night()
                                                    , dateFormat.format(cal1.getTime())
                                                    , i.getUser_name()
                                                    , i.getS_Unit()
                                                    , i.getActive().toString()
                                                    , "waiting");
                                    // todo save this record
                                    saveTheRecord(medicineReadyToShow);
                                }

                                cal1.add(Calendar.DATE, 1);
                            }
                            break;

                    }



                }


            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


        // todo -- > notify the changed data to the adapter


        homeViewInterface.updateTheUI();
    }


    public void saveTheRecord(MedicineReadyToShow medicineReadyToShow){
        Repo repo = new Repo();
        System.out.println(medicineReadyToShow.getName());
        System.out.println(medicineReadyToShow.getUser_name());
        System.out.println(medicineReadyToShow.getTime());
        repo.insertTheRecordToShow(medicineReadyToShow);
    }


}
