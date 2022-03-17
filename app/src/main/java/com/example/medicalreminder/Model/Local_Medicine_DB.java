package com.example.medicalreminder.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;


@Entity( tableName = "LocalMedicine" , primaryKeys = {"user_name","med_name"})
public class Local_Medicine_DB implements Serializable {

    @ColumnInfo(name = "user_name")
    @NonNull
    private  String User_name;
    @NonNull
    @ColumnInfo(name = "med_name")
    private String Med_name;
    @ColumnInfo(name = "med_form")
    private String Med_form;
    @ColumnInfo(name = "strength")
    private String Strength;

    @ColumnInfo(name = "Why_Taken")
    private String Why_Taken;

    @ColumnInfo(name = "flag")
    private String Flag;


    @ColumnInfo(name = "s_Unit")
    private  String S_Unit;

    @ColumnInfo(name = "instructions")
    private  String instructions;


    @ColumnInfo(name = "morning")
    private  String Morning;
    @ColumnInfo(name = "hour_of_morning")
    private  String hour_of_Morning;

    @ColumnInfo(name = "noon")
    private  String Noon;
    @ColumnInfo(name = "hour_of_noon")
    private  String hour_of_Noon;

    @ColumnInfo(name = "evening")
    private  String Evening;
    @ColumnInfo(name = "hour_of_evening")
    private  String hour_of_Evening;

    @ColumnInfo(name = "night")
    private  String Night;
    @ColumnInfo(name = "hour_of_night")
    private  String hour_of_Night;

    @ColumnInfo(name = "how_often")
    private String How_often; //once twice

    @ColumnInfo(name = "count")
    private int count;//

    @ColumnInfo(name = "med_amount")
    private int med_amount;

    @ColumnInfo(name = "med_left")
    private int med_left;

    @ColumnInfo(name = "at_what_time")
    private String At_what_time;

    @ColumnInfo(name = "start_date")
    private  String start_date;

    @ColumnInfo(name = "end_date")
    private String end_date;

    @ColumnInfo(name = "active")
    private Boolean active;

    @ColumnInfo(name = "last_time_taken")
    private String Last_time_taken;

    //fixed days


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

    public boolean isIs_Every_Day() {
        return Is_Every_Day;
    }

    public void setIs_Every_Day(boolean is_Every_Day) {
        Is_Every_Day = is_Every_Day;
    }

    @ColumnInfo(name = "is_every_day")
    private boolean Is_Every_Day;


    public Local_Medicine_DB() {
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

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
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

    public String getAt_what_time() {
        return At_what_time;
    }

    public void setAt_what_time(String at_what_time) {
        At_what_time = at_what_time;
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

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getS_Unit() {
        return S_Unit;
    }

    public void setS_Unit(String s_Unit) {
        S_Unit = s_Unit;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
