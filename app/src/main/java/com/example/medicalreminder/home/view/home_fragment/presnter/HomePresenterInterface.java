package com.example.medicalreminder.home.view.home_fragment.presnter;

import com.example.medicalreminder.home.view.home_fragment.model.Medicine;

import java.util.List;

public interface HomePresenterInterface {



    void requestUpdateMedicineList(String date);


    void getTheMovies(List<Medicine> medicines);



}
