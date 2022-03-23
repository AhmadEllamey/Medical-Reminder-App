package com.example.medicalreminder.refillreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.Model.Medicine;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RefillReminderWorkManger extends Worker {
    Context context;
    List<Medicine> medicationListForRefillReminder;

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
        Intent intent = new Intent(context,RefillReminderService.class);
        intent.putExtra("refillreminder",data);
        if(Settings.canDrawOverlays(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }
    private void callOneTimeRefillReminder(Medicine medicationPOJO) {

        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();
        String tag = medicationPOJO.getMed_name();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(RefillReminderWorkManger.class)
                .setConstraints(constraints)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .addTag(tag)
                .build();
        WorkManager.getInstance(context).enqueueUniqueWork(tag, ExistingWorkPolicy.REPLACE,oneTimeWorkRequest);
    }
    private void loopOnRefileMedicationList() {
        for (Medicine medicines : medicationListForRefillReminder) {

            if (medicines.getMed_left() <= medicines.getMed_amount()) {
                callOneTimeRefillReminder(medicines);
            }
        }
    }
}


