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
import android.widget.Button;
import android.widget.EditText;
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
import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.displaymedicin.DisplayPresenter.DisplayPresenter;
import com.example.medicalreminder.displaymedicin.DisplayPresenter.DisplayPresenterInterface;
import com.example.medicalreminder.editmedicin.EditView.Edit_View;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.model.MedicineReadyToShow;
import com.example.medicalreminder.home.view.home_fragment.view.HomeFragment;
import com.google.firebase.firestore.core.SyncEngine;

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
    Medicine medicine ;//= new Medicine();
    TextView medicinname;
    TextView Lasttaken;
    TextView Reminder;
    TextView Condition;
    Button btn;
    TextView Refill;
    ImageButton edit;
    ImageButton delete;
    int counter = 0;
    int new_dose=0;

    String username;
    String medName;

    DisplayPresenterInterface displayPresenterInterface = new DisplayPresenter(this);

    public Displaymed(MedicineReadyToShow medicineReadyToShow){
        username = medicineReadyToShow.getUser_name();
        medName = medicineReadyToShow.getName();
    }
    public Displaymed(Medicine medicine){
        username = medicine.getUser_name();
        medName = medicine.getMed_name();
        this.medicine = medicine;

    }



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.displaymedicin,container,false);
        image=view.findViewById(R.id.medicon);
        image.setClipToOutline(true);


        // get the medicine
        // we sent a request asking for the current medicine
//        Repo repo = new Repo(this);
//        repo.getMedicineFor(medName,username);

        displayPresenterInterface.SendRequest(medName,username);
        System.out.println("from view");
        medicinname=view.findViewById(R.id.MedNameDisplay);
        Lasttaken=view.findViewById(R.id.LastTaken);
        Reminder=view.findViewById(R.id.remind);
        Condition=view.findViewById(R.id.conditiondisplay);
        Refill=view.findViewById(R.id.Refilldisplay);
        btn = view.findViewById(R.id.suspend_id);

        System.out.println(medName);
        medicinname.setText(medName);



        // todo -- > set the on action click listeners only <<<

        //suspend
        view.findViewById(R.id.suspend_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(btn.getText().equals("Suspend")){
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
                    btn.setText("Activate");
                }else {
                    //set the end date with the  current date
                    Date c = Calendar.getInstance().getTime();
                    System.out.println("Current time => " + c);
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = df.format(c);
                    medicine.setStart_date(formattedDate);

                    medicine.setActive(true);
                    btn.setText("Suspend");
                    //send to preseneter to update the record
                    sendtopresenter( medicine);

                    Toast.makeText(getContext(), "Medicine is suspended", Toast.LENGTH_SHORT).show();


                }






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
                Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new Edit_View(medicine)).commit();


            }
        });


        //delete
        view.findViewById(R.id.deletebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletePresenter(medicine);
            }
        });


        //addDose
        view.findViewById(R.id.addDose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDoseDialog();


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



    private void setDoseDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        // ...Irrelevant code for customizing the buttons and title
        EditText Dose;
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.add_dose, null);
        Dose = dialogView.findViewById(R.id.DoseEdit);
//        Dose.setText(new_dose);

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new_dose = Integer.parseInt(Dose.getText().toString());
                medicine.setCount(new_dose);

                //send to presenter to update the record
                sendtopresenter(medicine);
            }

        });
        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });


        dialogBuilder.setView(dialogView);
//        Dose.setText(medicine.getCount());

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
        Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(),new HomeFragment()).commit();

    }

    @Override
    public void deletefailed() {
        Toast.makeText(getContext(), "Failed to delete it try again", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void iGotTheMed(Medicine medicine) {
        // todo -- > what to do after you got the medicine
        // like update the ui and other staff that depend on the medicine info

        //setting the component
         this.medicine = medicine;

          if(!medicine.getActive()){
              btn.setText("Activate");
          }


        medicinname.setText(medicine.getMed_name());
        Lasttaken.setText(medicine.getLast_time_taken());
        Condition.setText(medicine.getWhy_Taken());

        Refill.setText("remind you when "+medicine.getMed_left()+"pills  left");


        if(medicine.getHour_of_Morning()!= null){
            Reminder.append(medicine.getHour_of_Morning()+"\n");}
        if(medicine.getHour_of_Noon()!=null){
            Reminder.append(medicine.getHour_of_Noon()+"\n");}
        if(medicine.getHour_of_Evening()!=null) {
            Reminder.append(medicine.getHour_of_Evening() +"\n");
        } if( medicine.getHour_of_Night()!=null)        {
            Reminder.append(medicine.getHour_of_Night()+"\n");
        }



    }
}
