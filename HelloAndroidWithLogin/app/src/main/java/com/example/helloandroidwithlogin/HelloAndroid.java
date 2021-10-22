package com.example.helloandroidwithlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HelloAndroid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Required call through to Activity.onCreate()
        // Restore any saved instance state
        super.onCreate(savedInstanceState);

        // Set up the application's user interface (content view)
        setContentView(R.layout.activity_hello_android);
    }
}