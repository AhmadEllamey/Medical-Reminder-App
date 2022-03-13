package com.example.medicalreminder.login.view.gender_dob_fragment.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.medicalreminder.R;
import com.example.medicalreminder.login.model.User;
import com.example.medicalreminder.login.view.gender_dob_fragment.presenter.GenderAndDobPresenter;
import com.example.medicalreminder.login.view.gender_dob_fragment.presenter.GenderAndDobPresenterInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AskingForGenderAndDOBFragment extends Fragment implements GenderAndDobViewInterface {

    ProgressBar progressBar;
    ConstraintLayout constraintLayout;
    View viewX;

    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;

    public AskingForGenderAndDOBFragment() {
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
        View view = inflater.inflate(R.layout.fragment_asking_for_gender_and_d_o_b, container, false);
        editText = view.findViewById(R.id.dateOfBirthText);
        DatePickerDialog.OnDateSetListener date = (view12, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        editText.setOnClickListener(view13 -> new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());


        Bundle bundle = getArguments();
        User user = (User) bundle.getSerializable("User");


        view.findViewById(R.id.signUpRightArrowThree).setOnClickListener(view1 -> {

            // make sure that gender is selected and date of birth is written then complete the object and send it to the presenter to crete that user .


            RadioButton maleButton = view.findViewById(R.id.radioMaleButton);
            RadioButton femaleButton = view.findViewById(R.id.radioFemaleButton);
            progressBar = view.findViewById(R.id.progressBar);
            constraintLayout = view.findViewById(R.id.constrainLayoutForDob);
            System.out.println("we got here lol ");
            System.out.println(editText.getText().toString().trim());
            if( (maleButton.isChecked()||femaleButton.isChecked()) && !editText.getText().toString().trim().equals("Date Of Birth") ){

                user.setDob(editText.getText().toString().trim());
                String gender = "";
                if(maleButton.isChecked()){
                    gender = "male" ;
                }else {
                    gender = "female";
                }
                user.setGender(gender);

                GenderAndDobPresenterInterface genderAndDobPresenterInterface =
                        new GenderAndDobPresenter(this,view1);
                genderAndDobPresenterInterface.createUser(user);

                viewX = view ;

                view.findViewById(R.id.signUpRightArrowThree).setEnabled(false);
                view.findViewById(R.id.signUpLeftArrowThree).setEnabled(false);
                view.findViewById(R.id.radioMaleButton).setEnabled(false);
                view.findViewById(R.id.radioFemaleButton).setEnabled(false);
                view.findViewById(R.id.dateOfBirthText).setEnabled(false);

                progressBar.setVisibility(View.VISIBLE);
                System.out.println("done ccccccccccccccccccccccccccccccccccccc");


                // todo show loading bar until the create user ends

            }else {
                Toast.makeText(getContext(),"Fill all fields",Toast.LENGTH_LONG).show();
            }

        });

        view.findViewById(R.id.signUpLeftArrowThree).setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            Bundle bundleToSend = new Bundle();
            bundleToSend.putSerializable("User",user);
            navController.navigate(R.id.sign_up_second_fragment,bundleToSend);
        });


        return view ;
    }


    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }


    @Override
    public void goToLoginPage(View view) {
        NavController navController = Navigation.findNavController(view);
        NavDirections navDirections =  AskingForGenderAndDOBFragmentDirections.toLoginAgainX();
        navController.navigate(navDirections);
    }

    @Override
    public void showToast(String msg) {


        viewX.findViewById(R.id.signUpRightArrowThree).setEnabled(true);
        viewX.findViewById(R.id.signUpLeftArrowThree).setEnabled(true);
        viewX.findViewById(R.id.radioMaleButton).setEnabled(true);
        viewX.findViewById(R.id.radioFemaleButton).setEnabled(true);
        viewX.findViewById(R.id.dateOfBirthText).setEnabled(true);

        progressBar.setVisibility(View.INVISIBLE);

        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }
}