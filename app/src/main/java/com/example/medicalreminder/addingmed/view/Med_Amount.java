package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

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
import com.example.medicalreminder.R;

public class Med_Amount extends Fragment {

    TextView amounttext;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.medamount,container,false);
        amounttext= view.findViewById(R.id.medamount);
        view.findViewById(R.id.medleft).setOnClickListener(this::nextbtnn);
        // reciveing name form strenght takenfor
        // iseveryday howoften counter  timewithinday
        //hour
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            medicine= (Medicine) bundle.getSerializable("obj");
            Log.i(TAG, "onCreateView: "+bundle);
        }
        return  view;
    }

    private void nextbtnn(View view) {
        if(!amounttext.getText().toString().equals("")) {
            NavController navController;
            navController = Navigation.findNavController(view);
            // sending name form strenght takenfor
            // iseveryday howoften counter  timewithinday
            //hour medamount
            Bundle Sendbundle = new Bundle();
            medicine.setMed_amount(Integer.parseInt(amounttext.getText().toString()));
            Sendbundle.putSerializable("obj", medicine);
            NavDirections navDirections = com.example.myhealth.addingmed.view.Med_AmountDirections.actionMedAmountFragmentToMedLeft();
            navController.navigate(R.id.med_Left_fragment, Sendbundle); }
        else{
            Toast.makeText(getContext(), "Fill the amount", Toast.LENGTH_LONG).show();
        }
    }
}
