package com.example.medicalreminder.addingmed.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.addingmed.presenter.Presenter;
import com.example.medicalreminder.addingmed.presenter.PresenterInterface;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.view.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Instructions extends Fragment implements ViewInterface {
    private static final String TAG ="tag" ;
    public TextView strength;
    String medname;
    String medform;
    Medicine medicine;
    Spinner unit;
    PresenterInterface presenterInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.instructions,container,false);



        //to be stored
        view.findViewById(R.id.saveMedicine).setOnClickListener(this::save);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Before eating");
        spinnerArray.add("While eating");
        spinnerArray.add("After eating");
        spinnerArray.add("Dose not matter");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) view.findViewById(R.id.instructions);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==0){
                    medicine.setInstructions("Before eating");
                }
                else if (position == 1) {
                    medicine.setInstructions("While eating");
                }
                else  if (position==2){
                    medicine.setInstructions("After eating");
                }
                else if (position==3){
                    medicine.setInstructions("Dose not matter");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        //reciving the bundle
        // Med Name and Med Form
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            medicine = (Medicine) bundle.getSerializable("obj");
        }


        System.out.println( "2abl m ab3t morning"+medicine.getHour_of_Morning()+"\n  evening "+
                medicine.getHour_of_Evening()+"\n night"+
                medicine.getHour_of_Night()+"\n  noon"+
                medicine.getHour_of_Noon());

        return view;
}

    private void save(View view) {
        medicine.setActive(true);
        //MVP
        //implementaion in the presenter
        presenterInterface = new Presenter(this);

        presenterInterface.insert(medicine);

    }

    @Override
    public void show_Med() {
        Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new HomeFragment()).commit();

    }

    @Override
    public void show_faild(String msg) {
        Toast.makeText(getContext(), msg , Toast.LENGTH_SHORT).show();
    }
}
