package com.example.medicalreminder.displaymedicin;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
    int counter = 0;

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

        view.findViewById(R.id.Refillbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setperiodofdaysDilaoge();

            }
        });

        medicinname.setText(medicine.getMed_name());
        Lasttaken.setText(medicine.getLast_time_taken());

        //if not null
        Reminder.setText((medicine.getHour_of_Morning()));

        Condition.setText(medicine.getWhy_Taken());

        Refill.setText("remind you when "+medicine.getMed_left()+"pills  left");
        return  view;
    }

    private void setperiodofdaysDilaoge() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        // ...Irrelevant code for customizing the buttons and title
        TextView num_refill;
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.getrefill, null);
        num_refill = dialogView.findViewById(R.id.refilldisplay);
//        dialogView.findViewById(R.id.Setbtn).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            LocalDateTime myDateObj = LocalDateTime.now();
////                    LocaleData mydate = LocaleData.getInstance();
////                    System.out.println(mydate);
//                            Date date2 = new Date();
//                            date2.getDate();
//                            //System.out.println(myDateObj);
//                            System.out.println("DATE"+date2);
//
//                            //System.out.println(date);
//                            medicine.setStart_date(date); }});
//        dialogView.findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            counter=0;
//                        }
//                    });

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                counter=Integer.parseInt(num_refill.getText().toString());
                medicine.setMed_amount(counter);
              }
        });
        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
