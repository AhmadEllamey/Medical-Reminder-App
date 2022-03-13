package com.example.medicalreminder.login.view.login_fragment.view;

import androidx.annotation.NonNull;

import com.example.medicalreminder.login.model.User;
import com.google.android.gms.common.ConnectionResult;

public interface LoginFragmentInterface {

    void updateTheUiAfterSystemLogin(User user);

    void showToast(String msg);

    void onConnectionFailed(@NonNull ConnectionResult connectionResult);

    void clearTheRecentUser();





}
