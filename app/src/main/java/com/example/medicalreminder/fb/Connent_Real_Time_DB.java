//package com.example.medicalreminder.fb;
//
//import static android.content.ContentValues.TAG;
//
//import android.util.Log;
//
//import com.example.medicalreminder.Model.Medicine;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class Connent_Real_Time_DB {
//
//    DatabaseReference myRef;
//    FirebaseDatabase database;
//
//    public Connent_Real_Time_DB() {
//
//        //open the connection with firebase
//        database = FirebaseDatabase.getInstance();
//        //get the root of the database
//        myRef = database.getReference();
//    }
//    public void addmedtorealtime(Medicine medicine){
//        Log.i(TAG, "addmedtorealtime: from the connect");
//        //adding gson
//        myRef.child(medicine.getMed_name()).setValue(medicine);
//    }
//
//    public void readfromrealtime(){
//        // Read from the database
////        myRef.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                // This method is called once with the initial value and again
////                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue(String.class);
//////                name.setText(value);
////                // Log.d(TAG, "Value is: " + value);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////                // Failed to read value
////                //Log.w(TAG, "Failed to read value.", error.toException());
////            }
////    }
//    }
//
//}
