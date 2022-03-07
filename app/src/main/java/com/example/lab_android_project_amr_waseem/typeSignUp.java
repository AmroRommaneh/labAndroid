package com.example.lab_android_project_amr_waseem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class typeSignUp extends AppCompatActivity {

    Button tenant;
    Button agency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_sign_up);
        tenant=findViewById(R.id.tennat);
        agency=findViewById(R.id.agency);

        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(typeSignUp.this, SignUPActivity.class);
                typeSignUp.this.startActivity(intent);
                finish();

            }
        });

        agency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(typeSignUp.this, SignUpActivtyRentingAgency.class);
                typeSignUp.this.startActivity(intent);
                finish();
            }
        });
    }
}