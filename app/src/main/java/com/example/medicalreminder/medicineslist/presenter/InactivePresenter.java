package com.example.medicalreminder.medicineslist.presenter;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.medicineslist.view.InactiveViewInterface;

import java.util.List;

public class InactivePresenter implements InactivePresenterInterface{
    InactiveViewInterface inactiveViewInterface;

    public  InactivePresenter(InactiveViewInterface inactiveViewInterface) {
        this.inactiveViewInterface=inactiveViewInterface;
    }

    @Override
    public void getInactiveMeds(List<Medicine> medicine) {
    Repo repo=new Repo(this);
    repo.getInactiveMedications();
        inactiveViewInterface.getInactiveMeds(medicine);
    }
}

