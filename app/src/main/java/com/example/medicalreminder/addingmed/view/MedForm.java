package com.example.medicalreminder.addingmed.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.myhealth.Model.Medicine;
import com.example.myhealth.R;

public class MedForm extends Fragment {
    private static final String TAG = "tag";
    String Form;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.med_form, container, false);
        view.findViewById(R.id.Pill).setOnClickListener(this::gonext);
        view.findViewById(R.id.Solution).setOnClickListener(this::gonext);
        view.findViewById(R.id.Injection).setOnClickListener(this::gonext);
        view.findViewById(R.id.Powder).setOnClickListener(this::gonext);
        view.findViewById(R.id.Drops).setOnClickListener(this::gonext);
        view.findViewById(R.id.Inhaler).setOnClickListener(this::gonext);

        //reciving the bundle

        // Med Name

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            medicine = (Medicine) bundle.getSerializable("obj");
            System.out.println(medicine.getMed_name());
        }
        Log.i(TAG, "onCreateView: 1 " + bundle.toString());
        return view;
    }

    public void  gonext (View view) {
        Button holder = view.findViewById(view.getId());
        Form= holder.getText().toString();
        medicine.setMed_form(Form);
        NavController navController = Navigation.findNavController(view);

        //sending name and form

        Bundle Sendbundle = new Bundle();

        Sendbundle.putSerializable("obj",medicine);
        NavDirections navDirections = MedFormDirections.actionMedFormToStrengthfragment();
        navController.navigate(R.id.Strengthfragment, Sendbundle);

        // navController.navigate(navDirections);
    }

}
