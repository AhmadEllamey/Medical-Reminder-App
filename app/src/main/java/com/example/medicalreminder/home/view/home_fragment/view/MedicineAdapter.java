package com.example.medicalreminder.home.view.home_fragment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.home.view.home_fragment.model.Medicine;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {



    Context context ;
    static List<Medicine> medicines ;
    Communicator communicator;
    FragmentManager fragmentManager;


    public MedicineAdapter(Context context, List<Medicine> medicines, Communicator communicator, FragmentManager fragmentManager) {
        this.context = context;
        this.medicines = medicines;
        this.communicator = communicator;
        this.fragmentManager = fragmentManager;
    }

    public static void setMedicines(List<Medicine> medicines) {
        MedicineAdapter.medicines = medicines;
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

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {


        holder.medicineName.setText(medicines.get(position).getName());
        holder.medicineState.setText(medicines.get(position).getStates());
        holder.medicineAction.setText(medicines.get(position).getAction());
        holder.medicineTime.setText(medicines.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }
}
