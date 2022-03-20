package com.example.medicalreminder.medicineslist.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.databinding.ActiveMedsRowBinding;

import java.util.List;

public class ActiveMedicationsAdapter extends RecyclerView.Adapter<ActiveMedicationsAdapter.ViewHolder>{
     Context context;
    private List<Medicine> meds;

    public ActiveMedicationsAdapter(Context context, List<Medicine> meds){
        Log.i("TAG", "MedsListAdapter");
        this.context = context;
        this.meds = meds;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("TAG", "onCreateViewHolder");
        return new ViewHolder(ActiveMedsRowBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder ");
        holder.binding.activeImgMed.setImageResource(R.drawable.pill);//done
        holder.binding.txtMedName.setText(meds.get(position).getMed_name());//done
        holder.binding.txtMedStrength.setText(meds.get(position).getStrength());//done
        holder.binding.txtForm.setText(meds.get(position).getMed_form());//done
        holder.binding.txtMedAmount.setText(String.valueOf(meds.get(position).getMed_amount()));
        holder.binding.txtMedSUnit.setText(meds.get(position).getS_Unit());
        holder.binding.txtMedLeft.setText("left");
        holder.binding.constraintLayoutActiveRow.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("GetMidicationsDetails", (Parcelable) meds.get(position));
            Navigation.findNavController(view);//put the direction of the display medicine fragment instade of this direction
                  //  .navigate(R.id.action_addMedsForTestingFrafment_to_addTakerFragment,bundle);
        });


    }

    @Override
    public int getItemCount() {
        return meds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ActiveMedsRowBinding binding;
        public ViewHolder(@NonNull ActiveMedsRowBinding activeMedsRowBinding) {
            super(activeMedsRowBinding.getRoot());
            binding = activeMedsRowBinding;
            Log.i("TAG", "ViewHolder");        }
    }
}
