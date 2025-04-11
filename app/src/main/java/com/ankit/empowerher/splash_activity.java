package com.ankit.empowerher;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class  splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash_activity.this, onboardingActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

}
