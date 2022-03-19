package com.example.medicalreminder.refillreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefillReminderWorkManger extends Worker {
    Context context;

    //create Constructor take the context and WorkerParameters
    public RefillReminderWorkManger(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context=context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        startMyService(data.getString("MedReminderList"));
        return null;
    }

    public void startMyService(String data){
        Intent intent = new Intent(context,RefillReminderBroadCast.class);
        intent.putExtra("refillreminder",data);
        if(Settings.canDrawOverlays(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }
}



