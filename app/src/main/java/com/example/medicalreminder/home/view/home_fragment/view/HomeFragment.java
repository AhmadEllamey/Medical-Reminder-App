package com.example.medicalreminder.home.view.home_fragment.view;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.R;
import com.example.medicalreminder.home.view.home_fragment.model.Medicine;
import com.example.medicalreminder.home.view.home_fragment.presnter.HomePresenter;
import com.example.medicalreminder.home.view.home_fragment.presnter.HomePresenterInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface {


    RecyclerView recyclerView ;
    Context context ;
    static List<Medicine> medicines ;
    Communicator communicator;
    FragmentManager fragmentManager;
    MedicineAdapter medicineAdapter;


    HomePresenterInterface homePresenterInterface ;

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


        medicines = new ArrayList<>();
        context = view.getContext();
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        communicator = (Communicator) getActivity();
        medicineAdapter = new MedicineAdapter(context,medicines,communicator,fragmentManager);
        recyclerView.setAdapter(medicineAdapter);

        homePresenterInterface = new HomePresenter(this);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("--------------->>>>>" + dtf.format(now));

        homePresenterInterface.requestUpdateMedicineList(dtf.format(now));










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

                String formateDate = new SimpleDateFormat("MM/dd/yyyy").format(dateWithNormalFormat);
                System.out.println(formateDate);



                // todo --->>>> do some action here when the date is chosen

                homePresenterInterface.requestUpdateMedicineList(formateDate);





            }
        });





        return view ;
    }


    @Override
    public void updateTheListOfMedicines(List<Medicine> medicines) {
        MedicineAdapter.setMedicines(medicines);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        //System.out.println(medicines.get(0));
        medicineAdapter.notifyDataSetChanged();
    }


}