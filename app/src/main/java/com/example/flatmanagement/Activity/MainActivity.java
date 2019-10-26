package com.example.flatmanagement.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.flatmanagement.Fragment.CreateHouseFragment;
import com.example.flatmanagement.Fragment.HomeFragment;
import com.example.flatmanagement.Fragment.ProfileFragment;
import com.example.flatmanagement.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;
                    case R.id.createHouse:
                        replaceFragment(new CreateHouseFragment());
                        break;
                }
                return false;
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }

    private void init() {
        frameLayout=findViewById(R.id.frameLayout);
        bottomNavigation=findViewById(R.id.bottomNavigation);
    }
}
