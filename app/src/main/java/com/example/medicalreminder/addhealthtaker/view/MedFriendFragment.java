package com.example.medicalreminder.addhealthtaker.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addhealthtaker.RequestModel;
import com.example.medicalreminder.databinding.FragmentMedFriendBinding;
import com.example.medicalreminder.databinding.FragmentRequestsListBinding;
import com.example.medicalreminder.home.view.Home;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MedFriendFragment extends Fragment {
    Context context;
    FragmentMedFriendBinding binding;
    MedFriendAdapter medFriendAdapter;
    FirebaseFirestore firestore;
    DocumentReference documentReference;
    List<RequestModel> requestModelList;



    public MedFriendFragment() {
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
        binding= FragmentMedFriendBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore=FirebaseFirestore.getInstance();
        requestModelList=new ArrayList<>();

        medFriendAdapter=new MedFriendAdapter(context,requestModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MedFriendFragment.this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.medFriendRecyclerView.setHasFixedSize(true);
        binding.medFriendRecyclerView.setLayoutManager(layoutManager);
        binding.medFriendRecyclerView.setAdapter(medFriendAdapter);
        documentReference=firestore
                .collection("Med_Friends")
                .document("MyFriends")
                .collection(Home.getTheCurrentUser().getEmail())
                .document();

        firestore.collection("Med_Friends")
                .document("MyFriends")
                .collection(Home.getTheCurrentUser().getEmail())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null) {
                        }
                        for(QueryDocumentSnapshot doc: value){
                            RequestModel model = new RequestModel();
                            model.setSender_email(doc.getString("sender_email"));
                            model.setSender_name(doc.getString("sender_name"));
                            model.setReceiever_email(doc.getString("reciver_email"));
                            model.setReciever_name(doc.getString("reciver_name"));
                            model.setRequest_status(doc.getString("request_status"));
                            requestModelList.add(model);
                            medFriendAdapter.notifyDataSetChanged();
                        }
                    }

                });
    }
}