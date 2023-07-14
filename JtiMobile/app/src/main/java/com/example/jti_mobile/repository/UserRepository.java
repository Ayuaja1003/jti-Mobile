package com.example.jti_mobile.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jti_mobile.database.AppDatabase;
import com.example.jti_mobile.model.User;

public class UserRepository {
    private AppDatabase appDatabase;

    public UserRepository(Context context) {
        appDatabase = new AppDatabase(context);
    }

    public void insertUser(User user) {
        SQLiteDatabase db = appDatabase.getWritableDatabase();

        String query = "INSERT INTO " + AppDatabase.TABLE_USERS + " (" +
                AppDatabase.COLUMN_NAME + ", " +
                AppDatabase.COLUMN_USERNAME + ", " +
                AppDatabase.COLUMN_EMAIL + ", " +
                AppDatabase.COLUMN_PASSWORD + ", " +
                AppDatabase.COLUMN_BIRTHDATE + ", " +
                AppDatabase.COLUMN_GENDER + ", " +
                AppDatabase.COLUMN_ADDRESS +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        db.execSQL(query, new String[]{
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getBirthdate(),
                user.getGender(),
                user.getAddress()
        });

        db.close();
    }

    public User getUser(String email) {
        SQLiteDatabase db = appDatabase.getReadableDatabase();

        String query = "SELECT * FROM " + AppDatabase.TABLE_USERS +
                " WHERE " + AppDatabase.COLUMN_EMAIL + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{email});

        User user = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabase.COLUMN_NAME));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabase.COLUMN_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabase.COLUMN_PASSWORD));
            String birthdate = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabase.COLUMN_BIRTHDATE));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabase.COLUMN_GENDER));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(AppDatabase.COLUMN_ADDRESS));

            user = new User(name, username, email, password, birthdate, gender, address);
        }

        cursor.close();
        db.close();

        return user;
    }

}

