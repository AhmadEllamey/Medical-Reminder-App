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
import com.example.medicalreminder.R;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.view.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

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
        if (!medleft.getText().toString().equals("")) {
            if (Integer.parseInt(medleft.getText().toString()) >= medicine.getMed_amount()) {
                Toast.makeText(getContext(), "Must be less than the medicine amount", Toast.LENGTH_SHORT).show();
            } else {

                medicine.setMed_left(Integer.parseInt(medleft.getText().toString()));
                Bundle Sendbundle = new Bundle();
                Sendbundle.putSerializable("obj", medicine);
                NavDirections navDirections = com.example.medicalreminder.addingmed.view.Med_LeftDirections.actionMedLeftFragmentToInstructions2();
                navController.navigate(R.id.instructions2, Sendbundle);
            }
        } else {
            Toast.makeText(getContext(), "Fill the amount to remind you ", Toast.LENGTH_LONG).show();

        }
    }

}
