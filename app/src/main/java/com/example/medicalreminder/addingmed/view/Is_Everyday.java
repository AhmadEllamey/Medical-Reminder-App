package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;

import java.util.ArrayList;
import java.util.List;

public class Is_Everyday extends Fragment {
    Button yes;
    Button no;
    Boolean is = false;
    NavController navController;
    Medicine medicine;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.is_everyday, container, false);

        yes=view.findViewById(R.id.yes);
        no=view.findViewById(R.id.No);

        //reciving the bundle
        // Med Name and Med Form and strenght and takenfor
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            medicine= (Medicine) bundle.getSerializable("obj");
            System.out.println(medicine.getMed_name()+"\n"+
                    medicine.getMed_form()+"\n"+
                    medicine.getStrength()+"\n"+
                    medicine.getWhy_Taken());
        }
        Log.i(TAG, "onCreateView: reciving " + bundle.toString());
        yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                is = true;
                navController = Navigation.findNavController(yes);
                gonext(view);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is = false;
                navController = Navigation.findNavController(no);
                gonext(view);

            }
        });
        return view;
    }

    //if condition if no and if yes  MISSING
    private void gonext(View view) {

        navController = Navigation.findNavController(view);
        medicine.setIs_Every_Day(is);
        if(is==true){
            Bundle Sendbundle = new Bundle();
            Sendbundle.putSerializable("obj",medicine);

            NavDirections navDirections = com.example.medicalreminder.addingmed.view.Is_EverydayDirections.actionIsEveryDayToHowOftenInDay();
            navController.navigate(R.id.How_Often_In_Day_fragment,Sendbundle);}

        else{
            //not every day has different choose
            Bundle Sendbundle = new Bundle();
            Sendbundle.putSerializable("obj",medicine);
            NavDirections navDirections = com.example.medicalreminder.addingmed.view.Is_EverydayDirections.actionIsEveryDayFragmentToPlaceholder();
            navController.navigate(R.id.howoftennoteveryday,Sendbundle);
        }

    }
}


