package com.example.rpasr.financetracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


public class RegisterActivity extends AppCompatActivity {

    // Constants




    // UI Varibles
    Button mBack ,mSign;
    EditText mEmail,mPassword,mConfPassword;
    // FireBase
    FirebaseAuth mAuth;

    final int MINAMOUNTCHAR = 6;




    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        // set up Back Button

        // Intlitialses  other UI componets

        mEmail = findViewById(R.id.user);
        mPassword = findViewById(R.id.pass);
        mConfPassword = findViewById(R.id.confirmPassword);
        mSign = findViewById(R.id.createAccount);

        mBack = findViewById(R.id.backButton);
        // Intlise fireBaseAuth

        mAuth = FirebaseAuth.getInstance();

        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tracker","Back Button has been clicked");
                // Todo set up new Intent

               updateUI();
            }
        });




mSign.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        Log.d("Tracker","the submit button is pressed");

        // Parameters from  UI

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        String confPassword = mConfPassword.getText().toString();
        if ( !formatChecker(email,password,confPassword)){
            Log.d("Tracker","Invalid Loging formatChecker called");
            showError();
        }
        else{
            createNewUser();
            updateUI();
        }



    }
});


    }

    // Todo create a new User in fireBase

    private void createNewUser (){
      String email = mEmail.getText().toString().trim();
      String password = mPassword.getText().toString();
      String confirmationPassword = mConfPassword.getText().toString();



          Log.d("Tracker","data passed input test");

          mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this ,new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {

                  if ( ! task.isSuccessful()){
                   Log.d("Tracker", "Not Succsesful"+task.getException());


                  }

                  else{
                      Log.d("Tracker","Succses");

                  }

              }
          });





    }

    public boolean formatChecker(String email, String password, String confPasword){
        // This method checks if the input by the user is in the right format
        boolean isCorrect = false; // asume not correct
        if ( ! email.contains("@")){
            Toast.makeText(this, "enter in a valid email", Toast.LENGTH_SHORT).show();
        }
        else if ( email.contains("@")&& email.length()>4)
            isCorrect = true;


        // pasword

        if ( password.length() < MINAMOUNTCHAR){
            Toast.makeText(this,"Enterin the correct amount of character at least 6", Toast.LENGTH_SHORT).show();
            isCorrect = false;
        }
        else if ( password.length() >= MINAMOUNTCHAR && password.equals(confPasword)){
            isCorrect = true;
        }


        return isCorrect;



    }
    private void updateUI(){
        Intent intentBack = new Intent ( RegisterActivity.this, LoginActivity.class);
        finish();
        startActivity(intentBack);
    }

    public void showError (){
        String message = "Please ensure that all the forms completed";
        new AlertDialog.Builder((this))
                .setTitle("Error in Login ")
                .setMessage((message))
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
