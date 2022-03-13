package com.example.medicalreminder.login.view.name_email_phone_fragment.view;

import android.view.View;

public interface AskingForNameEmailPhoneViewInterface {



    void goToTheNextPage(View view , String name , String email ,String phone);

    void goToThePreviousPage(View view);

    void showToast(String msg);



}
