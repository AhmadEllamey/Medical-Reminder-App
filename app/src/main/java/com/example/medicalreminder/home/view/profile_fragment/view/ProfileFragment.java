package com.example.medicalreminder.home.view.profile_fragment.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.login.model.User;

public class ProfileFragment extends Fragment implements ProfileViewInterface {


    public ProfileFragment() {
        // Required empty public constructor
    }

    public ProfileFragment(MedicineReadyToShow medicine){
        System.out.println("we are right");
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        User user = Home.getTheCurrentUser();

        TextView name = view.findViewById(R.id.nameTextProfile);
        TextView email = view.findViewById(R.id.EmailTextProfile);
        TextView dob = view.findViewById(R.id.dobTextProfile);
        TextView gender = view.findViewById(R.id.genderTextProfile);
        TextView phone = view.findViewById(R.id.phoneTextProfile);

        name.setText(user.getName());
        email.setText(user.getEmail());
        dob.setText(user.getDob());
        gender.setText(user.getGender());
        phone.setText(user.getPhone());


        return view;

    }
}