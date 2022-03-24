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
import android.widget.Toast;

import com.example.medicalreminder.addhealthtaker.RequestModel;
import com.example.medicalreminder.databinding.FragmentRequestsListBinding;
import com.example.medicalreminder.home.view.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class RequestsListFragment extends Fragment implements onAcceptClickListener,onDeclineClickListener{
    Context context;
    FragmentRequestsListBinding binding;
    RequestListAdapter requestListAdapter;
    FirebaseFirestore firestore;
    DocumentReference documentReference;
    List<RequestModel> requestModelList;

    public RequestsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentRequestsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firestore=FirebaseFirestore.getInstance();

        requestModelList=new ArrayList<>();

        requestListAdapter=new RequestListAdapter(context,requestModelList,this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(RequestsListFragment.this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.RequestListRecyclerView.setHasFixedSize(true);
        binding.RequestListRecyclerView.setLayoutManager(layoutManager);
        binding.RequestListRecyclerView.setAdapter(requestListAdapter);

        documentReference=firestore
                .collection("Requests")
                .document("RecieverRequests")
                .collection(Home.getTheCurrentUser().getEmail())
                .document();

        firestore.collection("Requests")
                .document("RecieverRequests")
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
                      requestListAdapter.notifyDataSetChanged();
                    }
                }

        });
    }




    public void saveAsMedFreiend(RequestModel model) {
        documentReference=firestore
                .collection("Med_Friends")
                .document("MyFriends")
                .collection(model.getSender_email())
                .document(model.getSender_name());

        firestore.collection("MedFriends")
                .document("MyFriends")
                .collection(model.getSender_email())
                .document(model.getSender_name())
                .set(model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Medfriend Added Successfully", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Fail To Add This Medfriend", Toast.LENGTH_SHORT).show();

            }
        });

        firestore.collection("MedFriends")
                .document("MyFriends")
                .collection(Home.getTheCurrentUser().getEmail())
                .document(model.getSender_email())
                .set(model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "recieverMedfriendadded_added", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "fail to add reciever medfriend", Toast.LENGTH_SHORT).show();

            }
        });


    }



    @Override
    public void onAcceptClick(RequestModel model) {

        saveAsMedFreiend(model);
    }

    @Override
    public void onDeclineClick(RequestModel model) {
    }
}