package com.example.medicalreminder.home.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.example.medicalreminder.BlankFragment;
import com.example.medicalreminder.R;
import com.example.medicalreminder.home.view.home_fragment.view.Communicator;
import com.example.medicalreminder.home.view.home_fragment.view.HomeFragment;
import com.example.medicalreminder.home.view.profile_fragment.view.ProfileFragment;
import com.example.medicalreminder.login.model.User;
import com.example.medicalreminder.medicineslist.view.MedicationsListFragment;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , Communicator {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView ;
    User user;
    static User userX;
    static FrameLayout frameLayout ;


    public static FragmentManager fragmentManager;




    static Context context ;


    public static FrameLayout getFrameLayout(){
        return frameLayout ;
    }

    public static Context getContext() {
        return context;
    }

    public static FragmentManager getFragmentManagerX() {
        return fragmentManager;
    }

    public static User getTheCurrentUser(){
        return userX;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;

        fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        userX = user;
        frameLayout = findViewById(R.id.container);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        navigationView = findViewById(R.id.menuItems);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(user.getName());

        navigationView.setNavigationItemSelectedListener(this);

        // load the data (that the start date is before the current day plus 20 days ) from the fireStore to the local room



        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),new HomeFragment()).commit();


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.nav_home){
            Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),new HomeFragment()).commit();
        }else if(item.getItemId() == R.id.nav_profile){
            Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),new ProfileFragment()).commit();
        }else if(item.getItemId() == R.id.nav_add_medicine){
            Toast.makeText(this,"Add medicine",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),new BlankFragment()).commit();
        }else if(item.getItemId() == R.id.nav_edit_medicine){
            Toast.makeText(this,"Show Medicines",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),new MedicationsListFragment()).commit();
        }else if(item.getItemId() == R.id.nav_add_health_takers){
            Toast.makeText(this,"Add health takers",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),null).commit();
        }else if(item.getItemId() == R.id.nav_manage_health_takers){
            Toast.makeText(this,"Manage health takers",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),null).commit();
        }else if(item.getItemId() == R.id.nav_sittings){
            Toast.makeText(this,"Sittings",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),null).commit();
        }else if(item.getItemId() == R.id.nav_log_out){
            Toast.makeText(this,"Log Out",Toast.LENGTH_SHORT).show();

            // todo >>> here the steps of logging out of the account and go back to the login screen
            getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),null).commit();
        }

        navigationView.setCheckedItem(item.getItemId());

        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }



}