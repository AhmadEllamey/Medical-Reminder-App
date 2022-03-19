package com.example.medicalreminder.home.view.home_fragment.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "medicines_ready_to_view" , primaryKeys = {"name","time","user_name","date","when"})
public class MedicineReadyToShow {

    @ColumnInfo(name = "name")
    @NonNull
    private String name ;
    @ColumnInfo(name = "time")
    @NonNull
    private String time ;
    @ColumnInfo(name = "date")
    @NonNull
    private String date ;
    @ColumnInfo(name = "user_name")
    @NonNull
    private String user_name ;
    @ColumnInfo(name = "pills")
    private String pills_to_take ;
    @ColumnInfo(name = "states")
    private String states ; // active or not
    @ColumnInfo(name = "action")
    private String action ; // missed or done
    @ColumnInfo(name = "when")
    @NonNull
    private String when ; // missed or done
    @ColumnInfo(name = "instructions")
    private String instruction ;

    public MedicineReadyToShow(@NonNull String name, @NonNull String time, String date, String user_name, String pills_to_take, String states, String action,String when , String instruction) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.user_name = user_name;
        this.pills_to_take = pills_to_take;
        this.states = states;
        this.action = action;
        this.when = when ;
        this.instruction = instruction;
    }


    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPills_to_take() {
        return pills_to_take;
    }

    public void setPills_to_take(String pills_to_take) {
        this.pills_to_take = pills_to_take;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @NonNull
    public String getWhen() {
        return when;
    }

    public void setWhen(@NonNull String when) {
        this.when = when;
    }
}
