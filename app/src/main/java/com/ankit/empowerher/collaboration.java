package com.ankit.empowerher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Collaboration Activity
public class collaboration extends AppCompatActivity {
    private ImageView donationBanner;
    private TextView tvTitle;
    private ImageView imgSell;
    private Button btnSell;
    private ImageView imgPurchase;
    private Button btnPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaboration);

        // Initialize UI components
        donationBanner = findViewById(R.id.donation_banner);
        tvTitle = findViewById(R.id.tv_title);
        imgSell = findViewById(R.id.img_sell);
        btnSell = findViewById(R.id.btn_sell);
        imgPurchase = findViewById(R.id.img_purchase);
        btnPurchase = findViewById(R.id.btn_purchase);

        // Set up click listener for Sell button
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(collaboration.this, sell.class);
                startActivity(intent);
            }
        });

        // Set up click listener for Purchase button
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(collaboration.this, purchase.class);
                startActivity(intent);
            }
        });
    }
}
