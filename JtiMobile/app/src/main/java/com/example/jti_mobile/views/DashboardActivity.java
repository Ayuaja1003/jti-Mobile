package com.example.jti_mobile.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jti_mobile.R;
import com.example.jti_mobile.model.User;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvFullName, tvUsername, tvEmail, tvGender, tvBirthdate, tvAddress;
    private Button btnEditProfile, btnLogout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvFullName = findViewById(R.id.namaLengkap);
        tvUsername = findViewById(R.id.username);
        tvEmail = findViewById(R.id.email);
        tvGender = findViewById(R.id.jenisKelamin);
        tvBirthdate = findViewById(R.id.birthday);
        tvAddress = findViewById(R.id.address);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);

        sharedPreferences = getSharedPreferences("login_pref", MODE_PRIVATE);
        User user = getUserDataFromSharedPreferences();

        if (user != null) {
            tvFullName.setText(user.getName());
            tvUsername.setText(user.getUsername());
            tvEmail.setText(user.getEmail());
            tvGender.setText(user.getGender());
            tvBirthdate.setText(user.getBirthdate());
            tvAddress.setText(user.getAddress());
        }

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToEditProfile();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private User getUserDataFromSharedPreferences() {
        String name = sharedPreferences.getString("name", "");
        String username = sharedPreferences.getString("username", "");
        String email = sharedPreferences.getString("email", "");
        String gender = sharedPreferences.getString("gender", "");
        String birthdate = sharedPreferences.getString("birthdate", "");
        String address = sharedPreferences.getString("address", "");

        return new User(name, username, email, "", birthdate, gender, address);
    }

    private void navigateToEditProfile() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    private void logoutUser() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}