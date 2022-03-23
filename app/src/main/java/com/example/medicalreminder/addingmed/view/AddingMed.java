package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.medicalreminder.home.view.Home;


public class AddingMed extends Fragment  {

    Medicine medicine = new Medicine();
    TextView med_name;
    Button nextbtn;

    public AddingMed() {}

    //connection
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.adding_med,container,false);
    //to be stored
        med_name=view.findViewById(R.id.Medname);
        nextbtn=view.findViewById(R.id.savebtn);
        nextbtn.setOnClickListener(btnView -> {

            if(!med_name.getText().toString().equals("")){
            NavController navController= Navigation.findNavController(btnView);
            medicine.setMed_name(med_name.getText().toString().trim());
            medicine.setUser_name(Home.getTheCurrentUser().getEmail());
            Bundle bundle = new Bundle();
                bundle.putSerializable("obj", medicine);
            Log.i(TAG, "onCreateView: 1 " + bundle.toString());

            NavDirections navDirections = com.example.medicalreminder.addingmed.view.AddingMedDirections.actionAddingMedToMedForm();
            navController.navigate(R.id.MedFormfragment, bundle);}
            else {
                Toast.makeText(getContext(), "Please put the medicine name", Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }
}
