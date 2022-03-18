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
import com.example.medicalreminder.databinding.FragmentInactiveMedicationsBinding;
import com.example.medicalreminder.medicineslist.presenter.InactivePresenter;
import com.example.medicalreminder.medicineslist.presenter.InactivePresenterInterface;

import java.util.List;

public class InactiveViewMedicationsFragment extends Fragment implements InactiveViewInterface {
    FragmentInactiveMedicationsBinding fragmentInactiveMedicationsBinding;
    InactivePresenterInterface inactivePresenterInterface;
    InactiveMedicationsAdapter inactiveMedicationsAdapter;
    List<Medicine> medicines;


    public InactiveViewMedicationsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentInactiveMedicationsBinding = FragmentInactiveMedicationsBinding.inflate(inflater,container,false);
        return fragmentInactiveMedicationsBinding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentInactiveMedicationsBinding.inactiveTxt.setVisibility(View.GONE);
        fragmentInactiveMedicationsBinding.inActiveRecyclerView.setVisibility(View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(InactiveViewMedicationsFragment.this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        fragmentInactiveMedicationsBinding.inActiveRecyclerView.setLayoutManager(layoutManager);

        inactivePresenterInterface =new InactivePresenter(this);
        inactivePresenterInterface.getInactiveMeds(medicines);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentInactiveMedicationsBinding =null;
    }


    @Override
    public void getInactiveMeds(List<Medicine> medications) {
        if(medications.size()==0){
            fragmentInactiveMedicationsBinding.inactiveTxt.setVisibility(View.GONE);
            fragmentInactiveMedicationsBinding.inActiveRecyclerView.setVisibility(View.GONE);
        }else {
            fragmentInactiveMedicationsBinding.inactiveTxt.setVisibility(View.VISIBLE);
            fragmentInactiveMedicationsBinding.inActiveRecyclerView.setVisibility(View.VISIBLE);

            inactiveMedicationsAdapter=new InactiveMedicationsAdapter(this.getContext(),medications);
            fragmentInactiveMedicationsBinding.inActiveRecyclerView.setAdapter(inactiveMedicationsAdapter);

        }
    }


}