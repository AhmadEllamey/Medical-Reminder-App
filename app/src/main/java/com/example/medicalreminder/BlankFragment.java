package com.example.medicalreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.home.view.Home;


public class BlankFragment extends Fragment {


    public BlankFragment() {
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
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        System.out.println("********************************************************************************************");
        System.out.println(getParentFragmentManager().findFragmentById(R.id.containerX));
        System.out.println(Home.getFragmentManagerX().findFragmentById(R.id.containerX));
        System.out.println(view.findViewById(R.id.containerX).getId());
        // load the first screen programmatically
        NavHostFragment navHostFragment = (NavHostFragment) getParentFragmentManager().findFragmentById(R.id.containerX);
        if(navHostFragment!=null){
            System.out.println("we are here !");
            NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.navig_graph);
            navGraph.setStartDestination(R.id.AddingMed);
            navController.setGraph(navGraph);
        }
        System.out.println("********************************************************************************************");

        return view;
    }
}