package com.example.medicalreminder.addingmed.view;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.medicalreminder.Model.Medicine;
//import com.example.medicalreminder.Presenter.Presenter;
import com.example.medicalreminder.Presenter.PresenterInterface;
import com.example.medicalreminder.R;

import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class Set_Time_In_Day extends Fragment{
//        implements PresenterInterface {


//    DatabaseReference myRef;
//    FirebaseDatabase database;
    Context context= getContext();

    private TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    PresenterInterface presenterInterface;
    int counter=0;
    Medicine medicine;

    Button save;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.set_time,container,false);
        timePicker1 = view.findViewById(R.id.datepicker);
//        timePicker1.setIs24HourView(true);

        view.findViewById(R.id.save).setOnClickListener(this::save);
        // name form strenght takenfor iseveryday howoften counter  timewithinday

        Bundle bundle = this.getArguments();
        if(bundle!=null){
           medicine= (Medicine) bundle.getSerializable("obj");
           counter=bundle.getInt("counter");
            System.out.println("the counter sended  in set time as "+counter);

            System.out.println(
                            "counter == "+counter+"\n"+
                            medicine.getMed_name()+"\n"+
                            medicine.getMed_form()+"\n"+
                            medicine.getStrength()+"\n"+
                            medicine.getWhy_Taken()+"\n"+
                            medicine.getHow_often()+"\n"+
                            medicine.getMorning()+"\n"+
                            medicine.getHour_of_Morning()+"\n"+
                            medicine.getEvening()+"\n"+
                            medicine.getHour_of_Evening()+"\n"+
                            medicine.getNight()+"\n"+
                            medicine.getHour_of_Night()+"\n"+
                            medicine.getNoon()+"\n"+
                            medicine.getHour_of_Noon());
            System.out.println("monday"+medicine.isMonday());
            System.out.println("friday"+medicine.isFriday());
            System.out.println("sat"+medicine.isSaturday());
            System.out.println("sun"+medicine.isSunday());
            System.out.println("thurs"+medicine.isThursday());
            System.out.println("tues"+medicine.isTuesday());
            System.out.println("wed"+medicine.isWedensday());
        }

        return  view;
    }

    private void save(View view) {

        String daytime =timePicker1.getHour()+":"+timePicker1.getMinute();
//        String daytime;
//        int hour, minute;
//        String am_pm;
//        if (Build.VERSION.SDK_INT >= 23) {
//            hour = timePicker1.getHour();
//            minute = timePicker1.getMinute();
//        } else {
//            hour = timePicker1.getCurrentHour();
//            minute = timePicker1.getCurrentMinute();
//        }
//        if (hour > 12) {
//            am_pm = "PM";
//            hour = hour - 12;
//        } else {
//            am_pm = "AM";
//        }
//        daytime = hour + ":" + minute + am_pm;
//        presenterInterface = (PresenterInterface) new Presenter(context, (PresenterInterface) this);
        NavController navController;
        navController = Navigation.findNavController(view);


        //////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println(counter);
        System.out.println("CHECK:"+medicine.isIs_Every_Day());
        if (medicine.isIs_Every_Day()) {
            System.out.println(" before counter !=0 test 3 " + counter);
            if (counter != 0) {
                if (medicine.getMorning() != null && medicine.getHour_of_Morning()==null) {
                    medicine.setHour_of_Morning(daytime);
                    System.out.println( "goa el morning-> morning"+medicine.getHour_of_Morning()+"\n  evening "+
                            medicine.getHour_of_Evening()+"\n night"+
                            medicine.getHour_of_Night()+"\n  noon"+
                            medicine.getHour_of_Noon());
                    daytime="";
                } else if (medicine.getEvening() != null &&  medicine.getHour_of_Evening()==null) {
                    medicine.setHour_of_Evening(daytime);

                    System.out.println( "goa el evening-> morning"+medicine.getHour_of_Morning()+"\n  evening "+
                            medicine.getHour_of_Evening()+"\n night"+
                            medicine.getHour_of_Night()+"\n  noon"+
                            medicine.getHour_of_Noon());
                } else if (medicine.getNight() != null &&  medicine.getHour_of_Night()==null) {
                    medicine.setHour_of_Night(daytime);

                    System.out.println( "goa el night-> morning"+medicine.getHour_of_Morning()+"\n  evening "+
                            medicine.getHour_of_Evening()+"\n night"+
                            medicine.getHour_of_Night()+"\n  noon"+
                            medicine.getHour_of_Noon());

                } else if (medicine.getNoon() != null &&  medicine.getHour_of_Noon()==null) {
                    medicine.setHour_of_Noon(daytime);

                    System.out.println( "goa el noon-> morning"+medicine.getHour_of_Morning()+"\n  evening "+
                            medicine.getHour_of_Evening()+"\n night"+
                            medicine.getHour_of_Night()+"\n  noon"+
                            medicine.getHour_of_Noon());
                }
                System.out.println( "2abl m ab3t morning"+medicine.getHour_of_Morning()+"\n  evening "+
                        medicine.getHour_of_Evening()+"\n night"+
                        medicine.getHour_of_Night()+"\n  noon"+
                        medicine.getHour_of_Noon());


                counter = counter - 1;

                System.out.println("================================================================ sended \n  " + counter);

                    Bundle Sendbundle = new Bundle();
                    Sendbundle.putInt("count", counter); //if twice

                    Sendbundle.putSerializable("obj", medicine);
                NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToTimeInDayFragment();
                navController.navigate(R.id.Time_In_Day_fragment, Sendbundle);
                //}
            }

            //once a day
            if (counter == 0) {
                if (medicine.getMed_form().equals("Pill")) {

                    //if once and pill
                    Bundle Sendbundle = new Bundle();
                    if (medicine.getMorning() != null) medicine.setHour_of_Morning(daytime);
                    if (medicine.getEvening() != null) medicine.setHour_of_Evening(daytime);
                    if (medicine.getNight() != null) medicine.setHour_of_Night(daytime);
                    if (medicine.getNoon() != null) medicine.setHour_of_Noon(daytime);
                    Sendbundle.putInt("count", counter); //if twice
                    Sendbundle.putSerializable("obj", medicine);
                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToPlaceholder();
                    navController.navigate(R.id.Med_amount_fragment, Sendbundle);
                } else {

                    //reduce the counter
                    //if once and not a pill
                    Bundle Sendbundle = new Bundle();
//                    if (medicine.getMorning() != null) medicine.setHour_of_Morning(daytime);
//                    if (medicine.getEvening() != null) medicine.setHour_of_Evening(daytime);
//                    if (medicine.getNight() != null) medicine.setHour_of_Night(daytime);
//                    if (medicine.getNoon() != null) medicine.setHour_of_Noon(daytime);
                    Sendbundle.putInt("count", counter); //if twice
                    Sendbundle.putSerializable("obj", medicine);
                    Log.i(TAG, "save: " + Sendbundle);
                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToInstructions2();
                    navController.navigate(R.id.instructions2, Sendbundle);
//                    System.out.println(
//                            medicine.getMed_name()+"\n"+
//                                    medicine.getMed_form()+"\n"+
//                                    medicine.getStrength()+"\n"+
//                                    medicine.getHow_often()+"\n"+
//                                    medicine.getFlag()+"\n"+
//                                    medicine.getWhy_Taken()+"\n"+
//                                    medicine.getMed_amount()+"\n"+
//                                    medicine.getMed_left()+"\n"+
//
//                                    medicine.getMorning()+"\n"+
//                                    medicine.getHour_of_Morning()+"\n"+
//
//                                    medicine.getEvening()+"\n"+
//                                    medicine.getHour_of_Evening()+"\n"+
//
//                                    medicine.getNoon()+"\n"+
//                                    medicine.getHour_of_Noon()+"\n"+
//
//                                    medicine.getNight()+"\n"+
//                                    medicine.getHour_of_Night()+"\n"+
//
//                                    medicine.getStart_date()+"\n"+
//                                    medicine.getEnd_date()+"\n"+
//
//                                    medicine.isFriday()+"\n"+
//                                    medicine.isSaturday()+"\n"+
//                                    medicine.isSunday()+"\n"+
//                                    medicine.isMonday()+"\n"+
//                                    medicine.isTuesday()+"\n"+
//                                    medicine.isWedensday()+"\n"+
//                                    medicine.isThursday()+"\n"
//                    );
                }
            }
        }
    }
//        if(!medicine.isIs_Every_Day()){
//            if(counter!=0)
//            {
//                //int looping=counter-1;
//                //for(int i = 0; i<looping;i++){
//                    if(medicine.getMorning()!=null)  medicine.setHour_of_Morning(daytime);
//                    if(medicine.getEvening()!=null)  medicine.setHour_of_Evening(daytime);
//                    if(medicine.getNight()!=null)  medicine.setHour_of_Night(daytime);
//                    if(medicine.getNoon()!=null)  medicine.setHour_of_Noon(daytime);
//                    Bundle Sendbundle = new Bundle();
//                    int counter2 = counter-1;
//                    Sendbundle.putInt("count",counter2); //if twice
//                    System.out.println(counter+" sended from settime");
//                    Sendbundle.putSerializable("obj",medicine);
//                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToChooseTheDays();
//                    navController.navigate(R.id.choose_theDays,Sendbundle);
//                //}
//
//            }
//            if(counter<=1)   {
//                if(medicine.getMed_form().equals("Pill")) {
//                    //if once and pill
//                    Bundle Sendbundle = new Bundle();
//                    if(medicine.getMorning()!=null)  medicine.setHour_of_Morning(daytime);
//                    if(medicine.getEvening()!=null)  medicine.setHour_of_Evening(daytime);
//                    if(medicine.getNight()!=null)  medicine.setHour_of_Night(daytime);
//                    if(medicine.getNoon()!=null)  medicine.setHour_of_Noon(daytime);
//                    Sendbundle.putInt("count",counter); //if twice
//                    Sendbundle.putSerializable("obj",medicine);
//                    System.out.println(
//                            medicine.getMed_name()+"\n"+
//                                    medicine.getMed_form()+"\n"+
//                                    medicine.getStrength()+"\n"+
//                                    medicine.getWhy_Taken()+"\n"+
//                                    medicine.getFlag()+"\n"+
//                                    medicine.getHow_often()+"\n"+
//                                    medicine.getMorning()+"\n"+
//                                    medicine.getHour_of_Morning()+"\n"+
//                                    medicine.getEvening()+"\n"+
//                                    medicine.getHour_of_Evening()+"\n"+
//                                    medicine.getNight()+"\n"+
//                                    medicine.getHour_of_Night()+"\n"+
//                                    medicine.getNoon()+"\n"+
//                                    medicine.getHour_of_Noon()+"\n"+
//                                    medicine.getMed_amount()+"\n"+
//                                    medicine.getMed_left()
//
//
//                    );
//                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToPlaceholder();
//                    navController.navigate(R.id.Med_amount_fragment,Sendbundle);
//                }
//                else{
//                    //reduce the counter
//
//                    Bundle Sendbundle = new Bundle();
//                    if(medicine.getMorning()!=null)  medicine.setHour_of_Morning(daytime);
//                    if(medicine.getEvening()!=null)  medicine.setHour_of_Evening(daytime);
//                    if(medicine.getNight()!=null)  medicine.setHour_of_Night(daytime);
//                    if(medicine.getNoon()!=null)  medicine.setHour_of_Noon(daytime);
//
//                    Sendbundle.putInt("count",counter); //if twice
//                    Sendbundle.putSerializable("obj",medicine);
//                    Log.i(TAG, "save: "+Sendbundle);
//                    NavDirections navDirections = com.example.medicalreminder.addingmed.view.Set_Time_In_DayDirections.actionSetTimeFragmentToInstructions2();
//                    navController.navigate(R.id.instructions2,Sendbundle);
//                    System.out.println(
//                            medicine.getMed_name()+"\n"+
//                                    medicine.getMed_form()+"\n"+
//                                    medicine.getStrength()+"\n"+
//                                    medicine.getHow_often()+"\n"+
//                                    medicine.getFlag()+"\n"+
//                                    medicine.getWhy_Taken()+"\n"+
//                                    medicine.getMed_amount()+"\n"+
//                                    medicine.getMed_left()+"\n"+
//
//                                    medicine.getMorning()+"\n"+
//                                    medicine.getHour_of_Morning()+"\n"+
//
//                                    medicine.getEvening()+"\n"+
//                                    medicine.getHour_of_Evening()+"\n"+
//
//                                    medicine.getNoon()+"\n"+
//                                    medicine.getHour_of_Noon()+"\n"+
//
//                                    medicine.getNight()+"\n"+
//                                    medicine.getHour_of_Night()+"\n"+
//
//                                    medicine.getStart_date()+"\n"+
//                                    medicine.getEnd_date()+"\n"+
//
//                                    medicine.isFriday()+"\n"+
//                                    medicine.isSaturday()+"\n"+
//                                    medicine.isSunday()+"\n"+
//                                    medicine.isMonday()+"\n"+
//                                    medicine.isTuesday()+"\n"+
//                                    medicine.isWedensday()+"\n"+
//                                    medicine.isThursday()+"\n"
//                    );}
//
//
//            }

//        }
//        else {
////
//                if(medicine.getMed_form().equals("Pill")){
//                    //if once and pill
//                    Bundle Sendbundle = new Bundle();
//                    if(medicine.getMorning()!=null)  medicine.setHour_of_Morning(daytime);
//                    if(medicine.getEvening()!=null)  medicine.setHour_of_Evening(daytime);
//                    if(medicine.getNight()!=null)  medicine.setHour_of_Night(daytime);
//                    if(medicine.getNoon()!=null)  medicine.setHour_of_Noon(daytime);
//                    Sendbundle.putInt("count",counter); //if twice
//                    Sendbundle.putSerializable("obj",medicine);
//                    Log.i(TAG, "save: ++++++"+medicine.getMed_name());
//
//                    NavDirections navDirections = Set_Time_In_DayDirections.actionSetTimeFragmentToPlaceholder();
//                    navController.navigate(R.id.Med_amount_fragment,Sendbundle);
//                    }
//                else{
//                    //reduce the counter
//
//                    Bundle Sendbundle = new Bundle();
//                    if(medicine.getMorning()!=null)  medicine.setHour_of_Morning(daytime);
//                    if(medicine.getEvening()!=null)  medicine.setHour_of_Evening(daytime);
//                    if(medicine.getNight()!=null)  medicine.setHour_of_Night(daytime);
//                    if(medicine.getNoon()!=null)  medicine.setHour_of_Noon(daytime);
//
//                    Sendbundle.putInt("count",counter); //if twice
//                    Sendbundle.putSerializable("obj",medicine);
//                    Log.i(TAG, "save: "+Sendbundle);
//                    NavDirections navDirections = Set_Time_In_DayDirections.actionSetTimeFragmentToPlaceholder();
//                    navController.navigate(R.id.end2,Sendbundle); }
//            }
            //presenterInterface.AddMedacine(med);//}

}
