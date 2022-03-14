package com.example.medicalreminder.editmedicin;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Edit_View extends Fragment {
    Medicine medicine = new Medicine();

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

    Button save;
    View popupView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.edit_layout,container,false);
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
                    Second.setText("");
                    Third.setText("");
                    Forth.setText("");
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
                    medicine.setIs_Every_Day(true);

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
                    medicine.setIs_Every_Day(false);

                }
            }
        });
        periodofdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(periodofdays.isChecked()){
                    System.out.println("okay");
                    specificdays.setChecked(false);
                    everyday.setChecked(false);
                    medicine.setIs_Every_Day(false);
//
//                    popupView= LayoutInflater.from(MainActivity.this).inflate(R.layout.popupcounter, null);
//                    PopupWindow popupWindow = new PopupWindow(popupView,WindowManager.LayoutParams.MATCH_PARENT ,200);
//                    popupWindow.setFocusable(true);
//                    // finally show up your popwindow
//                    popupWindow.showAsDropDown(popupView,0,0);
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
    private void save(View view) {
        medicine.setMed_name(medname.getText().toString());
        medicine.setMed_left(Integer.parseInt(refill.getText().toString()));
        medicine.setStrength(strenght.getText().toString());
        medicine.setHour_of_Morning(First.getText().toString());
        medicine.setHour_of_Evening(Second.getText().toString());
        medicine.setHour_of_Noon(Third.getText().toString());
        medicine.setHour_of_Night(Forth.getText().toString());
        System.out.println(medicine.getMed_name());
        System.out.println(medicine.getHow_often());
        System.out.println(medicine.getHour_of_Evening());
        System.out.println(medicine.getHour_of_Morning());
        System.out.println(medicine.getHour_of_Noon());
        System.out.println(medicine.getHour_of_Night());
        System.out.println(medicine.getStrength());
        System.out.println(medicine.getMed_left());
        System.out.println(medicine.getIs_Every_Day());

    }


}
