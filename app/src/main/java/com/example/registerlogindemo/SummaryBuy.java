package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class SummaryBuy extends AppCompatActivity {
    ImageView vehicleImage;

    TextView name,insurance,totalCost,insuranceRate,email,phoneNumber,vehicleName,rate,totalDays,pickup,dropoff;
    String ImageUrl;
    String User_Id,CarName,mudlem,StartDate,Nameaa,EndDate,Eamill, phonenum,Eamilladd,phonenn;
    private int yerss, VehicleId,owner_idd,NumOfDay,Noofweeks,Active,Scheduled;
    private Double Price,pons,Amount_Due;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary_buy);

        name = findViewById(R.id.name);
        vehicleName = findViewById(R.id.vehicleName);
        phoneNumber= findViewById(R.id.phoneNumber);
        vehicleImage = findViewById(R.id.vehicleImage);
        email = findViewById(R.id.email);
        rate= findViewById(R.id.rate);
        totalDays = findViewById(R.id.totalDays);
        pickup = findViewById(R.id.pickup);
        dropoff = findViewById(R.id.dropoff);
        insuranceRate = findViewById(R.id.insuranceRate);
        insurance = findViewById(R.id.insurance);
        totalCost = findViewById(R.id.totalCost);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            name.setText(b.getString("Gend")+". "+b.getString("Getname"));

            email.setText(b.getString("GetEamill"));
            vehicleName.setText(b.getString("carName"));
            phoneNumber.setText(b.getString("GetPhoneNum"));
            String a = "$"+b.getDouble("getPrice")+"/day";
            rate.setText(a);
            totalDays.setText(b.getInt("NumOfDay")+ "day");
            pickup.setText(b.getString("StartDate"));
            dropoff.setText(b.getString("EndDate"));
            double bouns = b.getDouble("getpons");
            String typBu;
            if(bouns == 0){
                typBu = "None";
            }else if (bouns == 15){
                typBu = "Basic";
            }else{
                typBu = "Premium";
            }
            insuranceRate.setText("$"+bouns+"/day");
            insurance.setText(typBu);
            double totleP;
            totleP = (bouns+b.getDouble("getPrice"))*b.getInt("NumOfDay");
            totalCost.setText("$"+totleP);


            User_Id = b.getString("getiduser");
            VehicleId = b.getInt("getVehicleId");
            StartDate = b.getString("StartDate");
            EndDate = b.getString("EndDate");
            NumOfDay = b.getInt("NumOfDay");
            Noofweeks = 0;
            Amount_Due=totleP;
            Active = 1;
            Scheduled= 1;
            Eamilladd = b.getString("GetEamill");
            phonenn = b.getString("GetPhoneNum");
            Nameaa = b.getString("Getname");






            ImageUrl = b.getString("getImageUrl");
            Glide.with(this)
                    .load(ImageUrl)
                    .into(vehicleImage);
        }


    }

    public void BookNow(View view) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/PHP_Android/booking.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the PHP backend
                        // This could be a success or failure message
                        Toast.makeText(SummaryBuy.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle errors
                Toast.makeText(SummaryBuy.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Set POST parameters for the booking
                Map<String, String> params = new HashMap<>();
                params.put("Customer_id", User_Id);
                params.put("Vehicle_id", String.valueOf(VehicleId));
                params.put("Start_date", StartDate);
                params.put("Return_date", EndDate);
                params.put("Noofdays", String.valueOf(NumOfDay));
                params.put("Noofweeks", String.valueOf(Noofweeks));
                params.put("Amount_Due", String.valueOf(Amount_Due));
                params.put("Active", String.valueOf(Active));
                params.put("Scheduled", String.valueOf(Scheduled));
                params.put("email", Eamilladd);
                params.put("phone", phonenn);
                params.put("Name", Nameaa);
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        Intent i = new Intent(this, LastPross.class);
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
        i.putExtra("GetPhoneNum",phonenum);
        i.putExtra("Getname",Nameaa);
        i.putExtra("Amount_Due",Amount_Due);
        startActivity(i);
    }

}