package com.example.registerlogindemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class IndexOwner extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private CarOwner carOwner;
    private OwnerAccount ownerAccount;
    private Notifications notifications;
    private EditFreg editFreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_owner);

        String userId = getIntent().getStringExtra("USER_ID");
        String usertyp = getIntent().getStringExtra("UserTyp");

        carOwner = CarOwner.newInstance(userId,usertyp);
        ownerAccount = OwnerAccount.newInstance(userId);
        notifications = Notifications.newInstance(userId);
        editFreg = EditFreg.newInstance(userId);

        // Replace the fragment container with the CarOwner fragment
        setFragment(carOwner);

        initComponents();
        clickListener();
    }

    private void clickListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_vehicle) {
                setFragment(carOwner);
                return true;
            } else if (itemId == R.id.nav_booking) {
                setFragment(notifications);
                return true;
            } else if (itemId == R.id.nav_account) {
                setFragment(ownerAccount);
                return true;
            } else if (itemId == R.id.Add_Car) {
                setFragment(editFreg);
                return true;
            } else {
                throw new IllegalStateException("Unexpected value: " + itemId);
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_1, fragment);
        fragmentTransaction.commit();
    }

    private void initComponents() {
        bottomNavigationView = findViewById(R.id.bottom_nav1_1);
    }
}
