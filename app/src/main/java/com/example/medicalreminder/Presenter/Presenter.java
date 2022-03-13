//package com.example.medicalreminder.Presenter;
//
//import static android.content.ContentValues.TAG;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.example.myhealth.Model.Medicine;
//import com.example.myhealth.addingmed.view.Med_Left;
//import com.example.myhealth.fb.Connent_Real_Time_DB;
//
//public class Presenter implements  PresenterInterface {
//    Context context;
//    PresenterInterface presenterInterface;
////    Connent_Real_Time_DB connent_real_time_db;
//    public Presenter(Context context, PresenterInterface presenterInterface) {
//        this.context = context;
//        this.presenterInterface = presenterInterface;
//    }
//
//    public Presenter(Context context, Med_Left presenterInterface) {
//        this.context = context;
//        this.presenterInterface = presenterInterface;
//    }
//
//
//    @Override
//    public void AddMedacine(Medicine medicine) {
//        Log.i(TAG, "AddMedacine: send from presenter");
//        connent_real_time_db = new Connent_Real_Time_DB();
//        connent_real_time_db.addmedtorealtime(medicine);
//    }
//}
