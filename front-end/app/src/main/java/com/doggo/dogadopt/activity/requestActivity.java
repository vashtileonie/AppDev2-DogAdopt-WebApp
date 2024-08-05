package com.doggo.dogadopt.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.R;
import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class requestActivity extends AppCompatActivity {

    Long DogID;
    Long userID;
    Account account;
    Dog dog;
    QueryProcessor processor = new QueryProcessor();
    EditText dogName;
    EditText fullName;
    EditText contact;
    EditText inquiry;

    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_user);

        LoadingDialog progress = new LoadingDialog(requestActivity.this);
        progress.startLoadingAnimation();
        Intent intent = getIntent();
        DogID = intent.getLongExtra("dogID",0);
        userID = intent.getLongExtra("userID",0);

        dogName = findViewById(R.id.dogName_adopt);
        fullName = findViewById(R.id.fullName_adopt);
        inquiry = findViewById(R.id.inquiry_adopt);
        contact = findViewById(R.id.contactNum_adopt);
        send = findViewById(R.id.send_adopt);

        processor.AccountRead(Math.toIntExact(userID));
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                account = (Account) obj;
                fullName.setText(account.getFirstName() + " " + account.getLastName());
                contact.setText(account.getContactNumber());
            }
        });

        processor = new QueryProcessor();
        processor.DogRead(Math.toIntExact(DogID));
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                dog = (Dog) obj;
                dogName.setText(dog.getName().replace("\"", ""));
                progress.dismissAnimation();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processor = new QueryProcessor();
                processor.RequestAdd(DogID,userID,contact.getText().toString(),inquiry.getText().toString(),account.getFirstName() + " " + account.getLastName(),"FOR REVIEW");




                processor.setCbs(new CallBack() {
                    @Override
                    public void returnResult(Object obj) {
                        LoadingDialog progress = new LoadingDialog(requestActivity.this);
                        progress.startLoadingAnimation();
                        if ((boolean) obj == true){
                            progress.dismissAnimation();
                            Toast.makeText(requestActivity.this,"Request for adoption is successful.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if ((boolean) obj == false){
                            progress.dismissAnimation();
                            Toast.makeText(requestActivity.this,"Request for adoption is unsuccessful. Please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });




    }
}
