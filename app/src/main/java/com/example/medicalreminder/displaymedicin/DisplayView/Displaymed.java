package com.example.medicalreminder.displaymedicin.DisplayView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.displaymedicin.DisplayPresenter.DisplayPresenter;
import com.example.medicalreminder.displaymedicin.DisplayPresenter.DisplayPresenterInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/*
 * Missing the   addDose
 *
 * */

/*
 * get argument by the previous fragment of medicine with key "obj"
 *
 *  */
public class Displaymed extends Fragment implements  DisplayInterface{
    ImageView image;
    LayoutInflater inflater;
    Medicine medicine;
    TextView medicinname;
    TextView Lasttaken;
    TextView Reminder;
    TextView Condition;
    TextView Refill;
    ImageButton edit;
    ImageButton delete;
    int counter = 0;
    DisplayPresenterInterface displayPresenterInterface;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.displaymedicin,container,false);
        image=view.findViewById(R.id.medicon);
        image.setClipToOutline(true);


        Bundle bundle = this.getArguments();
        medicine= (Medicine) bundle.getSerializable("obj");

        medicinname=view.findViewById(R.id.MedNameDisplay);
        Lasttaken=view.findViewById(R.id.LastTaken);
        Reminder=view.findViewById(R.id.remind);
        Condition=view.findViewById(R.id.conditiondisplay);
        Refill=view.findViewById(R.id.Refilldisplay);


        //setting the component

        medicinname.setText(medicine.getMed_name());
        Lasttaken.setText(medicine.getLast_time_taken());
        Condition.setText(medicine.getWhy_Taken());

        Refill.setText("remind you when "+medicine.getMed_left()+"pills  left");

        //if not null
        Reminder.setText(medicine.getHour_of_Morning() + medicine.getHour_of_Night()
        +medicine.getHour_of_Noon()+medicine.getHour_of_Evening()
        );


        //suspend
        view.findViewById(R.id.suspend_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                medicine.setActive(false);

                //set the end date with the  current date
                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                medicine.setEnd_date(formattedDate);

                //send to preseneter to update the record
                sendtopresenter( medicine);

                Toast.makeText(getContext(), "Medicine is suspended", Toast.LENGTH_SHORT).show();
            }
        });


        //refill
        view.findViewById(R.id.Refillbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRefillDialog();


                //send to preseneter to update the record
                sendtopresenter( medicine);
            }
        });


        //edit
        view.findViewById(R.id.editbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // moving to edit fragment

            }
        });


        //delete
        view.findViewById(R.id.deletebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletePresenter(medicine);
            }
        });

        return  view;
    }

    private void deletePresenter(Medicine medicine) {
        displayPresenterInterface = new DisplayPresenter(this);
        displayPresenterInterface.delete(medicine);
    }

    private void setRefillDialog() {
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
                //do nothing
            }
        });


        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private  void sendtopresenter(Medicine medicine){
        displayPresenterInterface = new DisplayPresenter(this);
        displayPresenterInterface.update(medicine);
        ShowToast();

    }

    @Override
    public void ShowToast() {
        Toast.makeText(getContext(), "successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deletesuccess() {
        Toast.makeText(getContext(), "deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deletefailed() {
        Toast.makeText(getContext(), "Failed to delete it try again", Toast.LENGTH_SHORT).show();

    }
}
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