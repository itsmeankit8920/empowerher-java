package com.ankit.empowerher;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class profile extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAPTURE_IMAGE_REQUEST = 2;
    private static final int PERMISSION_REQUEST_READ_EXTERNAL_STORAGE = 3;
    private static final int PERMISSION_REQUEST_CAMERA = 4;

    private EditText etFullName, etUsername, etEmail, etPhone, etAddress, etDob, etGender, etBio;
    private ImageView profilePicture;
    private Uri imageUri;
    private DatabaseHelper databaseHelper;
    private long userId = 1;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etFullName = findViewById(R.id.et_full_name);
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etAddress = findViewById(R.id.et_address);
        etDob = findViewById(R.id.et_dob);
        etGender = findViewById(R.id.et_gender);
        etBio = findViewById(R.id.et_bio);
        profilePicture = findViewById(R.id.profile_picture);

        Button btnSettings = findViewById(R.id.btn_settings);
        Button btnChangePassword = findViewById(R.id.btn_change_password);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnCamera = findViewById(R.id.btn_camera);

        databaseHelper = new DatabaseHelper(this);
        loadUserData();

        profilePicture.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                openImagePicker();
            }
        });

        btnCamera.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(profile.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(profile.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                openCamera();
            }
        });

        btnSave.setOnClickListener(v -> saveUserProfile());
    }

    private void loadUserData() {
        Cursor cursor = databaseHelper.getUserData(userId);
        if (cursor != null && cursor.moveToFirst()) {
            etFullName.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FULL_NAME)));
            etUsername.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)));
            etEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USER_EMAIL)));
            etPhone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE)));
            etAddress.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS)));
            etDob.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOB)));
            etGender.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENDER)));
            etBio.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BIO)));
            String profilePictureUriString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PROFILE_PICTURE_URI));
            if (profilePictureUriString != null && !profilePictureUriString.isEmpty()) {
                imageUri = Uri.parse(profilePictureUriString);
                profilePicture.setImageURI(imageUri);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void saveUserProfile() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_FULL_NAME, etFullName.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_USERNAME, etUsername.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, etEmail.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_PHONE, etPhone.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_ADDRESS, etAddress.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_DOB, etDob.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_GENDER, etGender.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_BIO, etBio.getText().toString().trim());
        values.put(DatabaseHelper.COLUMN_PROFILE_PICTURE_URI, (imageUri != null) ? imageUri.toString() : null);

        databaseHelper.updateUserProfile(userId, values);
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile;
            try {
                photoFile = createImageFile();
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this, "com.ankit.empowerher.fileprovider", photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST);
                }
            } catch (IOException ex) {
                Toast.makeText(this, "Error occurred while creating the file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = getExternalFilesDir(null);
        return File.createTempFile("JPEG_" + timeStamp + "_", ".jpg", storageDir);
    }
}