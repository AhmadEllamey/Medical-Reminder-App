package com.example.medicalreminder.medicineslist.presenter;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.medicineslist.view.ActiveViewInterface;

import java.util.List;

public class ActivePresenter implements ActivePresenterInterface{
    List<Medicine>medicines;

     ActiveViewInterface activeViewInterface;

    public  ActivePresenter(ActiveViewInterface activeViewInterface){
        this.activeViewInterface = activeViewInterface;
    }

    @Override
    public void getActiveMeds(List<Medicine> medicine) {
        Repo repo=new Repo(this,this);
        repo.getActiveMedications();
        activeViewInterface.getActiveMedicines(medicine);
    }
}