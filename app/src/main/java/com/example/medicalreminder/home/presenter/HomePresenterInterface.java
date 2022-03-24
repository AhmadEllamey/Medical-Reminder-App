package com.example.medicalreminder.home.presenter;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.login.model.User;

import java.util.List;

public interface HomePresenterInterface {


    void loadMedicineToShow();

    void loadMedicineInfo();

    void clearTheReadyToShowData();

    void loadTheMedicinesDataFromTheServer(User user);

    void getTheListOfMedicinesForToday(String username);

    void sendTodayMedicines(List<MedicineReadyToShow> medicineReadyToShows);






}
