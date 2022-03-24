package com.example.medicalreminder.Medicitions.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.Medicitions.presenter.Presenter_Medicions;
import com.example.medicalreminder.Medicitions.presenter.Presenter_Medicions_Interface;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.addingmed.view.AddingMed;
import com.example.medicalreminder.home.view.Home;
import com.facebook.all.All;

import java.util.ArrayList;
import java.util.List;

public class Medicions_View extends Fragment implements Medicions_View_Interface {


    //List of Meds
    // todo-> will be sended to the recycler view
    List<Medicine> medsToShow = new ArrayList<>();
    List<Medicine> medsToShow_inactive = new ArrayList<>();

    //ref from presenter
    Presenter_Medicions_Interface presenter_medicions_interface =
            new Presenter_Medicions(this);


    String Email;

    RecyclerView recyclerView;
    RecyclerView recyclerView_Inactive;

    Medicions_Adapter medicions_adapter;
    Medicions_Adapter medicions_adapter_Inactive;

    public Medicions_View() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.medicitions_list , container, false);
        recyclerView = view.findViewById(R.id.MedictionsList_id);
        recyclerView_Inactive = view.findViewById(R.id.InActive_recyclerView);

        Email= Home.getTheCurrentUser().getEmail();
        view.findViewById(R.id.add_Medbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new AddingMed()).commit();

            }
        });

        medicions_adapter= new Medicions_Adapter(medsToShow,getContext());

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(view.getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(medicions_adapter);


        //inactive
        medicions_adapter_Inactive = new Medicions_Adapter(medsToShow_inactive,getContext());

        LinearLayoutManager linearLayoutManager_inactive= new LinearLayoutManager(view.getContext());

        linearLayoutManager_inactive.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView_Inactive.setLayoutManager(linearLayoutManager_inactive);

        recyclerView_Inactive.setAdapter(medicions_adapter_Inactive);

        //to load the data
        requestMedicines();
//        requestMedicines_InActive();

        return view;

    }




   // todo -> send the request to the presenter

    @Override
    public void requestMedicines() {
        System.out.println("request Active");
        presenter_medicions_interface.getMedicines(Email);

        System.out.println("request InActive");
        presenter_medicions_interface.getInactiveMedicines(Email);

    }


    // todo --> receving from presenter

    // ACTIVE MEDs

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void receivingMedicines(List<Medicine> All_Medicions) {
        medsToShow = All_Medicions;
        medicions_adapter.setMedicines(All_Medicions);
        medicions_adapter.notifyDataSetChanged();
        System.out.println("active"+All_Medicions.get(0).getMed_name() );


    }

    // ACTIVE MEDs

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void receivingMedicinesInactive(List<Medicine> All_Medicions) {
        medsToShow_inactive = All_Medicions;
        medicions_adapter_Inactive.setMedicines(All_Medicions);
//        System.out.println("Inactive"+All_Medicions.get(0).getMed_name());
        medicions_adapter_Inactive.notifyDataSetChanged();

    }
}
