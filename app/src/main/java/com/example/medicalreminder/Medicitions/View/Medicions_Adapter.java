package com.example.medicalreminder.Medicitions.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.displaymedicin.DisplayView.Displaymed;
import com.example.medicalreminder.editmedicin.EditView.Edit_View;
import com.example.medicalreminder.home.view.Home;

import java.util.List;

public class Medicions_Adapter extends RecyclerView.Adapter<Medicions_Adapter.EditHandler> {
    List<Medicine> Medicines;
    Context context;

    public Medicions_Adapter(List<Medicine> Medicines, Context context){
        this.context=context;
        this.Medicines=Medicines;

    }

    class EditHandler extends RecyclerView.ViewHolder{
        //data of custom Row
        View row;
        TextView med_Name;
        TextView strength;
        TextView med_Amount;

        public EditHandler(@NonNull View convertView) {
            super(convertView);
            row=convertView;
            med_Name=row.findViewById(R.id.Med_Name_id);
            med_Amount=row.findViewById(R.id.Med_amount_id);
            strength=row.findViewById(R.id.Strenght_id);
        }
    }

    @NonNull
    @Override

    //Create ViewHolder
    public EditHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        //parse xml custom row to view ==> so Adapter can deal withit
        View view = inflater.inflate(R.layout.mediction_row,parent , false);

        EditHandler viewHolder= new EditHandler(view);

        return viewHolder;
    }

    //BindData replace data on the views according to position
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EditHandler holder, @SuppressLint("RecyclerView") int position) {
        holder.med_Name.setText(Medicines.get(position).getMed_name());
        holder.med_Amount.setText(Medicines.get(position).getMed_amount()+ " Pills Left");
        holder.strength.setText(Medicines.get(position).getStrength() +" " +Medicines.get(position).getS_Unit());
        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new Displaymed(Medicines.get(position))).commit();

            }
        });
    }

    //items display
    @Override
    public int getItemCount() {
        return Medicines.size();
    }

    public void setMedicines(List<Medicine> medicines) {
        Medicines = medicines;
    }
}
