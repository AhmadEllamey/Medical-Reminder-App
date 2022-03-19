package com.example.medicalreminder.ReminderDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.medicalreminder.R;

public class MyCustomDialoge extends AppCompatDialogFragment {


    /*
    *
    *
    * Create the method  with this implementation
    *    private void openDialog() {
            MyCustomDialoge myCustomDialoge= new MyCustomDialoge();
            myCustomDialoge.show(getSupportFragmentManager()," example");
        }
    *
    *
    * */





    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//         super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.reminder_layout,null);

        builder.setView(view)
                .setTitle("Medicine Reminder")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                })
                .setPositiveButton("Take", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // minus one from med_amount
                        // update the last_taken date
                        // update the fire_store
                        // update the medicine info || medicine ready to show
                        Toast.makeText(getContext(), "Take", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Snooze", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Snooze
                        Toast.makeText(getContext(), "Snooze", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();

    }
}
