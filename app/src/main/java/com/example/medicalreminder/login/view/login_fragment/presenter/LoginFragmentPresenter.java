package com.example.medicalreminder.login.view.login_fragment.presenter;


import com.example.medicalreminder.login.model.User;
import com.example.medicalreminder.login.view.login_fragment.view.LoginFragmentInterface;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginFragmentPresenter implements LoginFragmentPresenterInterface{


    LoginFragmentInterface loginFragmentInterface;



    public LoginFragmentPresenter(LoginFragmentInterface loginFragmentInterface) {
        this.loginFragmentInterface = loginFragmentInterface;
    }



    @Override
    public void loginByTheSystem(String username, String password) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(username);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    if(document.get("password").equals(password)){
                        // todo go to the home screen with the info of the user
                        User user = document.toObject(User.class);
                        loginFragmentInterface.updateTheUiAfterSystemLogin(user);
                    }
                } else {
                    // todo says that wrong username or password
                    loginFragmentInterface.showToast("Wrong username or password");
                }
            } else {
                // todo print something went wrong
                loginFragmentInterface.showToast("something went wrong");
            }
        });


    }











}
