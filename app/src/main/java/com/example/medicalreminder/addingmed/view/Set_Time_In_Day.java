package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.Presenter.PresenterInterface;
import com.example.medicalreminder.R;

import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;


public class Set_Time_In_Day extends Fragment {

    Context context = getContext();

    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    PresenterInterface presenterInterface;
    int counter = 0;
    Medicine medicine;

    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.set_time, container, false);
        timePicker1 = view.findViewById(R.id.datepicker);
//        timePicker1.setIs24HourView(true);

        view.findViewById(R.id.save).setOnClickListener(this::save);
        // name form strenght takenfor iseveryday howoften counter  timewithinday

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            medicine = (Medicine) bundle.getSerializable("obj");
            counter = bundle.getInt("counter");
        }

        return view;
    }

    private void save(View view) {

        String daytime = timePicker1.getHour() + ":" + timePicker1.getMinute();
        NavController navController;
        navController = Navigation.findNavController(view);

        if (medicine.isIs_Every_Day()) {  // everyday
            System.out.println(" before counter !=0 test 3 " + counter);
            if (counter != 0) {
                if (medicine.getMorning() != null && medicine.getHour_of_Morning() == null) {
                    medicine.setHour_of_Morning(daytime);
                    daytime = "";
                } else if (medicine.getEvening() != null && medicine.getHour_of_Evening() == null) {
                    medicine.setHour_of_Evening(daytime);

                } else if (medicine.getNight() != null && medicine.getHour_of_Night() == null) {
                    medicine.setHour_of_Night(daytime);


                } else if (medicine.getNoon() != null && medicine.getHour_of_Noon() == null) {
                    medicine.setHour_of_Noon(daytime);

                }

                counter = counter - 1;

                Bundle Sendbundle = new Bundle();
                Sendbundle.putInt("count", counter);
                Sendbundle.putSerializable("obj", medicine);
                NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToTimeInDayFragment();
                navController.navigate(R.id.Time_In_Day_fragment, Sendbundle);
            }
            //once a day
            if (counter == 0) {
                if (medicine.getMed_form().equals("Pill")) {

                    //if once and pill
                    Bundle Sendbundle = new Bundle();
                    Sendbundle.putInt("count", counter);
                    Sendbundle.putSerializable("obj", medicine);
                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToPlaceholder();
                    navController.navigate(R.id.Med_amount_fragment, Sendbundle);
                }
                else {
            //not pills everyday
                    //reduce the counter
                    //if once and not a pill
                    Bundle Sendbundle = new Bundle();
                    Sendbundle.putInt("count", counter); //if twice
                    Sendbundle.putSerializable("obj", medicine);
                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToInstructions2();
                    navController.navigate(R.id.instructions2, Sendbundle);
                }
            }
        }
        else {  //specific or period of time or every 2 days
            if (medicine.getMorning() != null && medicine.getHour_of_Morning() == null) {
                medicine.setHour_of_Morning(daytime);
                daytime = "";
            } else if (medicine.getEvening() != null && medicine.getHour_of_Evening() == null) {
                medicine.setHour_of_Evening(daytime);

            } else if (medicine.getNight() != null && medicine.getHour_of_Night() == null) {
                medicine.setHour_of_Night(daytime);


            } else if (medicine.getNoon() != null && medicine.getHour_of_Noon() == null) {
                medicine.setHour_of_Noon(daytime);}

            //reduce the counter
            //if once and not a pill
            Bundle Sendbundle = new Bundle();
            Sendbundle.putInt("count", counter); //if twice
            Sendbundle.putSerializable("obj", medicine);
            NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToInstructions2();
            navController.navigate(R.id.instructions2, Sendbundle);
        }
    } //function save
} //class



