package com.example.stepanovaautorization;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private EditText username;
    private EditText password;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;

    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.btnLogin);
        loginLocked = (TextView) findViewById(R.id.textViewAuto);
        attempts = (TextView) findViewById(R.id.textViewPassword);
        numberOfAttempts = (TextView) findViewById(R.id.editTextAttempts);
        numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();
                    numberOfRemainingLoginAttempts--;
                    attempts.setVisibility(View.VISIBLE);
                    numberOfAttempts.setVisibility(View.VISIBLE);
                    numberOfAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
                    if (numberOfRemainingLoginAttempts == 0) {
                        login.setEnabled(false);
                        loginLocked.setVisibility(View.VISIBLE);
                        loginLocked.setBackgroundColor(Color.RED);
                        loginLocked.setText("Вход заблокирован!!!");
                    }
                }
            }
        });
    }
}