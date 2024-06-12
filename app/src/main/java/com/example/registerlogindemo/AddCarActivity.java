package com.example.registerlogindemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddCarActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText etCarName, etCarModel, etCarYear, etCarPrice, etAvailableStart, etAvailableEnd, etOwnerId, etAvailability;
    private ImageView ivCarImage;
    TextView pickupDate,returnDate,textView5;

    RadioGroup radioGroup;

    private Button btnSubmitCar, btnSelectImage;
    private Bitmap bitmap;
    RadioButton genderradioButton;
    LinearLayout clockstart,returnDateTimeField;
    int years,months,days,yeare,monthe,daye,daySrart,monSrart,yearSrart,dayEnd,monEnd,yearEnd;

    String StartDate,EndDate;
    private String URL = "http://10.0.2.2/PHP_Android/add_car.php"; // Change to your actual URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car);

        etCarName = findViewById(R.id.etCarName);
        etCarModel = findViewById(R.id.etCarModel);
        etCarYear = findViewById(R.id.etCarYear);
        etCarPrice = findViewById(R.id.etCarPrice);
//        etAvailableStart = findViewById(R.id.etAvailableStart);
//        etAvailableEnd = findViewById(R.id.etAvailableEnd);
//        etAvailability = findViewById(R.id.etAvailability);
        radioGroup = findViewById(R.id.Availabilityaaa);
        ivCarImage = findViewById(R.id.ivCarImage);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSubmitCar = findViewById(R.id.btnSubmitCar);
        clockstart = findViewById(R.id.startcc);
        returnDateTimeField = findViewById(R.id.endcc);
        pickupDate = findViewById(R.id.pickupDate);
        returnDate = findViewById(R.id.returnDate);
        clockstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                years = c.get(Calendar.YEAR);
                months = c.get(Calendar.MONTH);
                days = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddCarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                StartDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                daySrart = dayOfMonth;
                                monSrart = monthOfYear;
                                yearSrart = year;

                                pickupDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },

                        years, months, days);

                datePickerDialog.show();
            }
        });

        returnDateTimeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                yeare = c.get(Calendar.YEAR);
                monthe = c.get(Calendar.MONTH);
                daye = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddCarActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                EndDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                dayEnd = dayOfMonth;
                                monEnd = monthOfYear;
                                yearEnd = year;
                                returnDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },

                        yeare, monthe, daye);

                datePickerDialog.show();
            }
        });
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnSubmitCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
                Intent intent = new Intent(AddCarActivity.this, IndexOwner.class);
                String userId = getIntent().getStringExtra("USER_ID");
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
                finish();
            }

        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivCarImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void addCar() {

        String Gend;
        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);
        if(genderradioButton.getText().equals("Availability")){
            Gend = "1";
        }else{
            Gend = "2";
        }
        final String carName = etCarName.getText().toString().trim();
        final String carModel = etCarModel.getText().toString().trim();
        final String carYear = etCarYear.getText().toString().trim();
        final String carPrice = etCarPrice.getText().toString().trim();
        final String availableStart = StartDate;
        final String availableEnd = EndDate;
        final String ownerId = getIntent().getStringExtra("USER_ID");
        final String availability = Gend;
        final String carImage = getStringImage(bitmap);

        if (carName.isEmpty() || carModel.isEmpty() || carYear.isEmpty() || carPrice.isEmpty() || availableStart.isEmpty() || availableEnd.isEmpty() || ownerId.isEmpty() || availability.isEmpty() || bitmap == null) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddCarActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddCarActivity.this, "Failed to add car: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("name", carName);
                params.put("model", carModel);
                params.put("year", carYear);
                params.put("price", carPrice);
                params.put("availableStart", availableStart);
                params.put("availableEnd", availableEnd);
                params.put("ownerId", ownerId);
                params.put("imageUrl", carImage);
                params.put("availability", availability);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
