package com.example.android.sunshine.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.android.sunshine.R;

// COMPLETED (1) Add new Activity called SettingsActivity using Android Studio wizard
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Do step 2 in SettingsActivity
        // COMPLETED (2) Set setDisplayHomeAsUpEnabled to true on the support ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
