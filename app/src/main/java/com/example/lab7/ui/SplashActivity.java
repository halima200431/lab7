package com.example.lab7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab7.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView tvAppName, tvSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        logo = findViewById(R.id.logo);
        tvAppName = findViewById(R.id.tvAppName);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        // Animations
        logo.setAlpha(0f);
        logo.animate().alpha(1f).setDuration(1000).start();
        logo.animate().rotation(360f).setDuration(1500).setStartDelay(500).start();

        tvAppName.setAlpha(0f);
        tvAppName.animate().alpha(1f).setDuration(800).setStartDelay(800).start();

        tvSubtitle.setAlpha(0f);
        tvSubtitle.animate().alpha(1f).setDuration(800).setStartDelay(1200).start();

        Thread redirectThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                runOnUiThread(() -> {
                    startActivity(new Intent(SplashActivity.this, ListActivity.class));
                    finish();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        redirectThread.start();
    }
}