package com.example.medicalreminder.refillreminder;

import static android.content.Context.WINDOW_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.navigation.Navigation;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.R;
import com.example.medicalreminder.database.Repo;
import com.example.medicalreminder.databinding.RefillReminderAlarmWindowBinding;

import java.util.concurrent.TimeUnit;

public class RefillRminderWindow {
    private Context context;
    private View mView;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private LayoutInflater layoutInflater;
    private int layoutFlage;
    RefillReminderAlarmWindowBinding binding;
    Medicine medicationPOJO;
    String data;

    public RefillRminderWindow(Context context, String data) {
        this.context = context;
        this.data = data;
        setWindowManager();
        setBinding();
    }

    public void open() {
        try {
            if (mView.getWindowToken() == null) {
                if (mView.getParent() == null) {
                    mWindowManager.addView(mView, mParams);
                }
            }
        } catch (Exception e) {
            Log.d("Error1", e.toString());
        }
    }

    public void close() {
        try {
            ((WindowManager) context.getSystemService(WINDOW_SERVICE)).removeView(mView);
            mView.invalidate();
            ((ViewGroup) mView.getParent()).removeAllViews();
        } catch (Exception e) {
            Log.d("Error2", e.toString());
        }
    }

    private void setPeriodicWorkManger(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(RefillReminderPeriodicManager.class,
                3, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(context).enqueueUniquePeriodicWork("Counter", ExistingPeriodicWorkPolicy.REPLACE, periodicWorkRequest);
//        WorkManager.getInstance(this.getContext()).enqueue(periodicWorkRequest);
    }

    private void callOneTimeRefillReminder(String pojo) {

        Data data = new Data.Builder()
                .putString("MedReminderList", pojo).build();
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();
        String tag = medicationPOJO.getMed_name();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(RefillReminderPeriodicManager.class)
                .setInputData(data)
                .setConstraints(constraints)
                .setInitialDelay(1, TimeUnit.HOURS)
                .addTag(tag)
                .build();
        WorkManager.getInstance(context).enqueueUniqueWork(tag, ExistingWorkPolicy.REPLACE,oneTimeWorkRequest);
    }

    private void setBinding() {
        binding = binding.bind(mView);
        binding.refillNotificationCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMyService();
                close();
            }
        });
        binding.txtRefillNotificationTitle.setText("Refill " + medicationPOJO.getMed_name() + " before finish!");
        binding.refillNotificationMedIcon.setImageResource(R.drawable.pill);
        binding.txtMedNameRefillNotification.setText(medicationPOJO.getMed_name());
        binding.txtMedAmountLeft.setText(medicationPOJO.getMed_amount());
        binding.txtTypeOfMedLeft.setText(medicationPOJO.getS_Unit());
        binding.txtMedLeft.setText("left");
        binding.refillNotificationSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMyService();
                close();
            }
        }); //Skip Button
        binding.refillNotificationRefillBtn.setOnClickListener(view -> {
            /*Bundle bundle = new Bundle();
            Navigation.findNavController(view) //n7ot el direction yrou7 l nour
             .navigate(R.id.action_RefillWindow_DisplayMedicineScreen,bundle);*/
            stopMyService();
            close();
        });//Refill Button
        binding.refillNotificationSnoozeBtn.setOnClickListener(view -> {
            callOneTimeRefillReminder(data);
            stopMyService();
            close();
        });//Snooze Button
    }

    private void stopMyService() {
        context.stopService(new Intent(context, RefillReminderService.class));
    }
    private void setWindowManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutFlage = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

        } else {
            layoutFlage = WindowManager.LayoutParams.TYPE_PHONE;
        }
        mParams = new WindowManager.LayoutParams(

                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT,
                layoutFlage,
                android.R.attr.showWhenLocked | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE,
                PixelFormat.TRANSLUCENT);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.refill_reminder_alarm_window, null, false);


        mParams.gravity = Gravity.CENTER;
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
    }


}

