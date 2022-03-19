package com.example.medicalreminder.alarm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManagerClass extends Worker {


    public WorkManagerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {


        System.out.println("----------------------------------------- alarm working ------------------------------------------");

        return null;
    }
}
