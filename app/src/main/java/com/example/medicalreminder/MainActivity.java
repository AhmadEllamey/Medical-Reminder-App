package com.example.medicalreminder;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.medicalreminder.Model.Medicine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import static android.content.ContentValues.TAG;


public class MainActivity extends AppCompatActivity {


    public MainActivity() {
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





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