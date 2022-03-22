package com.example.medicalreminder;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    public MainActivity() {
    }

    private static Context context;

    private static String clearSharedPreferences = "null" ;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MainActivity.context = context;
    }

    public static String getClearSharedPreferences() {
        return clearSharedPreferences;
    }

    public static void setClearSharedPreferences(String clearSharedPreferences) {
        MainActivity.clearSharedPreferences = clearSharedPreferences;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        createNotificationChannel();

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
    public void onBackPressed() {
        //super.onBackPressed();

        // ToDO -- > handle what to do when back button pressed ...
    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name ="Medical Reminder Channel";
            String desc = "Channel for alarm";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Alarm",name,importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}