package com.ankit.empowerher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

// Home Activity
public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up the BottomNavigationView
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_training) {
                startActivity(new Intent(home.this, training.class));
                return true;
            } else if (itemId == R.id.nav_marketplace) {
                startActivity(new Intent(home.this, marketplace.class));
                return true;
            } else if (itemId == R.id.nav_collaboration) {
                startActivity(new Intent(home.this, collaboration.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(home.this, profile.class));
                return true;
            } else {
                return false;
            }
        });

        // Set up GridView for popular products
        GridView gridPopularProducts = findViewById(R.id.grid_popular_products);
        List<Product> products = new ArrayList<>();
//        products.add(new Product("Handmade Earrings", R.drawable.earrings));
//        products.add(new Product("Decorative Vase", R.drawable.vase));

        PopularProductsAdapter productAdapter = new PopularProductsAdapter(this, products);
        gridPopularProducts.setAdapter(productAdapter);

        // Set up ListView for recent updates
        ListView listRecentUpdates = findViewById(R.id.list_recent_updates);
        List<String> recentUpdates = new ArrayList<>();
        recentUpdates.add("New Sewing Course Released!");
        recentUpdates.add("Upcoming Workshop on Digital Marketing");

        RecentUpdatesAdapter recentUpdatesAdapter = new RecentUpdatesAdapter(this, recentUpdates);
        listRecentUpdates.setAdapter(recentUpdatesAdapter);

        // Set up Spinner for product categories
        Spinner categorySpinner = findViewById(R.id.spinner_categories);

// Define category list
        List<String> categories = new ArrayList<>();
        categories.add("Skill Development");
        categories.add("Handmade Products");
        categories.add("Community Support");
        categories.add("Training Programs");

// Set up the Spinner Adapter with custom layout
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, R.layout.spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);
    }
}