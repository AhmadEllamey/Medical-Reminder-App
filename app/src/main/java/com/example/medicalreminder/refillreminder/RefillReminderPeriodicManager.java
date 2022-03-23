package com.example.medicalreminder.refillreminder;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.example.medicalreminder.Model.Medicine;
import com.example.medicalreminder.database.Repo;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class RefillReminderPeriodicManager extends Worker
{
    Single<List<Medicine>> medicationSingleListForRefillReminder;
    List<Medicine> medicationListForRefillReminder;
    Context context;

    public RefillReminderPeriodicManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Repo repo=new Repo();
        medicationSingleListForRefillReminder = repo.getAllMedications();
        subscribeOnSingleForRefillReminder();
        return Result.success();
    }

    private void subscribeOnSingleForRefillReminder() {
        medicationSingleListForRefillReminder.subscribe(new SingleObserver<List<Medicine>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onSuccess(List<Medicine> medicationPOJOS) {
                for(Medicine medicationPOJO:medicationPOJOS){
                }
                medicationListForRefillReminder = medicationPOJOS;
                loopOnRefileMedicationList();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
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
