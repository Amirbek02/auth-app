package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btnIn, btnUp;
    TextView userDetail, text;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        mAuth.getCurrentUser();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIn = findViewById(R.id.btnSignIn);
        btnUp = findViewById(R.id.btnSignUp);
        userDetail = findViewById(R.id.userDetail);
        text = findViewById(R.id.text);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null) {
            userDetail.setText("Please sign in or sign up.");
            btnIn.setVisibility(View.VISIBLE);
            btnUp.setVisibility(View.VISIBLE);

            btnIn.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            });

            btnUp.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            });
        } else {
            userDetail.setText("Logged in as: " + user.getEmail());
            btnIn.setVisibility(View.GONE);
            btnUp.setVisibility(View.GONE);

            text.setText("Sign Out");
            text.setOnClickListener(v -> {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
