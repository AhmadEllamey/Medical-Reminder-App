package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
//import com.example.medicalreminder.Presenter.Presenter;
//import com.example.medicalreminder.Presenter.PresenterInterface;
import com.example.medicalreminder.R;

public class Med_Left extends Fragment {
//        implements PresenterInterface {
    //PresenterInterface presenterInterface;
    Context context = getContext();

    TextView medleft;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.remind_med_left,container,false);
        medleft= view.findViewById(R.id.medleft);
        view.findViewById(R.id.nextfrommedleft).setOnClickListener(this::nextend);

        // reciveing name form strenght takenfor
        // iseveryday howoften counter  timewithinday
        //hour amount
        Bundle bundle = this.getArguments();
        if(bundle!=null){
           medicine= (Medicine) bundle.getSerializable("obj");
        }
        return  view;
    }

    private void nextend(View view) {
        NavController navController;
        navController = Navigation.findNavController(view);
        medicine.setMed_left(Integer.parseInt(medleft.getText().toString()));
        Bundle Sendbundle = new Bundle();
        Sendbundle.putSerializable("obj",medicine);
        if(medleft!=null){

        //sending
//        presenterInterface = (PresenterInterface) new Presenter(context, (PresenterInterface) this);

        Log.i(TAG, "save: entered");
        Toast.makeText(getContext(), "entered", Toast.LENGTH_SHORT).show();

        NavDirections navDirections = com.example.medicalreminder.addingmed.view.Med_LeftDirections.actionMedLeftFragmentToEnd2();
        navController.navigate(R.id.end2,Sendbundle);
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
        else{
            Toast.makeText(getContext(), "Fill the amount to remind you ", Toast.LENGTH_LONG).show();
        }
    }
}
