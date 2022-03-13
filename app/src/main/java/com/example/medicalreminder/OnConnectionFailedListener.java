package com.example.medicalreminder;

import androidx.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;

public interface OnConnectionFailedListener {

    void onConnectionFailed(@NonNull ConnectionResult connectionResult);

}
