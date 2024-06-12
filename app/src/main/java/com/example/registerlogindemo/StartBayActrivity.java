package com.example.registerlogindemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StartBayActrivity extends AppCompatActivity {
    LinearLayout clockstart,returnDateTimeField;
    TextView pickupDate,returnDate,textView5;
    int years,months,days,yeare,monthe,daye,daySrart,monSrart,yearSrart,dayEnd,monEnd,yearEnd;
    TextView textViewFname;
    TextView textViewLname;
    TextView textViewEmail;
    TextView textViewPhone;
    Button backaa;
    RadioButton genderradioButton;
    RadioGroup radioGroup;
    String User_Id,CarName,mudlem,ImageUrl,StartDate,EndDate,Eamill, phonenum,name;
    private int yerss, VehicleId,owner_idd;
    private Double Price,pons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start_bay_actrivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewFname = findViewById(R.id.firstName);
        textViewLname = findViewById(R.id.lastName);
        textViewEmail = findViewById(R.id.email);
        textViewPhone = findViewById(R.id.phoneNumber);
        radioGroup=(RadioGroup)findViewById(R.id.mrMsTitle);
        backaa = findViewById(R.id.backaa);
        backaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clockstart = findViewById(R.id.dateTimeField);
        pickupDate = findViewById(R.id.pickupDate);
        returnDateTimeField = findViewById(R.id.returnDateTimeField);
        returnDate = findViewById(R.id.returnDate);
//        textView5 = findViewById(R.id.textView5);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            User_Id = b.getString("getiduser");
            CarName = b.getString("carName");
            yerss = b.getInt("getYear");
            Price = b.getDouble("getPrice");
            mudlem = b.getString("getModel");
            owner_idd = b.getInt("getOwnerId");
            VehicleId = b.getInt("getVehicleId");
            pons= b.getDouble("getpons");
            ImageUrl = b.getString("getImageUrl");

        }
        fetchUserDetails(User_Id);
        clockstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                years = c.get(Calendar.YEAR);
                months = c.get(Calendar.MONTH);
                days = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        StartBayActrivity.this,
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
                        StartBayActrivity.this,
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
    }
    private void fetchUserDetails(final String userId) {
        String URL = "http://10.0.2.2/PHP_Android/StartbuyUserHelp.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        // Extract user details from JSON response
                        String fname = jsonObject.getString("Fname");
                        String lname = jsonObject.getString("Lname");
                        String email = jsonObject.getString("email");
                        String phone = jsonObject.getString("phone");
                        name = fname+" "+lname;
                        Eamill = email;
                        phonenum = phone;
                        textViewFname.setText(fname);
                        textViewLname.setText(lname);
                        textViewEmail.setText(email);
                        textViewPhone.setText(phone);
                    } else {
                        Log.e("UserDetailsResponse", "Failed to fetch user details");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error response
                Log.e("UserDetailsResponse", "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", userId);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(this).add(stringRequest);
    }
    public void onSummary(View view){
        if(StartDate != null && EndDate != null){
            String Gend;
            int selectedId = radioGroup.getCheckedRadioButtonId();
            genderradioButton = (RadioButton) findViewById(selectedId);
            if(genderradioButton.getText().equals("Mr")){
                Gend = "Mr";
            }else{
                Gend = "Ms";
            }

//        int daysBetween = daysBetweenDates(yearStart, monthStart, daySrart, yearEnd, monthEnd, dayEnd);
        LocalDate startDate = LocalDate.of(yearSrart, monSrart, daySrart);
        LocalDate endDate = LocalDate.of(yearEnd, monEnd, dayEnd);
        int NumOfDay = (int) ChronoUnit.DAYS.between(startDate, endDate);

        Intent i = new Intent(this, SummaryBuy.class);
        i.putExtra("carName", CarName);
        i.putExtra("getYear", yerss);
        i.putExtra("getPrice", Price);
        i.putExtra("getModel", mudlem);
        i.putExtra("getOwnerId", owner_idd);
        i.putExtra("getVehicleId", VehicleId);
        i.putExtra("getpons", pons);
        i.putExtra("getiduser", User_Id);
        i.putExtra("getImageUrl", ImageUrl);
        i.putExtra("StartDate", StartDate);
        i.putExtra("EndDate", EndDate);
        i.putExtra("NumOfDay", NumOfDay);
        i.putExtra("GetEamill", Eamill);
        i.putExtra("Gend",Gend);
        i.putExtra("GetPhoneNum",phonenum);
        i.putExtra("Getname",name);
        startActivity(i);
        }else{
            Toast.makeText(this, "Please choose a date", Toast.LENGTH_SHORT).show();
        }
    }
}