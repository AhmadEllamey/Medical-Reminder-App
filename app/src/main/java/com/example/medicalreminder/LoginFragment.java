package com.example.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoginFragment extends Fragment {


    // login and register use firebase fire store

    public LoginFragment() {
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        view.findViewById(R.id.forgotPasswordLink).setOnClickListener(view1 -> {

            NavController navController = Navigation.findNavController(view1);

            NavDirections navDirections = LoginFragmentDirections.toForgotPassword();

            navController.navigate(navDirections);

        });


        view.findViewById(R.id.signUpButton).setOnClickListener(view1 -> {

            NavController navController = Navigation.findNavController(view1);

            NavDirections navDirections = LoginFragmentDirections.toSignUp();

            navController.navigate(navDirections);

        });



        return view ;
    }
}