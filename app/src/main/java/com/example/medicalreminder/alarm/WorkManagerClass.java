package com.example.medicalreminder.alarm;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicalreminder.MainActivity;
import com.example.medicalreminder.R;
import com.example.medicalreminder.ReminderDialog.MyCustomDialoge;
import com.google.firebase.firestore.model.Values;

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
