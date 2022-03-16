package com.example.medicalreminder.displaymedicin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;

public class Displaymed extends Fragment {
    ImageView image;
    LayoutInflater inflater;
    Medicine medicine;
    TextView medicinname;
    TextView Lasttaken;
    TextView Reminder;
    TextView Condition;
    TextView Refill;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.displaymedicin,container,false);
        image=view.findViewById(R.id.medicon);
        image.setClipToOutline(true);
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                name.setText(value);
//                // Log.d(TAG, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//            }

        Bundle bundle = this.getArguments();
        medicine= (Medicine) bundle.getSerializable("obj");

        medicinname=view.findViewById(R.id.MedNameDisplay);
        Lasttaken=view.findViewById(R.id.LastTaken);
        Reminder=view.findViewById(R.id.remind);
        Condition=view.findViewById(R.id.conditiondisplay);
        Refill=view.findViewById(R.id.Refilldisplay);

        view.findViewById(R.id.suspend_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicine.setActive(false);
                Toast.makeText(getContext(), "Medicine is suspended", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.Refillbtn);

        medicinname.setText(medicine.getMed_name());
        Lasttaken.setText(medicine.getLast_time_taken());

        //if not null
        Reminder.setText((medicine.getHour_of_Morning()));

        Condition.setText(medicine.getWhy_Taken());

        Refill.setText("remind you when "+medicine.getMed_left()+"pills  left");
        return  view;
    }
}
