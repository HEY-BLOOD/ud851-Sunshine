package com.example.android.sunshine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// COMPLETED (1) Create a new Activity called DetailActivity using Android Studio's wizard
// COMPLETED (2) Change the root layout of activity_detail.xml to a FrameLayout and remove unnecessary xml attributes
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}