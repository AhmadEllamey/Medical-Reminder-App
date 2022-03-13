package com.example.medicalreminder.addingmed.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Taking_For extends Fragment {
    private static final String TAG = "tag";
    TextView why;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.what_taking_for,container,false);

        //to be stored
        why=view.findViewById(R.id.why);
        view.findViewById(R.id.savebtn).setOnClickListener(this::next);
        //reciving the bundle
        // Med Name and Med Form and strenght
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            medicine= (Medicine) bundle.getSerializable("obj");
            System.out.println(medicine.getMed_name()+"\n"+
                    medicine.getMed_form()+"\n"+
            medicine.getStrength());
        }
        Log.i(TAG, "onCreateView: 1 " + bundle.toString());

        return  view;
    }

    private void next(View view) {
        if(!why.getText().toString().equals("")){
            NavController navController= Navigation.findNavController(view);
            //sending name  form strenght and takenfor
            medicine.setWhy_Taken(why.getText().toString());
            Bundle Sendbundle = new Bundle();
            Sendbundle.putSerializable("obj",medicine);
            NavDirections navDirections = com.example.myhealth.addingmed.view.Taking_ForDirections.actionTakenForToIsEveryDay();
            navController.navigate(R.id.IsEveryDayFragment,Sendbundle);}
        else{
            Toast.makeText(getContext(), "Fill the reason", Toast.LENGTH_SHORT).show();
        }

    }
}
