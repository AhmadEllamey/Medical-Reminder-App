package com.example.medicalreminder.addhealthtaker.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addhealthtaker.RequestModel;
import com.example.medicalreminder.databinding.FragmentAddHealthTakerBinding;
import com.example.medicalreminder.home.view.Home;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddHealthTakerFragment extends Fragment {
    Context context;
   FragmentAddHealthTakerBinding binding;
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore firestore;
    DocumentReference documentReference;
    RequestModel receiverReqeut,senderRequest;

    public AddHealthTakerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    binding=FragmentAddHealthTakerBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.requestListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(view).navigate(R.id.action_addHealthTakerFragment_to_requestListFragment,bundle);
            }
        });
        binding.medfriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                 Navigation.findNavController(view).navigate(R.id.action_addHealthTakerFragment_to_medFriendFragment,bundle);
            }});

        binding.sendRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore=FirebaseFirestore.getInstance();
                if(!binding.etUserEmail.getText().toString().equals("") && !binding.etUserName.getText().toString().equals("")){

                    senderRequest=new RequestModel(Home.getTheCurrentUser().getEmail(), Home.getTheCurrentUser().getName(), binding.etUserEmail.getText().toString(),binding.etUserName.getText().toString(),"Pinding");
                    receiverReqeut=new RequestModel(Home.getTheCurrentUser().getEmail(), Home.getTheCurrentUser().getName(), binding.etUserEmail.getText().toString(),binding.etUserName.getText().toString(),"Pinding");


                    documentReference=firestore.collection("Requests")
                            .document("SenderRequests")
                            .collection(Home.getTheCurrentUser().getEmail())
                            .document();

                    documentReference=firestore
                            .collection("Requests")
                            .document("RecieverRequests")
                            .collection(Home.getTheCurrentUser().getEmail())
                            .document();

                    //using

                    firestore.collection("Requests")
                            .document("SenderRequests")
                            .collection(Home.getTheCurrentUser().getEmail())
                            .document()
                            .set(senderRequest)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getContext(), "Request Sent Successfully ", Toast.LENGTH_SHORT).show();
                                }
                            });


                    firestore.collection("Requests")
                            .document("RecieverRequests")
                            .collection(binding.etUserEmail.getText().toString())
                            .document(binding.etUserName.getText().toString())
                            .set(receiverReqeut);//fields&values

                }
                else{
                    Toast.makeText(context, "Name and Email are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}