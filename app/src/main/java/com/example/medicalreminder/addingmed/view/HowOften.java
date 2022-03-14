package com.example.medicalreminder.addingmed.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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

public class HowOften extends Fragment {
    private static final String TAG ="tag" ;
    Button other;
    NavController navController;
    String count;
    Medicine medicine;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view= inflater.inflate(R.layout.how_often,container,false);

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            medicine= (Medicine) bundle.getSerializable("obj");
            System.out.println(medicine.getMed_name()+"\n"+
                    medicine.getMed_form()+"\n"+
                    medicine.getStrength()+"\n"+
                    medicine.getWhy_Taken()+"\n"+
                    medicine.getIs_Every_Day());
        }

        view.findViewById(R.id.once).setOnClickListener(this::gonext);
        view.findViewById(R.id.twice).setOnClickListener(this::gonext);
        view.findViewById(R.id.there).setOnClickListener(this::gonext);
        view.findViewById(R.id.four).setOnClickListener(this::gonext);
        view.findViewById(R.id.sixhours).setOnClickListener(this::gonext);


        return  view;
    }

    @SuppressLint("ResourceType")
    private void gonext(View view) {
        Button holder = view.findViewById(view.getId());
        count= holder.getText().toString();
        medicine.setHow_often(count);
        int counter = 0;
        if( holder.getId() == R.id.once ) counter=1;
        if( holder.getId() ==  R.id.twice) counter=2;
        if( holder.getId() == R.id.there ) counter=3;
        if( holder.getId() == R.id.four) counter=4;
        //if( holder.getId() ==2131231164 ) ;  //6 hours

        Bundle Sendbundle = new Bundle();
        System.out.println("the counter sended as "+counter);
        Sendbundle.putInt("count",counter);

        Sendbundle.putSerializable("obj",medicine);

        navController = Navigation.findNavController(view);
        NavDirections navDirections = com.example.medicalreminder.addingmed.view.HowOftenDirections.actionHowOftenInDayToTimeInDay();
        navController.navigate(R.id.Time_In_Day_fragment,Sendbundle);

    }
}
