package com.example.medicalreminder.addingmed.view;

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
import com.example.medicalreminder.R;

public class End extends Fragment {
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.end,container,false);
//    amounttext= view.findViewById(R.id.medamount);
//        view.findViewById(R.id.medleft).setOnClickListener(this::nextbtnn);
//
//    Bundle bundle = this.getArguments();
//        if(bundle!=null){
//        medname = bundle.getString("medname");
//        medform = bundle.getString("medform");
//        medstren=bundle.getString("medstren");
//        takenfor=bundle.getString("medstren");
//        iseveryday=bundle.getBoolean("iseveryday");
//        daily=bundle.getString("daily");
//        counter=bundle.getInt("counter");
//        dose=bundle.getString("durring");
//        hour=bundle.getString("hour");
//    }


        Bundle bundle= this.getArguments();
         medicine= (Medicine) bundle.getSerializable("obj");

        view.findViewById(R.id.showbtn).setOnClickListener(this::show);
        return  view;
    }

    private void show(View view) {
        Bundle Sendbundle= new Bundle();
        Sendbundle.putSerializable("obj",medicine);
        NavController navController= Navigation.findNavController(view);
        NavDirections navDirections = EndDirections.actionEnd2ToDisplaymed();
        navController.navigate(R.id.displaymed,Sendbundle);

    }

}
