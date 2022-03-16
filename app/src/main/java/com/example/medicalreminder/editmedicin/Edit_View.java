//package com.example.medicalreminder.editmedicin;
//
//import android.app.TimePickerDialog;
//import android.icu.util.Calendar;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.example.medicalreminder.Model.Medicine;
//import com.example.medicalreminder.Model.Medicine;
//import com.example.medicalreminder.R;
//
//import java.sql.Time;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Edit_View extends Fragment {
//    Medicine medicine = new Medicine();
//
//    TextView First;
//    TextView Second;
//    TextView Third;
//    TextView Forth;
//
//    RadioButton everyday;
//    RadioButton specificdays;
//    RadioButton periodofdays;
//
//    TextView refill;
//    TextView medname;
//    TextView strenght;
//
//    Button save;
//    View popupView;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        View view = inflater.inflate(R.layout.edit_layout,container,false);
//        view.findViewById(R.id.savebtn).setOnClickListener(this::save);
//        medname=view.findViewById(R.id.mednameedit);
//        refill=view.findViewById(R.id.refill_edit);
//        strenght=view.findViewById(R.id.strenghtEdit);
//        First=view.findViewById(R.id.Morningtime);
//        First.setText(medicine.getHour_of_Morning());
//
//        view.findViewById(R.id.Morningtime).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//
//                TimePickerDialog mTimePicker;
//
//                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
//                        Time time = new Time(selectedHour, selectedMinute, 0);
//
//                        //little h uses 12 hour format and big H uses 24 hour format
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");
//
//                        //format takes in a Date, and Time is a sublcass of Date
//                        String s = simpleDateFormat.format(time);
//                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//                        First.setText(s);
//                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
//                        //edit_adapter.getnewdata(Times_Selected);
//                        //edit_adapter.notifyDataSetChanged();
//                    }
//
//                }, hour, minute, true);
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
////            Times_Selected.get(position).replace(Times_Selected.get(position));
//            }});
//        Second=view.findViewById(R.id.eveningtime);
//        Second.setText(medicine.getEvening());
//
//        view.findViewById(R.id.eveningtime).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//
//                TimePickerDialog mTimePicker;
//
//                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
//                        Time time = new Time(selectedHour, selectedMinute, 0);
//
//                        //little h uses 12 hour format and big H uses 24 hour format
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");
//
//                        //format takes in a Date, and Time is a sublcass of Date
//                        String s = simpleDateFormat.format(time);
//                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//                        Second.setText(s);
//                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
//                        //edit_adapter.getnewdata(Times_Selected);
//                        //edit_adapter.notifyDataSetChanged();
//                    }
//
//                }, hour, minute, true);
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
////            Times_Selected.get(position).replace(Times_Selected.get(position));
//            }});
//        Third=view.findViewById(R.id.nighttime);
//        Third.setText(medicine.getHour_of_Night());
//        view.findViewById(R.id.nighttime).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//
//                TimePickerDialog mTimePicker;
//
//                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
//                        Time time = new Time(selectedHour, selectedMinute, 0);
//
//                        //little h uses 12 hour format and big H uses 24 hour format
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");
//
//                        //format takes in a Date, and Time is a sublcass of Date
//                        String s = simpleDateFormat.format(time);
//                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//                        Third.setText(s);
//                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
//                        //edit_adapter.getnewdata(Times_Selected);
//                        //edit_adapter.notifyDataSetChanged();
//                    }
//
//                }, hour, minute, true);
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
////            Times_Selected.get(position).replace(Times_Selected.get(position));
//            }});
//
//        Forth=view.findViewById(R.id.noontime);
//        Forth.setText(medicine.getHour_of_Noon());
//        view.findViewById(R.id.noontime).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                Calendar mcurrentTime = Calendar.getInstance();
//                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//                int minute = mcurrentTime.get(Calendar.MINUTE);
//
//                TimePickerDialog mTimePicker;
//
//                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
//                        Time time = new Time(selectedHour, selectedMinute, 0);
//
//                        //little h uses 12 hour format and big H uses 24 hour format
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");
//
//                        //format takes in a Date, and Time is a sublcass of Date
//                        String s = simpleDateFormat.format(time);
//                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//                        Forth.setText(s);
//                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
//                        //edit_adapter.getnewdata(Times_Selected);
//                        //edit_adapter.notifyDataSetChanged();
//                    }
//
//                }, hour, minute, true);
//                mTimePicker.setTitle("Select Time");
//                mTimePicker.show();
////            Times_Selected.get(position).replace(Times_Selected.get(position));
//            }});
//
//        //test snipper
//        //  Edit Frequency --> SPINNER________________________________________________________________________________________________
//        List<String> spinnerArray =  new ArrayList<String>();
//        spinnerArray.add("once");
//        spinnerArray.add("Twice");
//        spinnerArray.add("Third");
//        spinnerArray.add("Forth");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, spinnerArray);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                if(position==0){
//
//                    First.setText("9:00");
//                    Second.setText("");
//                    Third.setText("");
//                    Forth.setText("");
//                    medicine.setHow_often("once");
//                }
//                else if (position == 1){
//                    First.setText("9:00");
//                    Second.setText("9:00");
//                    Third.setText("");
//                    Forth.setText("");
//                    medicine.setHow_often("Twice");
//
//                }
//                else  if (position==2){
//                    First.setText("9:00");
//                    Second.setText("9:00");
//                    Third.setText("9:00");
//                    Forth.setText("");
//                    medicine.setHow_often("Third");
//
//                }
//                else if (position==3){
//                    First.setText("9:00");
//                    Second.setText("9:00");
//                    Third.setText("9:00");
//                    Forth.setText("9:00");
//                    medicine.setHow_often("Forth");
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
//
//        //edit the schadule
//        everyday= view.findViewById(R.id.everydaycheckbox);
//        specificdays=view.findViewById(R.id.specificcheckbox);
//        periodofdays=view.findViewById(R.id.numberofdayscheck);
//
//        everyday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(everyday.isChecked()){
//                    System.out.println("okay");
//                    specificdays.setChecked(false);
//                    periodofdays.setChecked(false);
//                    medicine.setIs_Every_Day(true);
//
//                }
//            }
//        });
//        specificdays.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(specificdays.isChecked()){
//                    System.out.println("okay");
//                    everyday.setChecked(false);
//                    periodofdays.setChecked(false);
//                    medicine.setIs_Every_Day(false);
//
//                }
//            }
//        });
//        periodofdays.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(periodofdays.isChecked()){
//                    System.out.println("okay");
//                    specificdays.setChecked(false);
//                    everyday.setChecked(false);
//                    medicine.setIs_Every_Day(false);
////
////                    popupView= LayoutInflater.from(MainActivity.this).inflate(R.layout.popupcounter, null);
////                    PopupWindow popupWindow = new PopupWindow(popupView,WindowManager.LayoutParams.MATCH_PARENT ,200);
////                    popupWindow.setFocusable(true);
////                    // finally show up your popwindow
////                    popupWindow.showAsDropDown(popupView,0,0);
//                }
//            }
//        });
////
////        @Override
////        public void OnClick(int position, View view) {
////        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
////        Calendar mcurrentTime = Calendar.getInstance();
////        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
////        int minute = mcurrentTime.get(Calendar.MINUTE);
////
////        TimePickerDialog mTimePicker;
////
////        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
////            @Override
////            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
////                Toast.makeText(MainActivity.this, selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
////                Time time = new Time(selectedHour, selectedMinute, 0);
////
////                //little h uses 12 hour format and big H uses 24 hour format
////                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");
////
////                //format takes in a Date, and Time is a sublcass of Date
////                String s = simpleDateFormat.format(time);
////                Times_Selected.get(position).replace(Times_Selected.get(position),s);
////                edit_adapter.getnewdata(Times_Selected);
////                edit_adapter.notifyDataSetChanged();
////            }
////
////        }, hour, minute, true);
////        mTimePicker.setTitle("Select Time");
////        mTimePicker.show();
////        Times_Selected.get(position).replace(Times_Selected.get(position));
//        return view;
//    }
//    private void save(View view) {
//        medicine.setMed_name(medname.getText().toString());
//        medicine.setMed_left(Integer.parseInt(refill.getText().toString()));
//        medicine.setStrength(strenght.getText().toString());
//        medicine.setHour_of_Morning(First.getText().toString());
//        medicine.setHour_of_Evening(Second.getText().toString());
//        medicine.setHour_of_Noon(Third.getText().toString());
//        medicine.setHour_of_Night(Forth.getText().toString());
//        System.out.println(medicine.getMed_name());
//        System.out.println(medicine.getHow_often());
//        System.out.println(medicine.getHour_of_Evening());
//        System.out.println(medicine.getHour_of_Morning());
//        System.out.println(medicine.getHour_of_Noon());
//        System.out.println(medicine.getHour_of_Night());
//        System.out.println(medicine.getStrength());
//        System.out.println(medicine.getMed_left());
//        System.out.println(medicine.getIs_Every_Day());
//
//    }
//
//
//}
package com.example.medicalreminder.editmedicin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.addingmed.view.Generate_End_date;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Edit_View extends Fragment {

    List <String> Selecteddays= new ArrayList<>();
    Medicine medicine = new Medicine();
//  RecyclerView recyclerView;
//  Edit_Adapter edit_adapter;
//  List <String>Times_Selected= new ArrayList<>();

    TextView First;
    TextView Second;
    TextView Third;
    TextView Forth;

    RadioButton everyday;
    RadioButton specificdays;
    RadioButton periodofdays;

    TextView refill;
    TextView medname;
    TextView strenght;
    int counter = 0;
    TextView num_of_days;
    Button save;
    View popupView;

    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

//    DatabaseReference myRef;
//    FirebaseDatabase database;
    TextView unit;
    //after press add
    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view= inflater.inflate(R.layout.edit_layout,container,false);
//        setContentView(R.layout.edit_layout);
//send the times that the user selected

        //test
//        Times_Selected.add("12:30");
//        Times_Selected.add("14:30");
//        Times_Selected.add("20:30");

        //test
        // Edit the Time

        medicine.setMed_name("adol");
        medicine.setHow_often("Twice");
        medicine.setHour_of_Morning("12:30");
        medicine.setHour_of_Night("6:30");
        medicine.setHour_of_Evening("");
        medicine.setHour_of_Noon("");
        medicine.setIs_Every_Day(true);
        medicine.setMed_left(3);
        medicine.setStrength("35");
        medicine.setS_Unit("mg");
        unit=view.findViewById(R.id.spinner_unit);
        unit.setText(medicine.getS_Unit());

        view.findViewById(R.id.savebtn).setOnClickListener(this::save);

        medname=view.findViewById(R.id.mednameedit);
        refill=view.findViewById(R.id.refill_edit);
        strenght=view.findViewById(R.id.strenghtEdit);
        First=view.findViewById(R.id.Morningtime);
        First.setText(medicine.getHour_of_Morning());

        view.findViewById(R.id.Morningtime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
                        Time time = new Time(selectedHour, selectedMinute, 0);

                        //little h uses 12 hour format and big H uses 24 hour format
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");

                        //format takes in a Date, and Time is a sublcass of Date
                        String s = simpleDateFormat.format(time);
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        First.setText(s);
                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
                        //edit_adapter.getnewdata(Times_Selected);
                        //edit_adapter.notifyDataSetChanged();
                    }

                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
//            Times_Selected.get(position).replace(Times_Selected.get(position));
            }});
        Second=view.findViewById(R.id.eveningtime);
        Second.setText(medicine.getEvening());

        view.findViewById(R.id.eveningtime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
                        Time time = new Time(selectedHour, selectedMinute, 0);

                        //little h uses 12 hour format and big H uses 24 hour format
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");

                        //format takes in a Date, and Time is a sublcass of Date
                        String s = simpleDateFormat.format(time);
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        Second.setText(s);
                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
                        //edit_adapter.getnewdata(Times_Selected);
                        //edit_adapter.notifyDataSetChanged();
                    }

                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
//            Times_Selected.get(position).replace(Times_Selected.get(position));
            }});
        Third=view.findViewById(R.id.nighttime);
        Third.setText(medicine.getHour_of_Night());
        view.findViewById(R.id.nighttime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
                        Time time = new Time(selectedHour, selectedMinute, 0);

                        //little h uses 12 hour format and big H uses 24 hour format
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");

                        //format takes in a Date, and Time is a sublcass of Date
                        String s = simpleDateFormat.format(time);
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        Third.setText(s);
                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
                        //edit_adapter.getnewdata(Times_Selected);
                        //edit_adapter.notifyDataSetChanged();
                    }

                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
//            Times_Selected.get(position).replace(Times_Selected.get(position));
            }});

        Forth=view.findViewById(R.id.noontime);
        Forth.setText(medicine.getHour_of_Noon());
        view.findViewById(R.id.noontime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Toast.makeText(getContext(), selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
                        Time time = new Time(selectedHour, selectedMinute, 0);

                        //little h uses 12 hour format and big H uses 24 hour format
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");

                        //format takes in a Date, and Time is a sublcass of Date
                        String s = simpleDateFormat.format(time);
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        Forth.setText(s);
                        //Times_Selected.get(position).replace(Times_Selected.get(position),s);
                        //edit_adapter.getnewdata(Times_Selected);
                        //edit_adapter.notifyDataSetChanged();
                    }

                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
//            Times_Selected.get(position).replace(Times_Selected.get(position));
            }});
//        recyclerView=findViewById(R.id.Reminder_times);
//        edit_adapter= new Edit_Adapter(Times_Selected,this,this);
//
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(edit_adapter);

        // Write a message to the database

//        Medicine medicine = new Medicine();
//        medicine.setMed_name("adol");
//        medicine.setMed_form("pill");
//        medicine.setStrength("23");
//
//        //open the connection with firebase
//        database = FirebaseDatabase.getInstance();
//
//        //get the root of the database
//        myRef = database.getReference();
//
//        //myRef.setValue(name.getText().toString());
//
//        //adding gson
//        myRef.child(medicine.getMed_name()).setValue(medicine);
        /*
        name = findViewById(R.id.MedName);
        form=findViewById(R.id.Med);
        strength=findViewById(R.id.editTextTextPersonName3);

        btn= findViewById(R.id.savebtn);
        //readbtn= findViewById(R.id.readbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database
                medicine.setMed_name(name.getText().toString());
                medicine.setMed_form(form.getText().toString());
                medicine.setStrength(strength.getText().toString());

                //open the connection with firebase
                 database = FirebaseDatabase.getInstance();

                //get the root of the database
                myRef = database.getReference();

                //myRef.setValue(name.getText().toString());

                //adding gson
                myRef.child(medicine.getMed_name()).setValue(medicine);
            }
        });


         */
/*
        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        name.setText(value);
                       // Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });
        */
//        For navgraph of adding med
//        setContentView(R.layout.activity_main);

//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav);
//        if(navHostFragment != null){
//            NavController navController = navHostFragment.getNavController();
//            NavGraph navGraph=navController.getNavInflater().inflate(R.navigation.navig_graph);
//            navGraph.setStartDestination(R.id.AddingMed);
//            navController.setGraph(navGraph);
//        }

        //test snipper
        //  Edit Frequency --> SPINNER________________________________________________________________________________________________
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("once");
        spinnerArray.add("Twice");
        spinnerArray.add("Third");
        spinnerArray.add("Forth");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==0){

                    First.setText("9:00");
                    Second.setText(null);
                    Third.setText(null);
                    Forth.setText(null);
                    medicine.setHow_often("once");
                }
                else if (position == 1){
                    First.setText("9:00");
                    Second.setText("9:00");
                    Third.setText("");
                    Forth.setText("");
                    medicine.setHow_often("Twice");

                }
                else  if (position==2){
                    First.setText("9:00");
                    Second.setText("9:00");
                    Third.setText("9:00");
                    Forth.setText("");
                    medicine.setHow_often("Third");

                }
                else if (position==3){
                    First.setText("9:00");
                    Second.setText("9:00");
                    Third.setText("9:00");
                    Forth.setText("9:00");
                    medicine.setHow_often("Forth");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //edit the schadule
        everyday= view.findViewById(R.id.everydaycheckbox);
        specificdays=view.findViewById(R.id.specificcheckbox);
        periodofdays=view.findViewById(R.id.numberofdayscheck);

        everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(everyday.isChecked()){
                    System.out.println("okay");
                    specificdays.setChecked(false);
                    periodofdays.setChecked(false);
                    medicine.setFlag("Everyday");;
                    medicine.setStart_date(null);
                    medicine.setEnd_date(null);

                }
            }
        });
        specificdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(specificdays.isChecked()){
                    System.out.println("okay");
                    everyday.setChecked(false);
                    periodofdays.setChecked(false);
                    medicine.setFlag("Specific_Days");
                    medicine.setStart_date(null);
                    medicine.setEnd_date(null);
                    SetDialoge();


                }
            }
        });
        periodofdays.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(periodofdays.isChecked()){
                    specificdays.setChecked(false);
                    everyday.setChecked(false);
                    medicine.setFlag("period_of_days");
                    setperiodofdaysDilaoge();

//
//                    popupView= LayoutInflater.from(MainActivity.this).inflate(R.layout.popupcounter, null);
//                    PopupWindow popupWindow = new PopupWindow(popupView,WindowManager.LayoutParams.MATCH_PARENT ,500);
//                    num_of_days = popupView.findViewById(R.id.numberofdays);
//                    popupView.findViewById(R.id.Setbtn).setOnClickListener(new View.OnClickListener() {
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
//                            medicine.setStart_date(date);
//                            popupWindow.dismiss();
//
//                        }
//                    });
//                    popupView.findViewById(R.id.cancelbtn).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            popupWindow.dismiss();
//                            counter=0;
//                        }
//                    });
//                    popupView.findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (counter>=365) {
//                                Toast.makeText(MainActivity.this, "Set it everyday", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                counter ++;
//                            }
//                            num_of_days.setText(String.valueOf(counter));
//                        }
//                    });
//                    popupView.findViewById(R.id.mins).setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if(counter==1){
//                                counter=1;
//                                Toast.makeText(MainActivity.this, "Can not be less than 1 ", Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                            {
//                                counter--;
//                            }
//                            num_of_days.setText(String.valueOf(counter));
//                        }
//                    });
//
//
//                    popupWindow.setFocusable(true);
//                    popupWindow.setTouchable(true);
//                    popupWindow.setContentView(popupView);
//                    // finally show up your popwindow
//                    popupWindow.showAsDropDown(popupView);//,0,0);

                }
            }
        });
//
//        @Override
//        public void OnClick(int position, View view) {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
//        Calendar mcurrentTime = Calendar.getInstance();
//        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//        int minute = mcurrentTime.get(Calendar.MINUTE);
//
//        TimePickerDialog mTimePicker;
//
//        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                Toast.makeText(MainActivity.this, selectedHour + ":" + selectedMinute, Toast.LENGTH_SHORT).show();
//                Time time = new Time(selectedHour, selectedMinute, 0);
//
//                //little h uses 12 hour format and big H uses 24 hour format
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mma");
//
//                //format takes in a Date, and Time is a sublcass of Date
//                String s = simpleDateFormat.format(time);
//                Times_Selected.get(position).replace(Times_Selected.get(position),s);
//                edit_adapter.getnewdata(Times_Selected);
//                edit_adapter.notifyDataSetChanged();
//            }
//
//        }, hour, minute, true);
//        mTimePicker.setTitle("Select Time");
//        mTimePicker.show();
//        Times_Selected.get(position).replace(Times_Selected.get(position));
    return view;
    }

    private void setperiodofdaysDilaoge() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        // ...Irrelevant code for customizing the buttons and title
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.popupcounter, null);
        num_of_days = dialogView.findViewById(R.id.refilldisplay);
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
                LocalDateTime myDateObj = LocalDateTime.now();
                Toast.makeText(getContext(), num_of_days.getText().toString(), Toast.LENGTH_SHORT).show();
                counter=Integer.parseInt(num_of_days.getText().toString());
                if (Integer.parseInt(num_of_days.getText().toString())>=365) {
                    Toast.makeText(getContext(), "Set it everyday", Toast.LENGTH_SHORT).show();
                    medicine.setFlag("Everyday");
                }
//                System.out.println(Selecteddays);
                medicine.setStart_date(date);
            }
        });
        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });



        dialogView.findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit to textview.
//                            if (Integer.parseInt(num_of_days.getText().toString())>=365) {
//                                Toast.makeText(MainActivity.this, "Set it everyday", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
                counter ++;
                num_of_days.setText(counter);
//                            }
                num_of_days.setText(String.valueOf(counter));
            }
        });
        dialogView.findViewById(R.id.mins).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_of_days.setText(counter);
                if(Integer.parseInt(num_of_days.getText().toString())==1){
                    counter=1;
                    Toast.makeText(getContext(), "Can not be less than 1 ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    counter--;
                }
                num_of_days.setText(String.valueOf(counter));
            }
        });
        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void SetDialoge() {
        // initialise the list items for the alert dialog
        final String[] listItems = new String[]{"Saturday", "Sunday", "Monday", "Tuesday","Wednesday","Thursday","Friday"};
        final boolean[] checkedItems = new boolean[listItems.length];

        // copy the items from the main list to the selected item list
        // for the preview if the item is checked then only the item
        // should be displayed for the user
        final List<String> selectedItems = Arrays.asList(listItems);

        // handle the Open Alert Dialog button
//                bOpenAlertDialog.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {


        // initialise the alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // set the title for the alert dialog
        builder.setTitle("Choose Items");

        // now this is the function which sets the alert dialog for multiple item selection ready
        builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
                String currentItem = selectedItems.get(which);
            }
        });

        // alert dialog shouldn't be cancellable
        builder.setCancelable(false);

        // handle the positive button of the dialog
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
//                            tvSelectedItemsPreview.setText(tvSelectedItemsPreview.getText() + selectedItems.get(i) + ", ");
                        Selecteddays.add(listItems[i]);

                        Toast.makeText(getContext(),  selectedItems.get(i), Toast.LENGTH_SHORT).show();
                    }
                }
                System.out.println(Selecteddays);
            }
        });

        // handle the negative button of the alert dialog
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // handle the neutral button of the dialog to clear
        // the selected items boolean checkedItem
        builder.setNeutralButton("CLEAR ALL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < checkedItems.length; i++) {
                    checkedItems[i] = false;
                }
            }
        });
        // create the builder
        builder.create();
        // create the alert dialog with the
        // alert dialog builder instance
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private void save(View view) {
        medicine.setMed_name(medname.getText().toString());
        if(!refill.getText().toString().equals(""))
        {
            medicine.setMed_left(Integer.parseInt(refill.getText().toString()));
        }

        medicine.setStrength(strenght.getText().toString());
        medicine.setHour_of_Morning(First.getText().toString());
        medicine.setHour_of_Evening(Second.getText().toString());
        medicine.setHour_of_Noon(Third.getText().toString());
        medicine.setHour_of_Night(Forth.getText().toString());

        Generate_End_date generate_end_date= new Generate_End_date(date,counter);
        System.out.println(generate_end_date.getEnd_date());
        medicine.setEnd_date(generate_end_date.getEnd_date());
//        medicine.setStart_date();
        System.out.println(medicine.getMed_name());
        System.out.println(medicine.getHow_often());
        System.out.println(medicine.getHour_of_Evening());
        System.out.println(medicine.getHour_of_Morning());
        System.out.println(medicine.getHour_of_Noon());
        System.out.println(medicine.getHour_of_Night());
        System.out.println(medicine.getStrength());
        System.out.println(medicine.getMed_left());
        System.out.println(medicine.getFlag());

    }

    ///for last taken
    //LocalTime myObj = LocalTime.now();
//
//
//    private void showCustomPopupMenu()
//    {
//        windowManager2 = (WindowManager)getSystemService(WINDOW_SERVICE);
//        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view=layoutInflater.inflate(R.layout.xxact_copy_popupmenu, null);
//        params=new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                PixelFormat.TRANSLUCENT
//        );
//
//        params.gravity=Gravity.CENTER|Gravity.CENTER;
//        params.x=0;
//        params.y=0;
//        windowManager2.addView(view, params);
//    }

}