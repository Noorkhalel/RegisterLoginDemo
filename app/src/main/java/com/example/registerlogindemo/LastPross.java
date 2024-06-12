package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LastPross extends AppCompatActivity {
    TextView name,insurance,totalCost,insuranceRate,email,phoneNumber,vehicleName,rate,totalDays,pickup,dropoff;
    Button back;

    String User_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_last_pross);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LastPross.this, IndexCus.class);
                intent.putExtra("USER_ID", User_Id);
                startActivity(intent);
                finish();

            }
        });


        Bundle b = getIntent().getExtras();
        if (b != null) {
            User_Id = b.getString("getiduser");
        }

    }
}