package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;

public class TIme_within_day extends Fragment {
    private static final String TAG ="tag" ;
    Medicine medicine;
    int counter=0;
    TextView dose;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.what_time_in_day,container,false);


        dose=view.findViewById(R.id.Dose);
        view.findViewById(R.id.Morning).setOnClickListener(this::gonextmorining);
        view.findViewById(R.id.Night).setOnClickListener(this::gonextNight);
        view.findViewById(R.id.Noon).setOnClickListener(this::gonextNoon);
        view.findViewById(R.id.Evening).setOnClickListener(this::gonextEvening);
        Bundle bundle = this.getArguments();
        // name form strenght takenfor iseveryday howoften counter
        if(bundle!=null){
            counter=bundle.getInt("count");

            System.out.println("================================================== recived:"+counter);
            if(counter==4) dose.setText("first dose");
            if(counter==3) dose.setText("second dose");
            if(counter==2) dose.setText("third dose");
            if(counter==1) dose.setText("forth dose");
            System.out.println(counter);

            medicine= (Medicine) bundle.getSerializable("obj");
            if(medicine.getMorning()!=null){
                view.findViewById(R.id.Morning).setEnabled(false);
            } if(medicine.getEvening()!=null){
                view.findViewById(R.id.Evening).setEnabled(false);
            } if(medicine.getNoon()!=null){
                view.findViewById(R.id.Noon).setEnabled(false);
            } if(medicine.getNight()!=null){
                view.findViewById(R.id.Night).setEnabled(false);
            }

            System.out.println("the counter recived in timewithinday as "+counter);

            System.out.println(
                    medicine.getMed_name()+"\n"+
                    medicine.getMed_form()+"\n"+
                    medicine.getStrength()+"\n"+
                    medicine.getWhy_Taken()+"\n"+
                    medicine.getHow_often()+"\n"+
                            medicine.getMorning()+"\n"+
                    medicine.getHour_of_Morning()+"\n"+
                            medicine.getEvening()+"\n"+
                    medicine.getHour_of_Evening()+"\n"+
                            medicine.getNight()+"\n"+
                    medicine.getHour_of_Night()+"\n"+
                            medicine.getNoon()+"\n"+
                    medicine.getHour_of_Noon()
                    );
        }


        return  view;
    }

    private void gonextEvening(View view) {
        medicine.setEvening("Evening");
        Bundle Sendbundle= new Bundle();
        Sendbundle.putInt("counter",counter);
        System.out.println("the counter sended from T_I_D as  "+counter);

        Sendbundle.putSerializable("obj",medicine);
        NavController navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.TIme_within_dayDirections.actionTimeInDayToSetTime();
        navController.navigate(R.id.set_time_fragment,Sendbundle);
    }

    private void gonextNoon(View view) {
        medicine.setNoon("Noon");
        Bundle Sendbundle= new Bundle();
        Sendbundle.putInt("counter",counter);
        System.out.println("the counter  tnd as "+counter);

        Sendbundle.putSerializable("obj",medicine);
        NavController navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.TIme_within_dayDirections.actionTimeInDayToSetTime();
        navController.navigate(R.id.set_time_fragment,Sendbundle);
    }

    private void gonextNight(View view) {
        medicine.setNight("Night");
        Bundle Sendbundle= new Bundle();
        Sendbundle.putInt("counter",counter);
        System.out.println("the counter sended tin as "+counter);

        Sendbundle.putSerializable("obj",medicine);
        NavController navController = Navigation.findNavController(view);
        NavDirections navDirections =com.example.medicalreminder.addingmed.view.TIme_within_dayDirections.actionTimeInDayToSetTime();
        navController.navigate(R.id.set_time_fragment,Sendbundle);
    }

    private void gonextmorining(View view) {
        medicine.setMorning("Morning");
        Bundle Sendbundle= new Bundle();
        Sendbundle.putInt("counter",counter);
        System.out.println("the counter sended time in day  as "+counter);

        Sendbundle.putSerializable("obj",medicine);
        NavController navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.TIme_within_dayDirections.actionTimeInDayToSetTime();
        navController.navigate(R.id.set_time_fragment,Sendbundle);
    }
}
