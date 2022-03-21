package com.example.medicalreminder.home.view.home_fragment.view;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.MainActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.alarm.WorkManagerClass;
import com.example.medicalreminder.database.AppDataBase;
import com.example.medicalreminder.database.DatabaseFunctions;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.home.view.home_fragment.presnter.HomePresenter;
import com.example.medicalreminder.home.view.home_fragment.presnter.HomePresenterInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment implements HomeViewInterface {


    RecyclerView recyclerView ;
    Context context ;
    static List<MedicineReadyToShow> medicineReadyToShows;
    Communicator communicator;
    FragmentManager fragmentManager;
    MedicineAdapter medicineAdapter;


    HomePresenterInterface homePresenterInterface ;

    com.example.medicalreminder.home.presenter.HomePresenterInterface homePresenterInterfaceParent;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homePresenterInterfaceParent
                = new com.example.medicalreminder.home.presenter.HomePresenter(this);

        medicineReadyToShows = new ArrayList<>();
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        communicator = (Communicator) getActivity();
        medicineAdapter = new MedicineAdapter(context, medicineReadyToShows,communicator,fragmentManager);
        recyclerView.setAdapter(medicineAdapter);

        // load medicines for the show list
        homePresenterInterfaceParent.loadTheMedicinesDataFromTheServer(Home.getTheCurrentUser());

        // ask for updating the alarm
        homePresenterInterfaceParent.getTheListOfMedicinesForToday(Home.getTheCurrentUser().getEmail());

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);


        System.out.println(view.findViewById(R.id.calendarView).getId());
        // on below line we are setting up our horizontal calendar view and passing id our calendar view to it.
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view,R.id.calendarView)
                // on below line we are adding a range
                // as start date and end date to our calendar.
                .range(startDate, endDate)
                // on below line we are providing a number of dates
                // which will be visible on the screen at a time.
                .datesNumberOnScreen(5)
                // at last we are calling a build method
                // to build our horizontal recycler view.
                .build();
        // on below line we are setting calendar listener to our calendar view.
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                // on below line we are printing date
                System.out.println("hello date");
                System.out.println(date.getTime());

                Date dateWithNormalFormat = null;
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
                String temp = date.getTime().toString();
                try {
                    dateWithNormalFormat = formatter.parse(temp);
                    System.out.println(dateWithNormalFormat);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String formateDate = new SimpleDateFormat("dd/MM/yyyy").format(dateWithNormalFormat);
                System.out.println(formateDate);



                // todo --->>>> do some action here when the date is chosen

                homePresenterInterface.requestUpdateMedicineList(formateDate);

            }
        });


        return view ;
    }


    public Date getNearestDate(List<Date> dates, Date currentDate) {

        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = date.getTime() - currentTime ;
            if (((minDiff == -1) || (diff < minDiff)) && diff>=0) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }

    @Override
    public void manageTheAlarms(List<MedicineReadyToShow> medicineReadyToShows){

        System.out.println("******************************************************************************************************");


        // we need to find the nearest time from now

        if(!medicineReadyToShows.isEmpty()){
            List<Date> dates = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            Date dTime ;
            Date date ;
            Calendar cal = Calendar.getInstance();
            Date currentTime = new Date();

            // first we need to find the nearest time from now

            int j  = 0 ;

            for (MedicineReadyToShow i : medicineReadyToShows) {
                try {
                    System.out.println(medicineReadyToShows.get(j).getTime());
                    System.out.println(medicineReadyToShows.get(j).getDate());
                    dTime = formatter.parse(i.getTime());
                    cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
                    cal.set(Calendar.MINUTE,dTime.getMinutes());
                    cal.set(Calendar.SECOND,0);
                    cal.set(Calendar.MILLISECOND,0);
                    date = cal.getTime();
                    dates.add(date);
                    j++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            // we get the right medicine to set the alarm with
            try {
                System.out.println("OMG Date >>>>>>>>>>>>>>>>> " +dates.get(0));
                MedicineReadyToShow medicineReadyToShow =
                        medicineReadyToShows.get(dates.indexOf(getNearestDate(dates,currentTime)));

                System.out.println("The time is ------------> "+ medicineReadyToShow.getTime());

                // set the alarm to the medicine object

                Data data = new Data.Builder()
                        .putString("Medicine Name",medicineReadyToShow.getName())
                        .putString("Pills ToTake",medicineReadyToShow.getPills_to_take())
                        .putString("Time Per Day",medicineReadyToShow.getWhen())
                        .putString("Date Of The Day",medicineReadyToShow.getDate())
                        .putString("Time Of The Day",medicineReadyToShow.getTime())
                        .putString("Instructions",medicineReadyToShow.getInstruction())
                        .putString("Username",medicineReadyToShow.getUser_name())
                        .build();

                // get the time to set the alarm

                SimpleDateFormat formatterX = new SimpleDateFormat("hh:mm");
                Date dTimeX ;
                Date dateX ;
                Calendar calX = Calendar.getInstance();
                long startTime = 0;
                try {
                    dTimeX = formatterX.parse(medicineReadyToShow.getTime());
                    calX.set(Calendar.HOUR_OF_DAY,dTimeX.getHours());
                    calX.set(Calendar.MINUTE,dTimeX.getMinutes());
                    calX.set(Calendar.SECOND,0);
                    calX.set(Calendar.MILLISECOND,0);
                    dateX = calX.getTime();
                    startTime = dateX.getTime();
                    System.out.println("we are here");
                    System.out.println(startTime);
                }catch (Exception e){
                    e.printStackTrace();
                }

                // set the alarm

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerClass.class)
                        .setInitialDelay(startTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                        .setInputData(data)
                        .build();

                WorkManager.getInstance(getContext()).cancelAllWork();
                WorkManager.getInstance(getContext()).enqueue(oneTimeWorkRequest);

            }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                System.out.println("That was the last for today");
                calculateTheNextAlarm(currentTime);
            }


        }
    }

    public void calculateTheNextAlarm(Date startDate){

        // get the next date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,1);
        c.set(Calendar.MILLISECOND,0);
        startDate = c.getTime();
        System.out.println("the date became -- > "+startDate);

        // get the max date
        Date endDate = new Date();
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        endCal.add(Calendar.DATE, 30);
        endCal.set(Calendar.HOUR_OF_DAY,0);
        endCal.set(Calendar.MINUTE,0);
        endCal.set(Calendar.SECOND,1);
        endCal.set(Calendar.MILLISECOND,0);
        endDate = endCal.getTime();

        if(startDate.before(endDate)){

            Date finalStartDate = startDate;

            Calendar calY = Calendar.getInstance();
            calY.setTime(finalStartDate);
            int year = calY.get(Calendar.YEAR);
            int month = calY.get(Calendar.MONTH);
            int day = calY.get(Calendar.DAY_OF_MONTH);


            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void run() {
                    AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
                    DatabaseFunctions databaseFunctions = appDataBase.databaseFunctions();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    List<MedicineReadyToShow> medicineReadyToShows1 = databaseFunctions.getTodayMedicines(dateFormat.format(finalStartDate),Home.getTheCurrentUser().getEmail());

                    if(medicineReadyToShows1!=null){

                        List<Date> dates = new ArrayList<>();
                        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                        Date dTime ;
                        Date date ;
                        Calendar cal = Calendar.getInstance();
                        Date currentTime = new Date();

                        // first we need to find the nearest time from now

                        int j  = 0 ;



                        for (MedicineReadyToShow i : medicineReadyToShows1) {
                            try {
                                System.out.println(medicineReadyToShows1.get(j).getTime());
                                System.out.println(medicineReadyToShows1.get(j).getDate());

                                dTime = formatter.parse(i.getTime());
                                cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
                                cal.set(Calendar.MINUTE,dTime.getMinutes());
                                cal.set(Calendar.SECOND,0);
                                cal.set(Calendar.MILLISECOND,0);
                                cal.set(Calendar.YEAR,year);
                                cal.set(Calendar.MONTH,month);
                                cal.set(Calendar.DAY_OF_MONTH,day);
                                date = cal.getTime();
                                dates.add(date);
                                j++;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        try {

                            MedicineReadyToShow medicineReadyToShow =
                                    medicineReadyToShows1.get(dates.indexOf(getNearestDate(dates,currentTime)));


                            // set the alarm to the medicine object

                            Data data = new Data.Builder()
                                    .putString("Medicine Name",medicineReadyToShow.getName())
                                    .putString("Pills ToTake",medicineReadyToShow.getPills_to_take())
                                    .putString("Time Per Day",medicineReadyToShow.getWhen())
                                    .putString("Date Of The Day",medicineReadyToShow.getDate())
                                    .putString("Time Of The Day",medicineReadyToShow.getTime())
                                    .putString("Instructions",medicineReadyToShow.getInstruction())
                                    .putString("Username",medicineReadyToShow.getUser_name())
                                    .build();

                            // get the time to set the alarm

                            SimpleDateFormat formatterX = new SimpleDateFormat("hh:mm");
                            Date dTimeX ;
                            Date dateX ;
                            Calendar calX = Calendar.getInstance();
                            long startTime = 0;
                            try {
                                dTimeX = formatterX.parse(medicineReadyToShow.getTime());
                                calX.set(Calendar.HOUR_OF_DAY,dTimeX.getHours());
                                calX.set(Calendar.MINUTE,dTimeX.getMinutes());
                                calX.set(Calendar.SECOND,0);
                                calX.set(Calendar.MILLISECOND,0);
                                calX.set(Calendar.YEAR,year);
                                calX.set(Calendar.MONTH,month);
                                calX.set(Calendar.DAY_OF_MONTH,day);
                                dateX = calX.getTime();
                                startTime = dateX.getTime();
                                System.out.println("we are here");
                                System.out.println(startTime);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            // set the alarm

                            OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerClass.class)
                                    .setInitialDelay(startTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                                    .setInputData(data)
                                    .build();

                            System.out.println(startTime - System.currentTimeMillis());


                            WorkManager.getInstance(getContext()).cancelAllWork();
                            WorkManager.getInstance(getContext()).enqueue(oneTimeWorkRequest);

                            System.out.println("time is selected to be ----> ---------> "+ medicineReadyToShow.getTime());

                        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                            System.out.println("That was the last for today");
                            calculateTheNextAlarm(finalStartDate);
                        }

                    }else {
                        System.out.println("out of range !");
                    }

                }
            }).start();
        }

    }


    @Override
    public void updateTheListOfMedicines(List<MedicineReadyToShow> medicineReadyToShows) {
        // update the list of the day
        MedicineAdapter.setMedicineReadyToShows(medicineReadyToShows);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        //System.out.println(medicines.get(0));
        medicineAdapter.notifyDataSetChanged();

        // todo -- > here we should calculate the nearest medicine to alert with. (Set The Alarm)

//        setTheAlarm(getTheMedicineToAlarm(medicineReadyToShows));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void updateTheUI() {
        homePresenterInterface = new HomePresenter(this);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("--------------->>>>>" + dtf.format(now));
        homePresenterInterface.requestUpdateMedicineList(dtf.format(now));
    }


}