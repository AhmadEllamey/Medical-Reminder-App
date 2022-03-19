package com.example.medicalreminder.medicineslist.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.databinding.FragmentActiveMedicationsBinding;
import com.example.medicalreminder.medicineslist.presenter.ActivePresenter;
import com.example.medicalreminder.medicineslist.presenter.ActivePresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class ActiveViewMedicationsFragment extends Fragment implements ActiveViewInterface {
    private FragmentActiveMedicationsBinding fragmentActiveMedicationsBinding;
    ActivePresenterInterface activePresenterInterface;
    ActiveMedicationsAdapter activeMedicationsAdapter;
    List<Medicine>medicines;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentActiveMedicationsBinding = FragmentActiveMedicationsBinding.inflate(inflater,container,false);
        return fragmentActiveMedicationsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentActiveMedicationsBinding.activeTxt.setVisibility(View.GONE);
        fragmentActiveMedicationsBinding.ActiveRecyclerView.setVisibility(View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ActiveViewMedicationsFragment.this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        fragmentActiveMedicationsBinding.ActiveRecyclerView.setLayoutManager(layoutManager);

        activePresenterInterface =new ActivePresenter(this);
        medicines = new ArrayList<>();
        activePresenterInterface.getActiveMeds(medicines);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentActiveMedicationsBinding =null;
    }


    @Override
    public void getActiveMedicines(List<Medicine> medications) {
        if(medications.size()==0){
            fragmentActiveMedicationsBinding.activeTxt.setVisibility(View.GONE);
            fragmentActiveMedicationsBinding.ActiveRecyclerView.setVisibility(View.GONE);
        }else {
            fragmentActiveMedicationsBinding.activeTxt.setVisibility(View.VISIBLE);
            fragmentActiveMedicationsBinding.ActiveRecyclerView.setVisibility(View.VISIBLE);

            activeMedicationsAdapter=new ActiveMedicationsAdapter(this.getContext(),medications);
            fragmentActiveMedicationsBinding.ActiveRecyclerView.setAdapter(activeMedicationsAdapter);

        }
    }
}