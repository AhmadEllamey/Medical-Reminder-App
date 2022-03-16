package com.example.medicalreminder.home.presenter;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.login.model.User;

import java.util.List;

public interface HomePresenterInterface {


    void loadMedicineToShow();


    void loadMedicineInfo();

    void clearTheReadyToShowData();

    void loadTheMedicinesDataFromTheServer(User user);




}
