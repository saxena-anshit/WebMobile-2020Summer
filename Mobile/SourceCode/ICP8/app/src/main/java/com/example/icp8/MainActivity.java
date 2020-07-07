package com.example.icp8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button Loginbtn;
    EditText UsernameCtrl;
    EditText PassowrdCtrl;
    TextView StatuesLabelCtrl;
    String username;
    String passowrd;
//    String StatuesLabel = StatuesLabelCtrl.getText().toString();

    boolean validationFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameCtrl = findViewById(R.id.UsernameText);
        PassowrdCtrl = findViewById(R.id.PasswordText);
        StatuesLabelCtrl = findViewById(R.id.StatuesLabel);
        username = UsernameCtrl.getText().toString();
        passowrd = PassowrdCtrl.getText().toString();
        Loginbtn = findViewById(R.id.Loginbtn);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UsernameCtrl.getText().toString().isEmpty() && !PassowrdCtrl.getText().toString().isEmpty()) {
                    if (UsernameCtrl.getText().toString().equals("admin") && PassowrdCtrl.getText().toString().equals("123")) {
                        validationFlag = true;
                    }
                }
                if (!validationFlag) {
                    StatuesLabelCtrl.setText(R.string.error_message_login);
                }
                else {
                    reDirectToHomePage();
                }
            }


        });
    }

    public void reDirectToHomePage () {
        Intent intent = new Intent(MainActivity.this, home.class);
        startActivity(intent);
    }
}