package com.ankit.empowerher;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Database Helper Class
class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "empowerher.db";
    private static final int DATABASE_VERSION = 1;

    // Products table and column names
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_IMAGE_URI = "image_uri";
    public static final String COLUMN_IMAGE_RES_ID = "image_res_id";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DELIVERY_TIME = "delivery_time";

    // Users table and column names
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_FULL_NAME = "full_name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIO = "bio";
    public static final String COLUMN_PROFILE_PICTURE_URI = "profile_picture_uri";

    private static final String CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                    COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_COST + " INTEGER, " +
                    COLUMN_IMAGE_URI + " TEXT, " +
                    COLUMN_IMAGE_RES_ID + " INTEGER, " +
                    COLUMN_LOCATION + " TEXT, " +
                    COLUMN_DELIVERY_TIME + " TEXT);";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT UNIQUE, " +
                    COLUMN_USER_PASSWORD + " TEXT, " +
                    COLUMN_FULL_NAME + " TEXT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PHONE + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_DOB + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_BIO + " TEXT, " +
                    COLUMN_PROFILE_PICTURE_URI + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hashedPassword = hashPassword(password);
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USER_EMAIL + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, hashedPassword});
        boolean userExists = (cursor.getCount() > 0);
        cursor.close();
        return userExists;
    }

    public long addUser(ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_USERS, null, values);
    }

    public Cursor getUserData(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_FULL_NAME,
                COLUMN_USERNAME,
                COLUMN_PHONE,
                COLUMN_ADDRESS,
                COLUMN_DOB,
                COLUMN_GENDER,
                COLUMN_BIO,
                COLUMN_PROFILE_PICTURE_URI
        };
        String selection = COLUMN_USER_ID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};
        return db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
    }

    public void updateUserProfile(long userId, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_USER_ID + "=?";
        String[] whereArgs = {String.valueOf(userId)};
        db.update(TABLE_USERS, values, whereClause, whereArgs);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}