package com.example.medicalreminder.displaymedicin.DisplayPresenter;

import com.example.medicalreminder.Model.Medicine;

public interface DisplayPresenterInterface {
    void update(Medicine medicine);

    void delete(Medicine medicine);
    void SendRequest(String name, String user);
}
