package com.example.medicalreminder;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ForgotPasswordFragment extends Fragment {


    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    public void goBackToLoginScreen(View view){

        NavController navController = Navigation.findNavController(view);

        NavDirections navDirections = ForgotPasswordFragmentDirections.toLogin();

        navController.navigate(navDirections);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        view.findViewById(R.id.okButtonToSendForgotPasswordRequestButton).setOnClickListener(this::goBackToLoginScreen);

        view.findViewById(R.id.cancelButtonToSendForgotPasswordRequestButton).setOnClickListener(this::goBackToLoginScreen);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener((v, keyCode, event) -> {
            Log.i("tag", "keyCode: " + keyCode);
            if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                Log.i("tag", "onKey Back listener is working!!!");
                goBackToLoginScreen(v);
                return true;
            }
            return false;
        });


        return view ;
    }






}