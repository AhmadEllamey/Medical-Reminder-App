package com.example.medicalreminder.addhealthtaker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addhealthtaker.RequestModel;
import com.example.medicalreminder.databinding.ActiveMedsRowBinding;
import com.example.medicalreminder.databinding.FragmentRequestsListBinding;
import com.example.medicalreminder.databinding.RequestRowBinding;
import com.example.medicalreminder.medicineslist.view.ActiveMedicationsAdapter;

import java.util.List;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {
    Context context;
    List<RequestModel> requestModelList;
    onDeclineClickListener declineClickListener;
    onAcceptClickListener acceptClickListener;
    public RequestListAdapter(Context context, List<RequestModel> requestModelList,onAcceptClickListener acceptClickListener,onDeclineClickListener declineClickListener){
        this.context=context;
        this.requestModelList=requestModelList;
        this.acceptClickListener=acceptClickListener;
        this.declineClickListener=declineClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RequestRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        RequestModel model = requestModelList.get(position);
        holder.binding.userImgRequestRow.setImageResource(R.drawable.usericon);
        holder.binding.txtUserName.setText(model.getSender_name());
        holder.binding.txtUserEmail.setText(model.getSender_email());
        holder.binding.txtRequestStatus.setText(model.getRequest_status());
        holder.binding.acceptRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.txtRequestStatus.setText("Accepted Request");
                acceptClickListener.onAcceptClick(model);
               holder.binding.acceptRequestBtn.setVisibility(View.GONE);
               holder.binding.declineRequestBtn.setVisibility(View.GONE);

            }
        });
        holder.binding.declineRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // declineClickListener.onDeclineClick(model);
                holder.binding.txtRequestStatus.setText("Declined Request");
                holder.binding.acceptRequestBtn.setVisibility(View.GONE);
               holder.binding.declineRequestBtn.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RequestRowBinding binding;
        public ViewHolder(@NonNull RequestRowBinding requestRowBinding) {
            super(requestRowBinding.getRoot());
            binding=requestRowBinding;
        }
    }
}
