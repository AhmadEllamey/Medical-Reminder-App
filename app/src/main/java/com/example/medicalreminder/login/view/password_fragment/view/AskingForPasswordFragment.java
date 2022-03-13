package com.example.medicalreminder.login.view.password_fragment.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicalreminder.R;
import com.example.medicalreminder.login.model.User;

import java.io.Serializable;

public class AskingForPasswordFragment extends Fragment implements AskingForPasswordViewInterface {

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



        Bundle bundle = getArguments();
        User user = (User) bundle.getSerializable("User");


        view.findViewById(R.id.signUpRightArrowTwo).setOnClickListener(view1 -> {

             // todo check the entered password and make sure that it meets the requirements and if it's true go with all data to next screen
            EditText passwordText = view.findViewById(R.id.etRegisterPasswordText);
            EditText rePasswordText = view.findViewById(R.id.etRegisterReEnterPasswordText);
            String password = passwordText.getText().toString().trim();
            String rePassword = rePasswordText.getText().toString().trim();
            if(!password.equals("") && !rePassword.equals("") && password.equals(rePassword)){
                if (password.length() >= 8) {
                    // go to the second screen
                    user.setPassword(password);
                    NavController navController = Navigation.findNavController(view1);
                    Bundle bundleToSend = new Bundle();
                    bundleToSend.putSerializable("User",user);
                    navController.navigate(R.id.sign_up_third_fragment,bundleToSend);
                }else {
                    Toast.makeText(getContext(),"password should be at least 8 chars",Toast.LENGTH_SHORT).show();
                }
            }else if (!password.equals(rePassword)){
                Toast.makeText(getContext(),"re-password doesn't match password !",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(),"fill all fields !",Toast.LENGTH_SHORT).show();
            }

        });

        view.findViewById(R.id.signUpLeftArrowTwo).setOnClickListener(view1 -> {

            // todo show an exit alarm if true we go back to the login screen .
            NavController navController = Navigation.findNavController(view1);
            Bundle bundleToSend = new Bundle();
            bundleToSend.putSerializable("User",user);
            navController.navigate(R.id.sign_up_first_fragment,bundleToSend);

        });


        return view ;
    }








}