package com.example.medicalreminder.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(tableName = "medicines_data")
public class Medicine implements Serializable {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String Med_name;

    @ColumnInfo(name = "username")
    private String userName;

    @ColumnInfo(name = "form")
    @NonNull
    private String Med_form;

    @ColumnInfo(name = "strength")
    @NonNull
    private String Strength;

    @ColumnInfo(name = "condition")
    @NonNull
    private String Why_Taken;

    @ColumnInfo(name = "Is_Every_Day")
    @NonNull
    private Boolean Is_Every_Day;

    @ColumnInfo(name = "Morning")
    private  String Morning;

    @ColumnInfo(name = "hour_of_Morning")
    private  String hour_of_Morning;

    @ColumnInfo(name = "Noon")
    private  String Noon;
    @ColumnInfo(name = "hour_of_Noon")
    private  String hour_of_Noon;

    @ColumnInfo(name = "Evening")
    private  String Evening;
    @ColumnInfo(name = "hour_of_Evening")
    private  String hour_of_Evening;

    @ColumnInfo(name = "Night")
    private  String Night;
    @ColumnInfo(name = "hour_of_Night")
    private  String hour_of_Night;

    @ColumnInfo(name = "Frequency")
    private String How_often; //once twice


    private int count;//

    @ColumnInfo(name = "med_amount")
    private int med_amount;

    @ColumnInfo(name = "med_left")
    private int med_left;

    @ColumnInfo(name = "Night")
    private  String start_date;

    @ColumnInfo(name = "end_date")
    private String end_date;

    @ColumnInfo(name = "active")
    private Boolean active;

    @ColumnInfo(name = "Last_time_taken")
    private String Last_time_taken;

    //fixeddays

    @ColumnInfo(name = "Sunday")
    private boolean Sunday;
    @ColumnInfo(name = "Saturday")
    private boolean Saturday;
    @ColumnInfo(name = "Monday")
    private boolean Monday;
    @ColumnInfo(name = "Tuesday")
    private boolean Tuesday;
    @ColumnInfo(name = "Wedensday")
    private boolean Wedensday;
    @ColumnInfo(name = "Thursday")
    private boolean Thursday;
    @ColumnInfo(name = "Friday")
    private boolean Friday;


    public Medicine() {
    }

    public String getMed_name() {
        return Med_name;
    }

    public void setMed_name(String med_name) {
        Med_name = med_name;
    }

    public String getMed_form() {
        return Med_form;
    }

    public void setMed_form(String med_form) {
        Med_form = med_form;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }

    public String getWhy_Taken() {
        return Why_Taken;
    }

    public void setWhy_Taken(String why_Taken) {
        Why_Taken = why_Taken;
    }

    public Boolean getIs_Every_Day() {
        return Is_Every_Day;
    }

    public void setIs_Every_Day(Boolean is_Every_Day) {
        Is_Every_Day = is_Every_Day;
    }

    public String getMorning() {
        return Morning;
    }

    public void setMorning(String morning) {
        Morning = morning;
    }

    public String getHour_of_Morning() {
        return hour_of_Morning;
    }

    public void setHour_of_Morning(String hour_of_Morning) {
        this.hour_of_Morning = hour_of_Morning;
    }

    public String getNoon() {
        return Noon;
    }

    public void setNoon(String noon) {
        Noon = noon;
    }

    public String getHour_of_Noon() {
        return hour_of_Noon;
    }

    public void setHour_of_Noon(String hour_of_Noon) {
        this.hour_of_Noon = hour_of_Noon;
    }

    public String getEvening() {
        return Evening;
    }

    public void setEvening(String evening) {
        Evening = evening;
    }

    public String getHour_of_Evening() {
        return hour_of_Evening;
    }

    public void setHour_of_Evening(String hour_of_Evening) {
        this.hour_of_Evening = hour_of_Evening;
    }

    public String getNight() {
        return Night;
    }

    public void setNight(String night) {
        Night = night;
    }

    public String getHour_of_Night() {
        return hour_of_Night;
    }

    public void setHour_of_Night(String hour_of_Night) {
        this.hour_of_Night = hour_of_Night;
    }

    public String getHow_often() {
        return How_often;
    }

    public void setHow_often(String how_often) {
        How_often = how_often;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMed_amount() {
        return med_amount;
    }

    public void setMed_amount(int med_amount) {
        this.med_amount = med_amount;
    }

    public int getMed_left() {
        return med_left;
    }

    public void setMed_left(int med_left) {
        this.med_left = med_left;
    }


    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getLast_time_taken() {
        return Last_time_taken;
    }

    public void setLast_time_taken(String last_time_taken) {
        Last_time_taken = last_time_taken;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean isSunday() {
        return Sunday;
    }

    public void setSunday(boolean sunday) {
        Sunday = sunday;
    }

    public boolean isSaturday() {
        return Saturday;
    }

    public void setSaturday(boolean saturday) {
        Saturday = saturday;
    }

    public boolean isMonday() {
        return Monday;
    }

    public void setMonday(boolean monday) {
        Monday = monday;
    }

    public boolean isTuesday() {
        return Tuesday;
    }

    public void setTuesday(boolean tuesday) {
        Tuesday = tuesday;
    }

    public boolean isWedensday() {
        return Wedensday;
    }

    public void setWedensday(boolean wedensday) {
        Wedensday = wedensday;
    }

    public boolean isThursday() {
        return Thursday;
    }

    public void setThursday(boolean thursday) {
        Thursday = thursday;
    }

    public boolean isFriday() {
        return Friday;
    }

    public void setFriday(boolean friday) {
        Friday = friday;
    }
}
