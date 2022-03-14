package com.example.medicalreminder.addingmed.view;

import android.content.SyncStatusObserver;
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

import java.net.SocketOption;
import java.security.SecureRandom;

public class Strength extends Fragment {
    private static final String TAG ="tag" ;
    public TextView strength;
    String medname;
    String medform;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.strength,container,false);
        //to be stored
        strength=view.findViewById(R.id.strenghtid);

        view.findViewById(R.id.totakenfor).setOnClickListener(this::next);

        //reciving the bundle
        // Med Name and Med Form
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            medicine= (Medicine) bundle.getSerializable("obj");
            System.out.println(medicine.getMed_name()+" "+
                                medicine.getMed_form()
                    );
        }
        Log.i(TAG, "onCreateView: strenght reciving " + bundle.toString());
        return  view;
    }


    private void next(View view) {
        if(!strength.getText().toString().equals("")){
        NavController navController= Navigation.findNavController(view);
        medicine.setStrength(strength.getText().toString());
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
