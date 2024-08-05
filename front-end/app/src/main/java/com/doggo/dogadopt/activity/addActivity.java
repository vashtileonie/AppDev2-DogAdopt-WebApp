package com.doggo.dogadopt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.doggo.dogadopt.LoadingDialog;
import com.doggo.dogadopt.retrofit.CallBack;
import com.doggo.dogadopt.retrofit.QueryProcessor;
import com.doggo.dogadopt.R;
import com.google.android.material.textfield.TextInputEditText;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addActivity extends AppCompatActivity {

    private ImageView dogPicPreview;
    private Button dogChoosePhoto;
    private EditText DName;
    private EditText DBreed;
    private EditText DAge;
    private Spinner DStatus;
    private Spinner DGender;
    private EditText dogDOAEditText;
    private TextInputEditText DogPersonal;
    private Button addDog_button;
    private Calendar calendar;
    int SELECT_PICTURE = 200;
    QueryProcessor processor = new QueryProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dogDOAEditText = findViewById(R.id.dogDOA);
        DName = findViewById(R.id.dogName);
        DGender = findViewById(R.id.dogGender);
        dogChoosePhoto = (Button) findViewById(R.id.dogChoosePhoto);
        dogPicPreview = findViewById(R.id.dogPhoto);
        DAge = findViewById(R.id.dogAge);
        DBreed = findViewById(R.id.dogBreed);
        DogPersonal = findViewById(R.id.dogPersonality);
        addDog_button = (Button) findViewById(R.id.admin_addDog_button);
        DStatus = findViewById(R.id.dogStatus);
        calendar = Calendar.getInstance();

        dogChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
        addDog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bitmap bitmap = ((BitmapDrawable) dogPicPreview.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();

                processor.DogAdd(imageInByte,DName.getText().toString(),DBreed.getText().toString(),DAge.getText().toString(),dogDOAEditText.getText().toString(),DogPersonal.getText().toString(),DStatus.getSelectedItem().toString(),DGender.getSelectedItem().toString());

                processor.setCbs(new CallBack() {
                    @Override
                    public void returnResult(Object obj) {
                        LoadingDialog progress = new LoadingDialog(addActivity.this);
                        progress.startLoadingAnimation("Submitting...");
                        if ((boolean) obj == true){
                            progress.dismissAnimation();
                            Toast.makeText(addActivity.this,"Dog addition is successful.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if ((boolean) obj == false){
                            progress.dismissAnimation();
                            Toast.makeText(addActivity.this,"Dog addition is unsuccessful. Please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


        // Optionally, set an initial date in the EditText
        updateDateInView();


    }

    public void showDatePickerDialog(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        calendar.set(Calendar.YEAR, selectedYear);
                        calendar.set(Calendar.MONTH, selectedMonth);
                        calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                        updateDateInView();
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void updateDateInView() {
        String myFormat = "yyyy-MM-dd"; // Choose the format you desire
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dogDOAEditText.setText(sdf.format(calendar.getTime()));
    }

    private void imageChooser(){

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri){
                    dogPicPreview.setImageURI(selectedImageUri);
                }
            }
        }

    }


    private void initializeButtons(){



    }
}