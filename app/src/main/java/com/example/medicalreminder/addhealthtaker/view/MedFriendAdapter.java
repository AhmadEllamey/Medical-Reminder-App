package com.example.medicalreminder.addhealthtaker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addhealthtaker.RequestModel;
import com.example.medicalreminder.databinding.MedFriendRowBinding;
import java.util.List;

public class MedFriendAdapter  extends RecyclerView.Adapter<MedFriendAdapter.ViewHolder> {
    Context context;
    List<RequestModel> requestModelList;
    public MedFriendAdapter(Context context, List<RequestModel> requestModelList){
        this.context=context;
        this.requestModelList=requestModelList;
    }
    @NonNull
    @Override
    public MedFriendAdapter. ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedFriendAdapter.ViewHolder(MedFriendRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedFriendAdapter.ViewHolder holder, int position) {
        RequestModel model = requestModelList.get(position);
        holder.binding.userImgMedFriendRow.setImageResource(R.drawable.medfriend);
        holder.binding.txtUserEmail.setText(model.getReceiever_email());
        holder.binding.txtUserName.setText(model.getReciever_name());
        /*holder.binding.constraintLayoutMedFriendRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MedFriendRowBinding binding;
        public ViewHolder(@NonNull MedFriendRowBinding medFriendRowBinding) {
            super(medFriendRowBinding.getRoot());
            binding=medFriendRowBinding;
        }
    }
}
