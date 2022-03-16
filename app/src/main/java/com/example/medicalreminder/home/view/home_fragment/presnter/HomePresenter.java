package com.example.medicalreminder.home.view.home_fragment.presnter;




import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.home.view.home_fragment.view.HomeViewInterface;

import java.util.List;

public class HomePresenter implements HomePresenterInterface {


    HomeViewInterface homeViewInterface ;


    public HomePresenter(HomeViewInterface homeViewInterface) {
        this.homeViewInterface = homeViewInterface;
    }

    @Override
    public void requestUpdateMedicineList(String date) {
        System.out.println("");
        Repo repo = new Repo(this);
        repo.getAllMedicines(date);
    }

    @Override
    public void getTheMovies(List<MedicineReadyToShow> medicineReadyToShows) {
        homeViewInterface.updateTheListOfMedicines(medicineReadyToShows);
    }

    @Override
    public void getDataFromFireStoreAndStoreItIntoRoom() {
         // get the fire store data and store them locally in a room



    }

    @Override
    public void transformTheIncomingDataToReadyToShowData() {

    }


}
