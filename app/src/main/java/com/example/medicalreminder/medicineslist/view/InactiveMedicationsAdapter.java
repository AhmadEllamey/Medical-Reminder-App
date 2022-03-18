package com.example.medicalreminder.medicineslist.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.InactiveMedsRowBinding;

import java.util.List;

public class InactiveMedicationsAdapter extends RecyclerView.Adapter<InactiveMedicationsAdapter.ViewHolder> {
    final private Context context;
    private List<Medicine> medicines;


    public InactiveMedicationsAdapter(Context context, List<Medicine> medicines) {
        Log.i("TAG", "InactiveListAdapter");
        this.context = context;
        this.medicines = medicines;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("TAG", "onCreateViewHolder");
        return new ViewHolder(InactiveMedsRowBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder ");
        holder.binding.inactiveImgMed.setImageResource(R.drawable.pill);
        holder.binding.txtInactiveMedName.setText(medicines.get(position).getMed_name());
        holder.binding.txtInactiveMedStrength.setText(medicines.get(position).getStrength());
        holder.binding.txtInactiveMedAmount.setText(String.valueOf(medicines.get(position).getMed_amount()));
        holder.binding.txtInactiveMedForm.setText(medicines.get(position).getMed_form());
        holder.binding.txtMedLeft.setText("left");
        holder.binding.constraintLayoutInactiveRow.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
           // bundle.putParcelable("Inactive med", (Parcelable) medicines.get(position));
            Navigation.findNavController(view);//put the direction of the display medicine fragment instade of this direction
                   // .navigate(R.id.action_fragment_medication_list_to_displayMedicationDrug,bundle);
        });
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        InactiveMedsRowBinding binding;
        public ViewHolder(InactiveMedsRowBinding inactiveMedsRowBinding) {
            super(inactiveMedsRowBinding.getRoot());
            Log.i("TAG", "ViewHolder");
            binding = inactiveMedsRowBinding;
        }
    }
}
