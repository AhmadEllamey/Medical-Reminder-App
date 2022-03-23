package com.example.medicalreminder.Medicitions.View;

import com.example.medicalreminder.Model.Medicine;

import java.util.List;

public interface Medicions_View_Interface {
    void requestMedicines();
    void receivingMedicines(List<Medicine> All_Medicions);
    void receivingMedicinesInactive(List<Medicine> All_Medicions);
}
