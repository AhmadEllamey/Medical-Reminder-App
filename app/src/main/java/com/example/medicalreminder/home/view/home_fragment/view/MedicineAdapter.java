package com.example.medicalreminder.home.view.home_fragment.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.displaymedicin.DisplayView.Displaymed;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.home.view.profile_fragment.view.ProfileFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {



    Context context ;
    static List<MedicineReadyToShow> medicineReadyToShows;
    Communicator communicator;
    FragmentManager fragmentManager;


    public MedicineAdapter(Context context, List<MedicineReadyToShow> medicineReadyToShows, Communicator communicator, FragmentManager fragmentManager) {
        this.context = context;
        this.medicineReadyToShows = medicineReadyToShows;
        this.communicator = communicator;
        this.fragmentManager = fragmentManager;
    }

    public static void setMedicineReadyToShows(List<MedicineReadyToShow> medicineReadyToShows) {
        MedicineAdapter.medicineReadyToShows = medicineReadyToShows;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView medicineName ;
        public TextView medicineState ;
        public TextView medicineAction;
        public TextView medicineTime;
        public ConstraintLayout constraintLayout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineName = itemView.findViewById(R.id.nameText);
            medicineState = itemView.findViewById(R.id.statesText);
            medicineAction = itemView.findViewById(R.id.actionText);
            medicineTime = itemView.findViewById(R.id.timeText);
            constraintLayout = itemView.findViewById(R.id.row);
        }

    }



    @NonNull
    @Override
    public MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.single_row,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {


        holder.medicineName.setText(medicineReadyToShows.get(position).getName());

        String state = "Waiting" ;
        Date currentDate = new Date();
        Date currentMedicineDate = new Date();
        try {
            currentMedicineDate = new SimpleDateFormat("dd/MM/yyyy").parse(medicineReadyToShows.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(currentMedicineDate.before(currentDate)){
            state = "Done";
        }
        holder.medicineState.setText(state);
        String active = "Active" ;
        if(medicineReadyToShows.get(position).getAction().equals("false")){
            active = "Deactivated" ;
        }
        holder.medicineAction.setText(active);





        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dTime = null;
        try {
            dTime = formatter.parse(medicineReadyToShows.get(position).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calY = Calendar.getInstance();
        calY.setTime(currentMedicineDate);
        calY.set(Calendar.HOUR_OF_DAY,dTime.getHours());
        calY.set(Calendar.MINUTE,dTime.getMinutes());
        calY.set(Calendar.SECOND,0);
        calY.set(Calendar.MILLISECOND,0);

        Date dateToShow = calY.getTime();

        String timeToShow = dateToShow +" "+medicineReadyToShows.get(position).getWhen();

        holder.medicineTime.setText(timeToShow);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new Displaymed(medicineReadyToShows.get(holder.getAdapterPosition()))).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return medicineReadyToShows.size();
    }
}
