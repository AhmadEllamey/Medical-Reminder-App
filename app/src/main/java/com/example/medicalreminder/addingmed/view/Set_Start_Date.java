package com.example.medicalreminder.addingmed.view;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Set_Start_Date extends Fragment {
    DatePicker datePicker;
    Medicine medicine;
    int intervaloftime=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.set_start_date,container,false);
        datePicker = (DatePicker) view.findViewById(R.id.datepicker);
        Bundle bundle=this.getArguments();
        medicine= (Medicine) bundle.getSerializable("obj");
        intervaloftime=bundle.getInt("intervaloftime");
        view.findViewById(R.id.savebtn).setOnClickListener(this::save);
        return  view;
    }

    private void save(View view) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String date;

        if (month<=9)  date= day+"/0"+month+"/"+year;
        else{
            date=day+"/"+month+"/"+year;
        }

        medicine.setStart_date(date);
        medicine.setLast_time_taken(date);

        if(intervaloftime==28){
            Generate_End_date generate_end_date=new Generate_End_date(day,month, year ,28);
            medicine.setEnd_date(Generate_End_date.End_date);
        }

        Bundle Sendbundle= new Bundle();
        Sendbundle.putSerializable("obj",medicine);

        NavController navController;
        navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Start_DateDirections.actionSetStartDateToTimeInDayFragment();
        navController.navigate(R.id.Time_In_Day_fragment,Sendbundle);
        Toast.makeText(getContext(), Generate_End_date.End_date, Toast.LENGTH_SHORT).show();

    }
}
