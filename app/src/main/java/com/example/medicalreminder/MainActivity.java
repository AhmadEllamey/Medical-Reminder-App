package com.example.medicalreminder;



import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.alarm.WorkManagerClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {


    public MainActivity() {
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dTime ;
        Date date ;
        Calendar cal = Calendar.getInstance();
        long startTime = 0;
        try {
            dTime = formatter.parse("8:55");
            cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
            cal.set(Calendar.MINUTE,dTime.getMinutes());
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            date = cal.getTime();
            startTime = date.getTime();
            System.out.println("we are here");
            System.out.println(startTime);
        }catch (Exception e){
            e.printStackTrace();
        }



        // set the alarm to the medicine object

//        Data data = new Data.Builder()
//                .putString("Medicine Name",medicineReadyToShow.getName())
//                .putString("Pills ToTake",medicineReadyToShow.getPills_to_take())
//                .putString("Time Per Day",medicineReadyToShow.getWhen())
//                .putString("Date Of The Day",medicineReadyToShow.getDate())
//                .putString("Time Of The Day",medicineReadyToShow.getTime())
//                .putString("Instructions",medicineReadyToShow.getInstruction())
//                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerClass.class)
                .setInitialDelay(startTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                //.setScheduleRequestedAt(startTime, TimeUnit.MILLISECONDS)
                .build();

        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
 


        context = this ;

        // load the first screen programmatically
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if(navHostFragment!=null){
            System.out.println("we are here !");
            NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_users_managment);
            navGraph.setStartDestination(R.id.login_fragment);
            navController.setGraph(navGraph);
        }


    }



    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}