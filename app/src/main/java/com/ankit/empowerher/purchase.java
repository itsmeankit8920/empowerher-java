package com.ankit.empowerher;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class purchase extends AppCompatActivity {

    private RecyclerView rvProductList;
    private ProductPurchaseAdapter productPurchaseAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        rvProductList = findViewById(R.id.rv_product_list);
        rvProductList.setLayoutManager(new LinearLayoutManager(this));

        productPurchaseAdapter = new ProductPurchaseAdapter(new ArrayList<ProductPurchase>());
        rvProductList.setAdapter(productPurchaseAdapter);

        dbHelper = new DatabaseHelper(this);

        loadProducts();
    }

    private void loadProducts() {
        List<ProductPurchase> products = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_PRODUCTS,
                new String[]{DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_DESCRIPTION, DatabaseHelper.COLUMN_COST, DatabaseHelper.COLUMN_IMAGE_RES_ID},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION));
                int cost = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_COST));
                int imageResId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_RES_ID));

                products.add(new ProductPurchase(name, description, cost, imageResId));
            } while (cursor.moveToNext());
        }
        cursor.close();

        productPurchaseAdapter.updateProducts(products);
    }
}
