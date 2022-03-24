package com.example.medicalreminder.Medicitions.presenter;

import com.example.medicalreminder.Medicitions.View.Medicions_View;
import com.example.medicalreminder.Medicitions.View.Medicions_View_Interface;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.database.Repo;

import java.util.ArrayList;
import java.util.List;

public class Presenter_Medicions implements  Presenter_Medicions_Interface{

    //ref from view
    Medicions_View_Interface medicions_view_interface;

    List<Medicine> myMeds = new ArrayList<>();


    Repo repo = new Repo(this);

    public Presenter_Medicions(Medicions_View_Interface medicions_view_interface) {
        this.medicions_view_interface = medicions_view_interface;
    }


    //reciving from repo and send it to view

    @Override
    public void sendToMedicionList(List<Medicine> All_Medicions) {
        medicions_view_interface.receivingMedicines(All_Medicions);

    }

    @Override
    public void sendToMedicionList_Inactive(List<Medicine> All_Medicions) {
        medicions_view_interface.receivingMedicinesInactive(All_Medicions);
    }


    // send the request to repo

    @Override
    public void getMedicines(String name) {

       repo.getMedicinesList(name);

    }

    @Override
    public void getInactiveMedicines(String name) {
        repo.getMedicinesList_Inactive(name);
    }


}
