package com.doggo.dogadopt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.model.Request;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.doggo.dogadopt.R;
import com.google.android.material.textfield.TextInputEditText;

public class viewActivity extends AppCompatActivity {

    private ImageView dogPicPreview;
    private TextView DName;
    private EditText DBreed;
    private EditText DAge;
    private EditText DStatus;
    private EditText DGender;
    private EditText dogDOAEditText;
    private TextInputEditText DogPersonal;
    private Button requestDog_button;
    private Long DogID;
    private Long userID;
    Dog aso = new Dog();
    Account account = new Account();
    Request request = new Request();
    QueryProcessor processor = new QueryProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        DogID = intent.getLongExtra("dogID",0);
        userID = intent.getLongExtra("userID",0);

        dogDOAEditText = findViewById(R.id.viewDogDOA);
        DName = findViewById(R.id.viewDogName);
        DGender = findViewById(R.id.viewDogGender);
        dogPicPreview = findViewById(R.id.viewDogPhoto);
        DAge = findViewById(R.id.viewDogAge);
        DBreed = findViewById(R.id.viewDogBreed);
        DogPersonal = findViewById(R.id.viewDogPersonality);
        requestDog_button = (Button) findViewById(R.id.adoptDog_button);
        DStatus = findViewById(R.id.viewDogStatus);

        initializeParameters(DogID);
        requestDog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), requestActivity.class);
                i.putExtra("userID",userID);
                i.putExtra("dogID",DogID);
                startActivity(i);
            }
        });
    }


    private void initializeParameters(Long id){
        LoadingDialog progress = new LoadingDialog(viewActivity.this);
        progress.startLoadingAnimation();
        processor.DogRead(Math.toIntExact(id));
        processor.setCbs(new CallBack() {
            @Override
            public void returnResult(Object obj) {
                aso = (Dog) obj;
                if (aso != null) {

                    DName.setText(aso.getName().replace("\"", ""));
                    DBreed.setText(aso.getBreed().replace("\"", ""));
                    DAge.setText(((String.valueOf(aso.getAge()))));
                    dogDOAEditText.setText(aso.getDoa().toString());
                    DogPersonal.setText(aso.getPersonality().replace("\"", ""));
                    DGender.setText(aso.getGender().replace("\"", ""));
                    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(viewActivity.this, R.array.dogStatusSpin, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    DStatus.setText(aso.getStatus().replace("\"", ""));
                    dogPicPreview.setImageBitmap(
                            Bitmap.createScaledBitmap(
                                    BitmapFactory.decodeByteArray(aso.getPhoto(), 0, aso.getPhoto().length),
                                    dogPicPreview.getWidth(),
                                    dogPicPreview.getHeight(),
                                    false
                            )
                    );

                }
                progress.dismissAnimation();
            }
        });


    }

}