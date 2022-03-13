package com.example.medicalreminder;

import androidx.annotation.NonNull;

import com.example.medicalreminder.login.model.User;
import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;

public class DraftCode {

    User user  = new User("Abdelrahman","9/4/2005","abdelrahmanellamey@gmail.com","male","asd","0100000009");


    /*

    private void handleFacebookAccessToken(AccessToken token) {
        System.out.println("handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, UI will update with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("Authentication Succeeded.");
                        } else {
                            // If sign-in fails, a message will display to the user.
                            System.out.println("Authentication failed.");
                        }
                    }
                });
    }


     */




    /*

     // for onactivity result
     // The activity result pass back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);


    loginFragmentPresenterInterface = new LoginFragmentPresenter(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mCallbackManager = CallbackManager.Factory.create();

        view.findViewById(R.id.forgotPasswordLink).setOnClickListener(view1 -> {

            NavController navController = Navigation.findNavController(view1);

            NavDirections navDirections = LoginFragmentDirections.toForgotPassword();

            navController.navigate(navDirections);

        });


        view.findViewById(R.id.signUpButton).setOnClickListener(view1 -> {

            NavController navController = Navigation.findNavController(view1);

            NavDirections navDirections = LoginFragmentDirections.toSignUp();

            navController.navigate(navDirections);

        });

        LoginButton loginButton = view.findViewById(R.id.continueWithFacebook);

        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                System.out.println("facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {

            }

        });
     */




    public void aVoid() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("users").document("email");
                db.collection("users").document("abdelrahmanellamey@gmail.com").set(user);
                docRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            //System.out.println(document);
                            User user = document.toObject(User.class);
                            System.out.println(user.getName());
                            System.out.println(user.getEmail());
                            System.out.println(user.getGender());
                            System.out.println(user.getDob());
                            System.out.println(user.getPhone());
                            System.out.println(user.getPassword());
                        } else {
                            System.out.println("doesn't exist");
                        }
                    } else {
                        System.out.println("failed");
                    }
                });


            }
        }).start();
    }


    /*
     try {
        final PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
        for (android.content.pm.Signature signature : info.signatures) {
            final MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            final String hashKey = new String(Base64.encode(md.digest(), 0));
            Log.i("AppLog", "key:" + hashKey + "=");
            System.out.println(hashKey);
        }
    } catch (Exception e) {
        Log.e("AppLog", "error:", e);
    }
     */


}
