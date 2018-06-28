package com.example.rpasr.financetracker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {







    // UI references.
   private EditText mPasswordView ;
   private EditText mEmailView;
   private  Button mRegister ,mSignIn;


    // Firebase Auth

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views


        mPasswordView = findViewById(R.id.password);
        mEmailView = findViewById(R.id.email);


        // Intilization of FireBase Auth
        mAuth = FirebaseAuth.getInstance();

        // Onclick Buttons

        mRegister = findViewById(R.id.button);

        mSignIn = findViewById(R.id.email_sign_in_button);

        // String email and password

        mRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tracker","the Registar button has been pressed");
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                finish();
                startActivity(intent);
            }
        });




    }

    //


    // Executed with signIN button is pressed : onclick
    public void signInExistingUser(View v)   {
        // TODO: create a password checker that only allows valid emial to enter on to new screen
        logIn();

    }



    // Todo login method
    private void logIn(){
        Log.d("Tracker","log in method called");

      String  email = mEmailView.getText().toString();
      String  password = mPasswordView.getText().toString();

        // Basic password and length checker

        if (email.length() == 0 || password.length() == 0) {
            Log.d("Tracker","bad pasword length and email length");
            Toast.makeText(this, "enter in a valid password and a valid email ", Toast.LENGTH_SHORT).show();
            return;

        }


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Tracker","Starting the singIn with email and paassword method");

                if (!task.isSuccessful()) {
                    Log.d("Tracker", "Problem signing in" + task.getException());
                    LoginInFaliure();// pop up message

                }
                else {
                    Log.d("Tracker", "SigninWithEmail is complete " + task.isSuccessful());
                    // start the new activity only if there is an acount

                    Intent intent = new Intent ( LoginActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);







                }

            }

        });








    }

    public void LoginInFaliure (){
        // pop up message if acount is not found in firebase

        String message = "Email or password is Incorrect";
        new AlertDialog.Builder((this))
                .setTitle("Error in Login ")
                .setMessage((message))
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }





}

