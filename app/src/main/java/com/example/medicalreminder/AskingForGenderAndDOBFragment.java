package com.example.medicalreminder;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AskingForGenderAndDOBFragment extends Fragment {


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


        view.findViewById(R.id.signUpRightArrowThree).setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            NavDirections navDirections =  AskingForGenderAndDOBFragmentDirections.toLoginAgainX();
            navController.navigate(navDirections);
        });

        view.findViewById(R.id.signUpLeftArrowThree).setOnClickListener(view1 -> {
            NavController navController = Navigation.findNavController(view1);
            NavDirections navDirections = AskingForGenderAndDOBFragmentDirections.toPreviousPage();
            navController.navigate(navDirections);
        });


        return view ;
    }


    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }





}