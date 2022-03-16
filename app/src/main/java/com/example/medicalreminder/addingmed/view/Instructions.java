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
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.view.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Instructions extends Fragment {
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
        return view;
}

    private void save(View view) {
        medicine.setActive(true);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Medicine Info").document(medicine.getUser_name()+"-"+medicine.getMed_name())
                .set(medicine).addOnSuccessListener(aVoid -> {
            //Log.d(TAG, "DocumentSnapshot successfully written!");
            //getChildFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

            Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new HomeFragment()).commit();

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Log.w(TAG, "Error writing document", e);
            }
        });
    }
    }
