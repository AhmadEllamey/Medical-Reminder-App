package com.example.medicalreminder.home.view.home_fragment.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicines_ready_to_view")
public class Medicine {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    private String name ;
    @ColumnInfo(name = "time")
    @NonNull
    private String time ;
    @ColumnInfo(name = "date")
    private String date ;
    @ColumnInfo(name = "pills")
    private String pills_to_take ;
    @ColumnInfo(name = "states")
    private String states ; // active or not
    @ColumnInfo(name = "action")
    private String action ; // missed or done

    public Medicine(String name, String time, String date, String pills_to_take, String states, String action) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.pills_to_take = pills_to_take;
        this.states = states;
        this.action = action;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
