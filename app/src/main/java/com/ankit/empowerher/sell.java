package com.ankit.empowerher;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class sell extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 2;

    private ImageView imgProduct;
    private EditText etProductName, etDescription, etCost, etLocation, etDeliveryTime;
    private Button btnUploadPhoto, btnSubmitSell;
    private DatabaseHelper dbHelper;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        imgProduct = findViewById(R.id.img_product);
        etProductName = findViewById(R.id.et_product_name);
        etDescription = findViewById(R.id.et_description);
        etCost = findViewById(R.id.et_cost);
        etLocation = findViewById(R.id.et_location);
        etDeliveryTime = findViewById(R.id.et_delivery_time);
        btnUploadPhoto = findViewById(R.id.btn_upload_photo);
        btnSubmitSell = findViewById(R.id.btn_submit_sell);

        dbHelper = new DatabaseHelper(this);

        btnUploadPhoto.setOnClickListener(v -> checkStoragePermission());
        btnSubmitSell.setOnClickListener(v -> submitProduct());
    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imgProduct.setImageURI(imageUri);
        }
    }

    private void submitProduct() {
        String productName = etProductName.getText().toString();
        String description = etDescription.getText().toString();
        String costStr = etCost.getText().toString();
        String location = etLocation.getText().toString();
        String deliveryTime = etDeliveryTime.getText().toString();

        if (!productName.isEmpty() && !description.isEmpty() && !costStr.isEmpty()) {
            int cost = Integer.parseInt(costStr);

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NAME, productName);
            values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
            values.put(DatabaseHelper.COLUMN_COST, cost);
            values.put(DatabaseHelper.COLUMN_LOCATION, location);
            values.put(DatabaseHelper.COLUMN_DELIVERY_TIME, deliveryTime);
            values.put(DatabaseHelper.COLUMN_IMAGE_URI, imageUri != null ? imageUri.toString() : null);

            long newRowId = db.insert(DatabaseHelper.TABLE_PRODUCTS, null, values);

            if (newRowId != -1) {
                Toast.makeText(this, "Product successfully listed", Toast.LENGTH_SHORT).show();
                clearForm();
            } else {
                Toast.makeText(this, "Error inserting product", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        etProductName.setText("");
        etDescription.setText("");
        etCost.setText("");
        etLocation.setText("");
        etDeliveryTime.setText("");
        imgProduct.setImageResource(R.drawable.add_a_photo_24);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
