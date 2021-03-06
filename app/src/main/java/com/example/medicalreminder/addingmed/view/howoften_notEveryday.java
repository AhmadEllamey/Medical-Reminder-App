package com.example.medicalreminder.addingmed.view;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class howoften_notEveryday extends Fragment {
    NavController navController;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.howoften_not_every_day,container,false);
        Bundle bundle = this.getArguments();
        medicine= (Medicine) bundle.getSerializable("obj");

        view.findViewById(R.id.once_a_week).setOnClickListener(this::nexttochooseday);
        view.findViewById(R.id.every2days).setOnClickListener(this::every2day);
//        view.findViewById(R.id.two_days_aweek).setOnClickListener(this::nexttochooseday);
//        view.findViewById(R.id.threedaysaweek).setOnClickListener(this::nexttochooseday);
        view.findViewById(R.id.every28day).setOnClickListener(this::every28day);

        return  view;
    }

    private void nexttochooseday(View view) {
        String count;
        Button holder = view.findViewById(view.getId());
        count= holder.getText().toString();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        //for calender in home
        medicine.setStart_date(formattedDate);

        medicine.setLast_time_taken(formattedDate);

        medicine.setHow_often(count);

        medicine.setFlag("Specific_Days");


        Bundle Sendbundle = new Bundle();
        Sendbundle.putSerializable("obj",medicine);
        navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.howoften_notEverydayDirections.actionHowoftennoteverydayToChooseTheDays();
        navController.navigate(R.id.choose_theDays,Sendbundle);
    }

    private void every2day(View view) {
        navController = Navigation.findNavController(view);
        Bundle Sendbundle = new Bundle();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        //for calender in home
        medicine.setStart_date(formattedDate);

        medicine.setLast_time_taken(formattedDate);

        medicine.setFlag("Every_two_days");

        Sendbundle.putSerializable("obj",medicine);
        Sendbundle.putInt("intervaloftime",2);

        NavDirections navDirections = com.example.medicalreminder.addingmed.view.howoften_notEverydayDirections.actionHowoftennoteverydatToSetStartDate();
        navController.navigate(R.id.SetStartDate,Sendbundle);
    }

    private void every28day(View view) {
        navController = Navigation.findNavController(view);
        Bundle Sendbundle = new Bundle();


        medicine.setFlag("period_of_days");

        Sendbundle.putSerializable("obj",medicine);
        Sendbundle.putInt("intervaloftime",28);

        NavDirections navDirections = com.example.medicalreminder.addingmed.view.howoften_notEverydayDirections.actionHowoftennoteverydatToSetStartDate();
        navController.navigate(R.id.SetStartDate,Sendbundle);
    }
}
