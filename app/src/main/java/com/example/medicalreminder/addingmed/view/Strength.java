package com.example.medicalreminder.addingmed.view;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.net.SocketOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Strength extends Fragment {
    private static final String TAG ="tag" ;
    public TextView strength;
    String medname;
    String medform;
    Medicine medicine;
    Spinner unit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.strength,container,false);
        //to be stored
        strength=view.findViewById(R.id.strenghtid);

        view.findViewById(R.id.totakenfor).setOnClickListener(this::next);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("mg");
        spinnerArray.add("g");
        spinnerArray.add("IU");
        spinnerArray.add("mcg");
        spinnerArray.add("mEq");
        spinnerArray.add("mcg/ml");
        spinnerArray.add("mg/ml");
        spinnerArray.add("mL");
        spinnerArray.add("%");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_unit);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==0){
                    medicine.setS_Unit("mg");
                }
                else if (position == 1) {
                    medicine.setS_Unit("g");
                }
                else  if (position==2){
                    medicine.setS_Unit("IU");
                }
                else if (position==3){
                    medicine.setS_Unit("mcg");
                }
                else if (position==4){
                    medicine.setS_Unit("mEq");
                }
                else if (position==5){
                    medicine.setS_Unit("mcg/ml");
                }
                else if (position==6){
                    medicine.setS_Unit("mg/ml");
                }
                else if (position==7){
                    medicine.setS_Unit("mL");
                }
                else if (position==8){
                    medicine.setS_Unit("%");
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
        if(bundle!=null){
            medicine= (Medicine) bundle.getSerializable("obj");
            System.out.println(medicine.getMed_name()+" "+
                                medicine.getMed_form()); }
        Log.i(TAG, "onCreateView: strenght reciving " + bundle.toString());
        return  view;
    }


    private void next(View view) {
        if(!strength.getText().toString().equals("")){
        NavController navController= Navigation.findNavController(view);
        medicine.setStrength(strength.getText().toString());
        System.out.println(medicine.getS_Unit());
        //sending name and form and strenght
        Bundle Sendbundle = new Bundle();
        Sendbundle.putSerializable("obj",medicine);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.StrengthDirections.actionStrengthfragmentToTakenFor();
        navController.navigate(R.id.TakenForfragment,Sendbundle);
        }
        else{
            Toast.makeText(getContext(), "Fill the strenght", Toast.LENGTH_SHORT).show();
        }

    }
}
