package com.example.medicalreminder.Medicitions.presenter;

import com.example.medicalreminder.Model.Medicine;

import java.util.List;

public interface Presenter_Medicions_Interface {
    void sendToMedicionList (List<Medicine> All_Medicions);
    void sendToMedicionList_Inactive (List<Medicine> All_Medicions);
    void getMedicines(String name);
    void getInactiveMedicines(String name);
}
