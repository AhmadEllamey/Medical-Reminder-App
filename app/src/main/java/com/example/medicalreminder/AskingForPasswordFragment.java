package com.example.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AskingForPasswordFragment extends Fragment {

    public AskingForPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_asking_for_password, container, false);


        view.findViewById(R.id.signUpRightArrowTwo).setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);

            NavDirections navDirections = AskingForPasswordFragmentDirections.toThirdSignUpPage();

            navController.navigate(navDirections);
        });

        view.findViewById(R.id.signUpLeftArrowTwo).setOnClickListener(view1 -> {

            NavController navController = Navigation.findNavController(view1);

            NavDirections navDirections = AskingForPasswordFragmentDirections.toFirstSignUpPage();

            navController.navigate(navDirections);
        });


        return view ;
    }
}