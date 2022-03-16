package com.example.medicalreminder.home.view.home_fragment.view;

import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;

import java.util.List;

public interface HomeViewInterface {


    void updateTheListOfMedicines(List<MedicineReadyToShow> medicineReadyToShows);


    void updateTheUI();





}
