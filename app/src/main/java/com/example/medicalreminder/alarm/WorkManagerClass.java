package com.example.medicalreminder.alarm;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.medicalreminder.MainActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.ReminderDialog.MyCustomDialoge;
import com.example.medicalreminder.database.AppDataBase;
import com.example.medicalreminder.database.DatabaseFunctions;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkManagerClass extends Worker {

    Context context;
    LayoutInflater inflater;
    String userNameGlobal;
    public WorkManagerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Result doWork() {

        String medicineName = getInputData().getString("Medicine Name");
        String pillsToTake = getInputData().getString("Pills ToTake");
        String timePerDay = getInputData().getString("Time Per Day");
        String dateOfTheDay = getInputData().getString("Date Of The Day");
        String time = getInputData().getString("Time Of The Day");
        String instructions = getInputData().getString("Instructions");
        String username = getInputData().getString("Username");

        userNameGlobal = username ;

        //pop up the window
        NotificationCompat.Builder builder = new NotificationCompat.Builder( getApplicationContext(),"Alarm")
                .setContentTitle("medicineName")
                .setContentText("Take ("+pillsToTake+") pill/s from your medicine ("+medicineName+")\n" +
                        "Time : "+time+" , "+dateOfTheDay+" , "+timePerDay+"\n" +
                        "note : " + instructions)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.med)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
//                .setContentIntent(pendingIntent);


        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(123 ,builder.build());

        // todo -- > display the dialog (not working with no error)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Run your task here
                Toast.makeText(context, "Testing", Toast.LENGTH_LONG).show();
                //openDialog();


                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View view = inflater.inflate(R.layout.reminder_layout,null);

                builder.setView(view)
                        .setTitle("Medicine Reminder")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        })
                        .setPositiveButton("Take", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // minus one from med_amount
                                // update the last_taken date
                                // update the fire_store
                                // update the medicine info || medicine ready to show
                                Toast.makeText(context, "Take", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Snooze", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Snooze
                                Toast.makeText(context, "Snooze", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.create();



            }
        }, 1000 );


        // set the next alarm

        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseFunctions databaseFunctions;
                AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
                databaseFunctions = appDataBase.databaseFunctions();
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                List<MedicineReadyToShow> medicineReadyToShows = databaseFunctions.getTodayMedicines(dateFormat.format(date),username);

                manageTheAlarms(medicineReadyToShows);

            }
        }).start();

        System.out.println("----------------------------------------- alarm working ------------------------------------------");

        return Result.success();
    }


    public void manageTheAlarms(List<MedicineReadyToShow> medicineReadyToShows){

        System.out.println("******************************************************************************************************");


        // we need to find the nearest time from now

        if(!medicineReadyToShows.isEmpty()){
            List<Date> dates = new ArrayList<>();
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            Date dTime ;
            Date date ;
            Calendar cal = Calendar.getInstance();
            Date currentTime = new Date();

            // first we need to find the nearest time from now

            int j  = 0 ;

            for (MedicineReadyToShow i : medicineReadyToShows) {
                try {
                    System.out.println(medicineReadyToShows.get(j).getTime());
                    System.out.println(medicineReadyToShows.get(j).getDate());
                    dTime = formatter.parse(i.getTime());
                    cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
                    cal.set(Calendar.MINUTE,dTime.getMinutes());
                    cal.set(Calendar.SECOND,0);
                    cal.set(Calendar.MILLISECOND,0);
                    date = cal.getTime();
                    dates.add(date);
                    j++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            // we get the right medicine to set the alarm with
            try {
                System.out.println("OMG Date >>>>>>>>>>>>>>>>> " +dates.get(0));
                MedicineReadyToShow medicineReadyToShow =
                        medicineReadyToShows.get(dates.indexOf(getNearestDate(dates,currentTime)));

                System.out.println("The time is ------------> "+ medicineReadyToShow.getTime());

                // set the alarm to the medicine object

                Data data = new Data.Builder()
                        .putString("Medicine Name",medicineReadyToShow.getName())
                        .putString("Pills ToTake",medicineReadyToShow.getPills_to_take())
                        .putString("Time Per Day",medicineReadyToShow.getWhen())
                        .putString("Date Of The Day",medicineReadyToShow.getDate())
                        .putString("Time Of The Day",medicineReadyToShow.getTime())
                        .putString("Instructions",medicineReadyToShow.getInstruction())
                        .putString("Username",medicineReadyToShow.getUser_name())
                        .build();

                // get the time to set the alarm

                SimpleDateFormat formatterX = new SimpleDateFormat("hh:mm");
                Date dTimeX ;
                Date dateX ;
                Calendar calX = Calendar.getInstance();
                long startTime = 0;
                try {
                    dTimeX = formatterX.parse(medicineReadyToShow.getTime());
                    calX.set(Calendar.HOUR_OF_DAY,dTimeX.getHours());
                    calX.set(Calendar.MINUTE,dTimeX.getMinutes());
                    calX.set(Calendar.SECOND,0);
                    calX.set(Calendar.MILLISECOND,0);
                    dateX = calX.getTime();
                    startTime = dateX.getTime();
                    System.out.println("we are here");
                    System.out.println(startTime);
                }catch (Exception e){
                    e.printStackTrace();
                }

                // set the alarm

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerClass.class)
                        .setInitialDelay(startTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                        .setInputData(data)
                        .build();

                WorkManager.getInstance(context).cancelAllWork();
                WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);

            }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                System.out.println("That was the last for today");
                calculateTheNextAlarm(currentTime);
            }


        }
    }

    public void calculateTheNextAlarm(Date startDate){

        // get the next date
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,1);
        c.set(Calendar.MILLISECOND,0);
        startDate = c.getTime();
        System.out.println("the date became -- > "+startDate);

        // get the max date
        Date endDate = new Date();
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        endCal.add(Calendar.DATE, 30);
        endCal.set(Calendar.HOUR_OF_DAY,0);
        endCal.set(Calendar.MINUTE,0);
        endCal.set(Calendar.SECOND,1);
        endCal.set(Calendar.MILLISECOND,0);
        endDate = endCal.getTime();

        if(startDate.before(endDate)){

            Date finalStartDate = startDate;

            Calendar calY = Calendar.getInstance();
            calY.setTime(finalStartDate);
            int year = calY.get(Calendar.YEAR);
            int month = calY.get(Calendar.MONTH);
            int day = calY.get(Calendar.DAY_OF_MONTH);


            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void run() {
                    AppDataBase appDataBase = AppDataBase.getInstance(MainActivity.getContext());
                    DatabaseFunctions databaseFunctions = appDataBase.databaseFunctions();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    List<MedicineReadyToShow> medicineReadyToShows1 = databaseFunctions.getTodayMedicines(dateFormat.format(finalStartDate), Home.getTheCurrentUser().getEmail());

                    if(medicineReadyToShows1!=null){

                        List<Date> dates = new ArrayList<>();
                        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                        Date dTime ;
                        Date date ;
                        Calendar cal = Calendar.getInstance();
                        Date currentTime = new Date();

                        // first we need to find the nearest time from now

                        int j  = 0 ;



                        for (MedicineReadyToShow i : medicineReadyToShows1) {
                            try {
                                System.out.println(medicineReadyToShows1.get(j).getTime());
                                System.out.println(medicineReadyToShows1.get(j).getDate());

                                dTime = formatter.parse(i.getTime());
                                cal.set(Calendar.HOUR_OF_DAY,dTime.getHours());
                                cal.set(Calendar.MINUTE,dTime.getMinutes());
                                cal.set(Calendar.SECOND,0);
                                cal.set(Calendar.MILLISECOND,0);
                                cal.set(Calendar.YEAR,year);
                                cal.set(Calendar.MONTH,month);
                                cal.set(Calendar.DAY_OF_MONTH,day);
                                date = cal.getTime();
                                dates.add(date);
                                j++;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        try {

                            MedicineReadyToShow medicineReadyToShow =
                                    medicineReadyToShows1.get(dates.indexOf(getNearestDate(dates,currentTime)));


                            // set the alarm to the medicine object

                            Data data = new Data.Builder()
                                    .putString("Medicine Name",medicineReadyToShow.getName())
                                    .putString("Pills ToTake",medicineReadyToShow.getPills_to_take())
                                    .putString("Time Per Day",medicineReadyToShow.getWhen())
                                    .putString("Date Of The Day",medicineReadyToShow.getDate())
                                    .putString("Time Of The Day",medicineReadyToShow.getTime())
                                    .putString("Instructions",medicineReadyToShow.getInstruction())
                                    .putString("Username",medicineReadyToShow.getUser_name())
                                    .build();

                            // get the time to set the alarm

                            SimpleDateFormat formatterX = new SimpleDateFormat("hh:mm");
                            Date dTimeX ;
                            Date dateX ;
                            Calendar calX = Calendar.getInstance();
                            long startTime = 0;
                            try {
                                dTimeX = formatterX.parse(medicineReadyToShow.getTime());
                                calX.set(Calendar.HOUR_OF_DAY,dTimeX.getHours());
                                calX.set(Calendar.MINUTE,dTimeX.getMinutes());
                                calX.set(Calendar.SECOND,0);
                                calX.set(Calendar.MILLISECOND,0);
                                calX.set(Calendar.YEAR,year);
                                calX.set(Calendar.MONTH,month);
                                calX.set(Calendar.DAY_OF_MONTH,day);
                                dateX = calX.getTime();
                                startTime = dateX.getTime();
                                System.out.println("we are here");
                                System.out.println(startTime);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            // set the alarm

                            OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManagerClass.class)
                                    .setInitialDelay(startTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                                    .setInputData(data)
                                    .build();

                            System.out.println(startTime - System.currentTimeMillis());


                            WorkManager.getInstance(context).cancelAllWork();
                            WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);

                            System.out.println("time is selected to be ----> ---------> "+ medicineReadyToShow.getTime());

                        }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                            System.out.println("That was the last for today");
                            calculateTheNextAlarm(finalStartDate);
                        }

                    }else {
                        System.out.println("out of range !");
                    }

                }
            }).start();
        }

    }

    public Date getNearestDate(List<Date> dates, Date currentDate) {
        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = date.getTime() - currentTime ;
            if (((minDiff == -1) || (diff < minDiff)) && diff>=0) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }

    private void openDialog() {
        MyCustomDialoge myCustomDialoge= new MyCustomDialoge(context);
        myCustomDialoge.show();
    }
}
