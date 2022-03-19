package com.example.medicalreminder;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.example.medicalreminder.alarm.WorkManagerClass;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

        createNotificationChannel();


        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dTime ;
        Date date ;
        Calendar cal = Calendar.getInstance();
        long startTime = 0;
        try {
            dTime = formatter.parse("18:40");
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

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name ="Medical Reminder Channel";
            String desc = "Channel for alram";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Alarm",name,importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}