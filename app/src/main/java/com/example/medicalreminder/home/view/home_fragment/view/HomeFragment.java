package com.example.medicalreminder.home.view.home_fragment.view;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.R;
import com.example.medicalreminder.alarm.WorkManagerClass;
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

        homePresenterInterfaceParent.loadTheMedicinesDataFromTheServer(Home.getTheCurrentUser());

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



    @Override
    public void setTheAlarm(MedicineReadyToShow medicineReadyToShow){

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dTime ;
        Date date ;
        Calendar cal = Calendar.getInstance();
        long startTime = 0;
        try {
            dTime = formatter.parse(medicineReadyToShow.getTime());
            cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
            cal.set(Calendar.MINUTE,dTime.getMinutes());
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            date = cal.getTime();
            startTime = date.getTime();
        }catch (Exception e){
            e.printStackTrace();
        }



        // set the alarm to the medicine object

        Data data = new Data.Builder()
                .putString("Medicine Name",medicineReadyToShow.getName())
                .putString("Pills ToTake",medicineReadyToShow.getPills_to_take())
                .putString("Time Per Day",medicineReadyToShow.getWhen())
                .putString("Date Of The Day",medicineReadyToShow.getDate())
                .putString("Time Of The Day",medicineReadyToShow.getTime())
                .putString("Instructions",medicineReadyToShow.getInstruction())
                .build();

        @SuppressLint("RestrictedApi") OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerClass.class)
                .setInputData(data)
                //.setInitialDelay(startTime,TimeUnit.MILLISECONDS)
                .setScheduleRequestedAt(startTime, TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(this.getContext()).enqueue(oneTimeWorkRequest);


    }


    public Date getNearestDate(List<Date> dates, Date currentDate) {
        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = Math.abs(currentTime - date.getTime());
            if ((minDiff == -1) || (diff < minDiff)) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }

    public MedicineReadyToShow getTheMedicineToAlarm(List<MedicineReadyToShow> medicineReadyToShows){


        List<Date> dates = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dTime ;
        Date date ;
        Calendar cal = Calendar.getInstance();
        Date currentTime = new Date();

        // first we need to find the nearest time from now


        for (MedicineReadyToShow i : medicineReadyToShows) {
            try {
                dTime = formatter.parse(i.getTime());
                cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
                cal.set(Calendar.MINUTE,dTime.getMinutes());
                cal.set(Calendar.SECOND,0);
                cal.set(Calendar.MILLISECOND,0);
                date = cal.getTime();
                dates.add(date);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return medicineReadyToShows.get(dates.indexOf(getNearestDate(dates,currentTime)));
    }





    @Override
    public void updateTheListOfMedicines(List<MedicineReadyToShow> medicineReadyToShows) {
        // update the list of the day
        MedicineAdapter.setMedicineReadyToShows(medicineReadyToShows);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        //System.out.println(medicines.get(0));
        medicineAdapter.notifyDataSetChanged();

        // todo -- > here we should calculate the nearest medicine to alert with. (Set The Alarm)

        setTheAlarm(getTheMedicineToAlarm(medicineReadyToShows));

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