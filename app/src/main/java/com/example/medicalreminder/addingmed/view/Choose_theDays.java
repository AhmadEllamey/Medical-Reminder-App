package com.example.medicalreminder.addingmed.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;

import com.example.myhealth.Model.Medicine;
import com.example.myhealth.R;

import java.util.ArrayList;
import java.util.List;

public class Choose_theDays extends Fragment implements Week_Adapter.onclickday {
    Week_Adapter week_adapter;
    RecyclerView recyclerView;
    List <String> WeeK= new ArrayList<>();
    Medicine  medicine;
    int counter;
    boolean iseveryday;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.choosetheday,container,false);
        WeeK.add("Saturday");
        WeeK.add("Sunday");
        WeeK.add("Monday");
        WeeK.add("Tuesday");
        WeeK.add("Wednesday");
        WeeK.add("Thursday");
        WeeK.add("Friday");

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            counter = bundle.getInt("count");
            medicine= (Medicine) bundle.getSerializable("obj");

            System.out.println("in choose day");
            System.out.println(counter);
            System.out.println("monday"+medicine.isMonday());
            System.out.println("friday"+medicine.isFriday());
            System.out.println("sat"+medicine.isSaturday());
            System.out.println("sun"+medicine.isSunday());
            System.out.println("thurs"+medicine.isThursday());
            System.out.println("tues"+medicine.isTuesday());
            System.out.println("wed"+medicine.isWedensday());


        }
        recyclerView=view.findViewById(R.id.recycleweek);
        week_adapter= new Week_Adapter(WeeK,getContext(),this);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(week_adapter);
        return view;

    }

    @Override
    public void OnClickDay(int position ,View view) {


        Toast.makeText(getContext(), WeeK.get(position), Toast.LENGTH_SHORT).show();

        if(WeeK.get(position).equals("Sunday"))  medicine.setSunday(true);
        if(WeeK.get(position).equals("Saturday"))  medicine.setSaturday(true);
        if(WeeK.get(position).equals("Monday"))  medicine.setMonday(true);
        if(WeeK.get(position).equals("Tuesday"))  medicine.setTuesday(true);
        if(WeeK.get(position).equals("Wednesday"))  medicine.setWedensday(true);
        if(WeeK.get(position).equals("Thursday"))  medicine.setThursday(true);
        if(WeeK.get(position).equals("Friday"))  medicine.setFriday(true);

        Bundle Sendbundle = new Bundle();
        Sendbundle.putInt("count",counter);
        Sendbundle.putSerializable("obj",medicine);

        NavController navController;
        navController = Navigation.findNavController(view);
        NavDirections navDirections = Choose_theDaysDirections.actionChooseTheDaysToTimeInDayFragment();
        navController.navigate(R.id.Time_In_Day_fragment,Sendbundle);
    }
}
