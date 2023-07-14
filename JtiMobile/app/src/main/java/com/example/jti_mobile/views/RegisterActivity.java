package com.example.jti_mobile.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import android.os.Bundle;

import com.example.jti_mobile.R;
import com.example.jti_mobile.model.User;
import com.example.jti_mobile.viewModel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFullName, etUsername, etEmail, etPassword, etBirthdate, etAddress;
    private Spinner spinnerGender;
    private Button btnRegister;
    private TextView tvLogin;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.et_full_name);
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etBirthdate = findViewById(R.id.et_birthdate);
        etAddress = findViewById(R.id.et_address);
        spinnerGender = findViewById(R.id.spinner_gender);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void registerUser() {
        String fullName = etFullName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        String address = etAddress.getText().toString().trim();

        User user = new User(fullName, username, email, password, birthdate, gender, address);
        userViewModel.insertUser(user);

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        navigateToLogin();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
