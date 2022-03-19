package com.example.medicalreminder.home.view.home_fragment.presnter;

import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;

import java.util.List;

public interface HomePresenterInterface {



    void requestUpdateMedicineList(String date);


    void sendTheListOfMedicines(List<MedicineReadyToShow> medicineReadyToShows);

    void getDataFromFireStoreAndStoreItIntoRoom();

    void transformTheIncomingDataToReadyToShowData();






}
