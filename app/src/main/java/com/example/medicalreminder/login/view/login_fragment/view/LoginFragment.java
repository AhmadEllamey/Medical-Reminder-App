package com.example.medicalreminder.login.view.login_fragment.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.example.medicalreminder.home.view.Home;
import com.example.medicalreminder.R;
import com.example.medicalreminder.login.model.User;
import com.example.medicalreminder.login.view.login_fragment.presenter.LoginFragmentPresenter;
import com.example.medicalreminder.login.view.login_fragment.presenter.LoginFragmentPresenterInterface;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;


public class LoginFragment extends Fragment implements LoginFragmentInterface {



    SharedPreferences sharedpreferences ;

    // login and register use firebase fire store
    LoginFragmentPresenterInterface loginFragmentPresenterInterface;


    private static final int RC_SIGN_IN = 12345;
    private GoogleApiClient googleApiClient;
    String idToken;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    private static final int RC_SIGN_IN_FB = 123456;
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;




    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        sharedpreferences = view.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        /*
        if(!sharedpreferences.getString("email","NA").equals("NA")){
            // the user is already signed in before , then go to the home with his stored info

            User user = new User(
                    sharedpreferences.getString("name","NA"),
                    sharedpreferences.getString("date_of_birth","NA"),
                    sharedpreferences.getString("email","NA"),
                    sharedpreferences.getString("gender","NA"),
                    sharedpreferences.getString("password","NA"),
                    sharedpreferences.getString("phone","NA")) ;

            updateTheUiAfterSystemLogin(user);
        }
         */


        loginFragmentPresenterInterface = new LoginFragmentPresenter(this);



        firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();


        // set up code for google sign in
        authStateListener = firebaseAuth -> {
            // Get signedIn user
            FirebaseUser user = firebaseAuth.getCurrentUser();

            //if user is signed in, we call a helper method to save the user details to Firebase
            if (user != null) {
                // User is signed in
                // you could place other firebase code
                //logic to save the user details to Firebase
                //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                showToast("sign in :" + user.getEmail());
            } else {
                // User is signed out
                //Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        };

        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))//you can also use R.string.default_web_client_id
                .requestEmail()
                .build();


        googleApiClient=new GoogleApiClient.Builder(this.getContext())
                .enableAutoManage(this.getActivity(),this::onConnectionFailed)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();


        // set up for facebook

        mAuth = FirebaseAuth.getInstance();

        mCallbackManager = CallbackManager.Factory.create();





        view.findViewById(R.id.login).setOnClickListener(view1 -> {
            EditText username = view.findViewById(R.id.etUsername);
            EditText password = view.findViewById(R.id.etPassword);
            String usernameText = username.getText().toString() ;
            String passwordText = password.getText().toString() ;
            if(! (usernameText.equals("") && passwordText.equals("")) ){
                loginFragmentPresenterInterface.loginByTheSystem(usernameText,passwordText);
            }
        });

        view.findViewById(R.id.signUpButton).setOnClickListener(view12 -> {

            NavController navController = Navigation.findNavController(view12);

            NavDirections navDirections = LoginFragmentDirections.toSignUp();

            navController.navigate(navDirections);

        });

        view.findViewById(R.id.continueWithGmail).setOnClickListener(view13 -> {
            // go to sign in for google
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent,RC_SIGN_IN);
        });

        view.findViewById(R.id.continueWithFacebook).setOnClickListener(view14 -> {

            LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    showToast("done we are in ");
                    System.out.println(loginResult.getAccessToken().getUserId());
                    User user = new User(loginResult.getAccessToken().getUserId(),
                            "null",loginResult.getAccessToken().getToken(),
                            "null","null","null");
                    updateTheUiAfterSystemLogin(user);
                }

                @Override
                public void onCancel() {
                    showToast("login canceled");
                }

                @Override
                public void onError(FacebookException error) {
                    showToast("something went wrong");
                }
            });

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));


        });

        view.findViewById(R.id.continueAsGuestButton).setOnClickListener(view13 -> {
            // create a guest user with any guest as mail , save the shared with this user , go to home page .
            User user = new User("guest","NA","guest@locala.user","NA","NA","NA");
            updateTheUiAfterSystemLogin(user);
        });

        view.findViewById(R.id.forgotPasswordLink).setOnClickListener(view15 -> {

            NavController navController = Navigation.findNavController(view15);

            NavDirections navDirections = LoginFragmentDirections.toForgotPassword();

            navController.navigate(navDirections);

        });


        return view ;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // what happened if the user signed in already
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        try {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }catch (FacebookGraphResponseException graphResponse){
            graphResponse.printStackTrace();
        }


    }

    @Override
    public void onStart() {
        super.onStart();

        if (authStateListener != null){
            FirebaseAuth.getInstance().signOut();
        }
        firebaseAuth.addAuthStateListener(authStateListener);


    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }


    // utility functions to use here

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            idToken = account.getIdToken();

            User user = new User(account.getDisplayName(),"null",account.getEmail(),"null","null","null");

            // you can store user data to SharedPreference
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            firebaseAuthWithGoogle(credential , user);
        }else{
            // Google Sign In failed, update UI appropriately
            showToast("failed to sign in");

        }
    }

    private void firebaseAuthWithGoogle(AuthCredential credential ,User user){

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            updateTheUiAfterSystemLogin(user);
                        }else{
                            showToast("failed to authenticate with Gmail");
                        }

                    }
                });
    }

    private void saveTheRecentUser(User user){

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("name",user.getName());
        editor.putString("date_of_birth",user.getDob());
        editor.putString("email",user.getEmail());
        editor.putString("gender",user.getGender());
        editor.putString("password",user.getPassword());
        editor.putString("phone",user.getPhone());
        editor.apply();
        editor.commit();

    }



    // overridden methods from the view controller interface

    @Override
    public void updateTheUiAfterSystemLogin(User user) {

        saveTheRecentUser(user);
        Intent i = new Intent(getContext(), Home.class);
        i.putExtra("User",user);
        //i.putExtra("username" ,user.getEmail());
        //i.putExtra("name" ,user.getName());
        //i.putExtra("date_of_birth" ,user.getDob());
        //i.putExtra("phone" ,user.getPhone());
        //i.putExtra("gender" ,user.getGender());
        //i.putExtra("password" ,user.getPassword());
        startActivity(i);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        showToast("Connection Failed");
    }

    @Override
    public void clearTheRecentUser() {
        User user = new User("NA","NA","NA","NA","NA","NA");
        saveTheRecentUser(user);
    }


}