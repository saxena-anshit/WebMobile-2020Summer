package com.example.icp8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    Button LogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LogoutBtn = findViewById(R.id.LogoutBtn);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}