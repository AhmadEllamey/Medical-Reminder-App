package com.example.medicalreminder.addingmed.view;

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

        String date= day+"-"+month+"-"+year;

        medicine.setStart_date(date);
        if(intervaloftime==28){
            Generate_End_date generate_end_date=new Generate_End_date(day,month, year ,28);
            medicine.setEnd_date(Generate_End_date.End_date);
        }

        Bundle Sendbundle= new Bundle();
        Sendbundle.putSerializable("obj",medicine);

        NavController navController;
        navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.myhealth.addingmed.view.Set_Start_DateDirections.actionSetStartDateToTimeInDayFragment();
        navController.navigate(R.id.Time_In_Day_fragment,Sendbundle);
        Toast.makeText(getContext(), Generate_End_date.End_date, Toast.LENGTH_SHORT).show();

        System.out.println(
                        medicine.getMed_name()+"\n"+
                        medicine.getMed_form()+"\n"+
                        medicine.getStrength()+"\n"+
                        medicine.getHow_often()+"\n"+
                        medicine.getIs_Every_Day()+"\n"+
                        medicine.getWhy_Taken()+"\n"+
                        medicine.getMed_amount()+"\n"+
                        medicine.getMed_left()+"\n"+

                        medicine.getMorning()+"\n"+
                        medicine.getHour_of_Morning()+"\n"+

                        medicine.getEvening()+"\n"+
                        medicine.getHour_of_Evening()+"\n"+

                        medicine.getNoon()+"\n"+
                        medicine.getHour_of_Noon()+"\n"+

                        medicine.getNight()+"\n"+
                        medicine.getHour_of_Night()+"\n"+

                        medicine.getStart_date()+"\n"+
                        medicine.getEnd_date()+"\n"+

                        medicine.isFriday()+"\n"+
                        medicine.isSaturday()+"\n"+
                        medicine.isSunday()+"\n"+
                        medicine.isMonday()+"\n"+
                        medicine.isTuesday()+"\n"+
                        medicine.isWedensday()+"\n"+
                        medicine.isThursday()+"\n"
                );

    }
}
