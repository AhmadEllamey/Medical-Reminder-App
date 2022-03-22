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
package com.example.medicalreminder.editmedicin.EditView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.work.impl.background.systemalarm.SystemAlarmService;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.addingmed.presenter.AddingPresenter;
import com.example.medicalreminder.addingmed.presenter.AddingPresenterInterface;
import com.example.medicalreminder.addingmed.view.Generate_End_date;
import com.example.medicalreminder.addingmed.view.ViewInterface;
import com.example.medicalreminder.editmedicin.EditPresenter.EditPresenter;
import com.example.medicalreminder.editmedicin.EditPresenter.EditPresenterInterface;
import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.home.view.home_fragment.view.HomeFragment;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Edit_View extends Fragment  implements EditViewInterface {

    List<String> Selecteddays = new ArrayList<>();
    Medicine medicine;

    TextView First;
    TextView Second;
    TextView Third;
    TextView Forth;
    EditText refill;
    TextView medname;
    EditText strenght;
    TextView unit;
    TextView num_of_days;

    String unitt;
    RadioButton everyday;
    RadioButton specificdays;
    RadioButton periodofdays;
    ArrayList<String> hours = new ArrayList<>();
    int counter = 0;
    String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

    List<Integer> checked = new ArrayList<Integer>();

    public Edit_View(Medicine medicine) {
        this.medicine = medicine;
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_layout, container, false);

        // Edit the Time
        sethourslist();


        //saving the edit
        //update in the medicine info (AHMED) and firestore
        view.findViewById(R.id.savebtn).setOnClickListener(this::save);


        //display the items
        unit = view.findViewById(R.id.Unit);
        medname = view.findViewById(R.id.mednameedit);
        refill = view.findViewById(R.id.refill_edit);
        strenght = view.findViewById(R.id.strenghtEdit);
        medname.setText(medicine.getMed_name());
        unit.setText(medicine.getS_Unit());
        strenght.setText(medicine.getStrength());

        First = view.findViewById(R.id.First);
        view.findViewById(R.id.First).setOnClickListener(new View.OnClickListener() {
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");
//                        new SimpleDateFormat("h:mma");
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
            }
        });

        Second = view.findViewById(R.id.Second);
        view.findViewById(R.id.Second).setOnClickListener(new View.OnClickListener() {
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");

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
            }
        });

        Third = view.findViewById(R.id.Third);
        view.findViewById(R.id.Third).setOnClickListener(new View.OnClickListener() {
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");

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
            }
        });

        Forth = view.findViewById(R.id.Forth);
        view.findViewById(R.id.Forth).setOnClickListener(new View.OnClickListener() {
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
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm");

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
            }
        });

//_______________________________Set the time_____________________________________
        if (hours.size() >= 1)
            //show
            First.setText(hours.get(0));
            First.setVisibility(View.VISIBLE);

//        else {
//            First.setText("Set Alarm");
//        }
        if (hours.size() >= 2)
            Second.setText(hours.get(1));
            Second.setVisibility(View.VISIBLE);

//        else {
//            Second.setText("Set Alarm");
//        }
        if (hours.size() >= 3)
            Third.setText(hours.get(2));
            Third.setVisibility(View.VISIBLE);

//        else {
//            Third.setText("Set Alarm");
//        }
        if (hours.size() >= 4)
            Forth.setText(hours.get(3));
            Forth.setVisibility(View.VISIBLE);

//        else {
//            Forth.setText("Set Alarm");
//        }



//________________________________________Instruction_____________________________
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Before eating");
        spinnerArray.add("While eating");
        spinnerArray.add("After eating");
        spinnerArray.add("Dose not matter");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    medicine.setInstructions("Before eating");
                } else if (position == 1) {
                    medicine.setInstructions("While eating");
                } else if (position == 2) {
                    medicine.setInstructions("After eating");
                } else if (position == 3) {
                    medicine.setInstructions("Dose not matter");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //____________________SPINNER_Time__________________________________

        List<String> Frequency_Array =  new ArrayList<String>();
        Frequency_Array.add("once");
        Frequency_Array.add("Twice");
        Frequency_Array.add("Third");
        Frequency_Array.add("Forth");
        int FRQ=0;
        ArrayAdapter<String>  Frequency_Adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, Frequency_Array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner Frequency_Spinner = (Spinner) view.findViewById(R.id.spinner2);
        Frequency_Spinner.setAdapter(Frequency_Adapter);


        System.out.println(medicine.getHow_often());
        if(medicine.getHow_often()==null) FRQ=0;
       else if (medicine.getHow_often().equals("Twice daily")) FRQ=1;
       else if (medicine.getHow_often().equals("3 times a day")) FRQ=2;
       else  if(medicine.getHow_often().equals("4 times a day")) FRQ=3;
       else FRQ=0;


        Frequency_Spinner.setSelection(FRQ);

        Frequency_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Once frequency
                if(position==0) {
                    First.setVisibility(View.VISIBLE);

                    //hide them
                    Second.setVisibility(View.INVISIBLE);
                    Third.setVisibility(View.INVISIBLE);
                    Forth.setVisibility(View.INVISIBLE);

                    //reset text
                    Second.setText("Set Alarm");
                    Third.setText("Set Alarm");
                    Forth.setText("Set Alarm");


                    medicine.setHow_often("Once Daily");

                }
                //Twice frequency
                else if (position == 1){
                    First.setVisibility(View.VISIBLE);
                    Second.setVisibility(View.VISIBLE);

                    //hide them
                    Third.setVisibility(View.INVISIBLE);
                    Forth.setVisibility(View.INVISIBLE);

                    //reset text
                    Third.setText("Set Alarm");
                    Forth.setText("Set Alarm");


                    medicine.setHow_often("Twice daily");
                }

                //Third Frequency
                else  if (position==2){
                    First.setVisibility(View.VISIBLE);
                    Second.setVisibility(View.VISIBLE);
                    Third.setVisibility(View.VISIBLE);

                    //hide
                    Forth.setVisibility(View.INVISIBLE);

                    //set text
                    Forth.setText("Set Alarm");

                    medicine.setHow_often("3 times a day");
                }
                //Forth frequency
                else if (position==3){
                    First.setVisibility(View.VISIBLE);
                    Second.setVisibility(View.VISIBLE);
                    Third.setVisibility(View.VISIBLE);
                    Forth.setVisibility(View.VISIBLE);

                    medicine.setHow_often("4 times a day");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //edit the schadule


        //_______________handling the period of days _____________________
        everyday = view.findViewById(R.id.everydaycheckbox);
        specificdays = view.findViewById(R.id.specificcheckbox);
        periodofdays = view.findViewById(R.id.numberofdayscheck);
        everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (everyday.isChecked()) {
                    System.out.println("okay");
                    specificdays.setChecked(false);
                    periodofdays.setChecked(false);
                    medicine.setFlag("Everyday");
//                    medicine.setStart_date(date);
//                    medicine.setEnd_date(null);
                    setdaysfalse();


                }
            }
        });
        specificdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (specificdays.isChecked()) {
                    System.out.println("okay");
                    everyday.setChecked(false);
                    periodofdays.setChecked(false);
                    medicine.setFlag("Specific_Days");
//                    medicine.setStart_date(date);
//                    medicine.setEnd_date(null);
                    SetDialoge();


                }
            }
        });
        periodofdays.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (periodofdays.isChecked()) {
                    specificdays.setChecked(false);
                    everyday.setChecked(false);
                    medicine.setFlag("period_of_days");
                    setperiodofdaysDilaoge();
                    setdaysfalse();


                }
            }
        });

        return view;
    }

    private void sethourslist() {

        //the list will avoid the null and present the time in order

        if(medicine.getHour_of_Morning()!= null)
        hours.add(medicine.getHour_of_Morning());
        if(medicine.getHour_of_Evening()!= null)
        hours.add(medicine.getHour_of_Evening());
        if(medicine.getHour_of_Night()!= null )
        hours.add(medicine.getHour_of_Night());
        if(medicine.getHour_of_Noon()!= null)
        hours.add(medicine.getHour_of_Noon());
    }

    private void setdaysfalse() {
        medicine.setSunday(false);
        medicine.setSaturday(false);
        medicine.setMonday(false);
        medicine.setThursday(false);
        medicine.setTuesday(false);
        medicine.setWedensday(false);
        medicine.setFriday(false);
    }

    private void putcontent() {
        medname.setText(medicine.getMed_name());
        refill.setText(medicine.getMed_left());
        strenght.setText(medicine.getStrength());

    }

    private void setperiodofdaysDilaoge() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        // ...Irrelevant code for customizing the buttons and title
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.popupcounter, null);
        num_of_days = dialogView.findViewById(R.id.refilldisplay);


        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LocalDateTime myDateObj = LocalDateTime.now();
                Toast.makeText(getContext(), num_of_days.getText().toString(), Toast.LENGTH_SHORT).show();
                counter = Integer.parseInt(num_of_days.getText().toString());
                if (Integer.parseInt(num_of_days.getText().toString()) >= 365) {
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
                counter++;
//                num_of_days.setText(counter);
//                            }
                num_of_days.setText(String.valueOf(counter));
            }
        });
        dialogView.findViewById(R.id.mins).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_of_days.setText(counter);
                if (Integer.parseInt(num_of_days.getText().toString()) == 1) {
                    counter = 1;
                    Toast.makeText(getContext(), "Can not be less than 1 ", Toast.LENGTH_SHORT).show();
                } else {
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
        final String[] listItems = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        final boolean[] checkedItems = new boolean[listItems.length];

        // copy the items from the main list to the selected item list
        // for the preview if the item is checked then only the item
        // should be displayed for the user
        final List<String> selectedItems = Arrays.asList(listItems);


        // handle the Open Alert Dialog button


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
                        Selecteddays.add(listItems[i]);
                        checked.add(i);
                        Toast.makeText(getContext(), selectedItems.get(i), Toast.LENGTH_SHORT).show();
                    }
                }

                System.out.println(Selecteddays);
                System.out.println(checked);
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

        //saving
        EditPresenterInterface editpresenterInterface = new EditPresenter(this);


        medicine.setMed_name(medname.getText().toString());
        if (!refill.getText().toString().equals("")) {
            medicine.setMed_left(Integer.parseInt(refill.getText().toString()));
        }

        medicine.setStrength(strenght.getText().toString());


        //____________________________Set_Hour___________________________________

        if (!First.getText().equals("Set Alarm"))
            medicine.setHour_of_Noon(First.getText().toString());
        else {
            medicine.setHour_of_Noon(null); }

        //_______________________________________
        if (!Second.getText().equals("Set Alarm")){
            medicine.setHour_of_Night(Second.getText().toString());
        } else {
            medicine.setHour_of_Night(null);
        }
        //------------------------------------------

        if (!Third.getText().equals("Set Alarm")) {medicine.setHour_of_Morning(Third.getText().toString());
        } else {
            medicine.setHour_of_Morning(null);
        }


        //------------------------------------------------
        if (!Forth.getText().equals("Set Alarm")) medicine.setHour_of_Evening(Forth.getText().toString());
        else {
            medicine.setHour_of_Evening(null);
        }

        //-----------------------------------------------
        System.out.println("1" + medicine.getHour_of_Noon());
        System.out.println("2" + medicine.getHour_of_Night());
        System.out.println("3" + medicine.getHour_of_Morning());
        System.out.println("4" + medicine.getHour_of_Evening());


        //-------------------- to generate the end_date-----------------------------
        Generate_End_date generate_end_date = new Generate_End_date(date, counter);
        medicine.setEnd_date(generate_end_date.getEnd_date());
        if (!medicine.getFlag().equals("Everyday")) {
            for (int i = 0; i < checked.size(); i++) {
                System.out.println(checked.size());
                if (checked.get(i) == 0) medicine.setSaturday(true);
                if (checked.get(i) == 1) medicine.setSunday(true);
                if (checked.get(i) == 2) medicine.setMonday(true);
                if (checked.get(i) == 3) medicine.setTuesday(true);
                if (checked.get(i) == 4) medicine.setWedensday(true);
                if (checked.get(i) == 5) medicine.setThursday(true);
                if (checked.get(i) == 6) medicine.setFriday(true);

            }
        }


        //passing medicine to Edit Presenter
        editpresenterInterface.update(medicine);

        //navigate to home
        Home.getFragmentManagerX().beginTransaction().replace(Home.getFrameLayout().getId(), new HomeFragment()).commit();


    }

    @Override
    public void ShowToast() {
//        Toast.makeText(getContext(), "Edit Successfully", Toast.LENGTH_SHORT).show();
    }
}