package com.example.medicalreminder.login.view.name_email_phone_fragment.view;

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
import com.example.medicalreminder.login.view.name_email_phone_fragment.presenter.AskingForNameEmailPhonePresenter;
import com.example.medicalreminder.login.view.name_email_phone_fragment.presenter.AskingForNameEmailPhonePresenterInterface;

public class AskingForName_Email_PhoneFragment extends Fragment implements AskingForNameEmailPhoneViewInterface {


    public AskingForName_Email_PhoneFragment() {
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
        View view = inflater.inflate(R.layout.fragment_asking_for_name__email__phone, container, false);

        EditText emailText = view.findViewById(R.id.etRegisterEmailText);
        EditText nameText = view.findViewById(R.id.etRegisterNameText);
        EditText phoneText = view.findViewById(R.id.etRegisterPhoneText);


        Bundle bundle = getArguments();
        User user = (User) (bundle != null ? bundle.getSerializable("User") : null);

        if(user != null){
            emailText.setText(user.getEmail());
            nameText.setText(user.getName());
            phoneText.setText(user.getPhone());
        }

        view.findViewById(R.id.signUpRightArrowOne).setOnClickListener(view1 -> {


            String mail = emailText.getText().toString().trim();

            String name = nameText.getText().toString().trim();

            String phone = phoneText.getText().toString().trim();

            if(!(mail.equals("") && name.equals("") && phone.equals(""))){

                // check that all the required fields are filled then check for the mail if it exists or not , if not go to the second screen .
                AskingForNameEmailPhonePresenterInterface askingForNameEmailPhonePresenterInterface =
                        new AskingForNameEmailPhonePresenter(this,view1);
                askingForNameEmailPhonePresenterInterface.checkIfTheUserExists(mail , name , phone);
            }

        });

        view.findViewById(R.id.signUpLeftArrowOne).setOnClickListener(this::goToThePreviousPage);


        return view ;
    }

    @Override
    public void goToTheNextPage(View view, String name, String email, String phone) {


        NavController navController = Navigation.findNavController(view);

        // to send data with bundle
        Bundle bundle = new Bundle();
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        bundle.putSerializable("User",user);
        navController.navigate(R.id.sign_up_second_fragment,bundle);

    }

    @Override
    public void goToThePreviousPage(View view) {

        NavController navController = Navigation.findNavController(view);

        NavDirections navDirections = AskingForName_Email_PhoneFragmentDirections.toBackPage() ;

        navController.navigate(navDirections);

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }
}