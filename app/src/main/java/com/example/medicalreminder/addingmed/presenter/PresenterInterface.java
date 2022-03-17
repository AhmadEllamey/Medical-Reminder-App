package com.example.medicalreminder.addingmed.presenter;

import com.example.medicalreminder.Model.Medicine;

public interface PresenterInterface {
    void delete();
    void insert(Medicine medicine);
    void show();
}
