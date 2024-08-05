package com.doggo.dogadopt.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.doggo.dogadopt.R;

public class signupActivity extends AppCompatActivity {
    EditText signup_firstname;
    EditText signup_lastname;
    EditText signup_age;
    EditText signup_contact;
    EditText signup_address;
    EditText signup_username;
    EditText signup_password;
    EditText signup_verify;
    Button signup_submit;
    String username;
    String password;
    String confirmPassword;
    String firstname;
    String lastname;
    String address;
    int age;
    String contact;
    QueryProcessor processor = new QueryProcessor();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_firstname = findViewById(R.id.signup_firstNameField);
        signup_lastname = findViewById(R.id.signup_lastNameField);
        signup_address = findViewById(R.id.signup_addressField);
        signup_age = findViewById(R.id.signup_ageField);
        signup_contact = findViewById(R.id.signup_contactField);
        signup_username = findViewById(R.id.signup_usernameField);
        signup_password = findViewById(R.id.signup_passwordField);
        signup_verify = findViewById(R.id.signup_confPassField);
        signup_submit = findViewById(R.id.signup_submit_button);
        signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perform_signup();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        triggerCancel();
    }

    void triggerCancel(){
        AlertDialog.Builder frameBuilder = new AlertDialog.Builder(this);
        frameBuilder.setCancelable(false);
        frameBuilder.setTitle("Cancel Signup");
        frameBuilder.setMessage("Do you want to cancel signup?");
        frameBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        frameBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        frameBuilder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        AlertDialog dialog = frameBuilder.create();
        dialog.show();
    }

    void perform_signup(){
        firstname = signup_firstname.getText().toString();
        lastname = signup_lastname.getText().toString();
        address = signup_address.getText().toString();
        try {
            age = Integer.parseInt(signup_age.getText().toString());
        } catch (NumberFormatException e) {
            age = 0;
        }
        contact = signup_contact.getText().toString();
        username = signup_username.getText().toString();
        password = signup_password.getText().toString();
        confirmPassword = signup_verify.getText().toString();
        if (username.isBlank() || password.isBlank() || address.isBlank() || signup_age.getText().toString().isBlank() || contact.isBlank() || firstname.isBlank() || lastname.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(signupActivity.this,"A field is blank. Please try again", Toast.LENGTH_SHORT).show();
        }
        else if(password.equals(confirmPassword)){
            processor.AccountAdd(firstname,lastname,address,contact,age,username,password,"USER");
            processor.setCbs(new CallBack() {
                @Override
                public void returnResult(Object obj) {
                    if ((boolean) obj == true){
                        Toast.makeText(signupActivity.this,"Account creation is successful.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if ((boolean) obj == false){
                        Toast.makeText(signupActivity.this,"Account creation is unsuccessful. Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            Toast.makeText(signupActivity.this,"The password doesn't match. Please try again", Toast.LENGTH_SHORT).show();
        }
    }
}
