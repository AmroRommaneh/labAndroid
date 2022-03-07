package com.example.lab_android_project_amr_waseem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class Tenant_main_activity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    String email_data = " ";
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_main_activity);
        Intent intent = getIntent();
        email_data = intent.getStringExtra(SignINActivity.extra_email);
//navigationView.findViewById(R.id.nav_view);
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_callus,R.id.nav_house_menu,R.id.nav_favourites,R.id.nav_logout,R.id.search)
//                .setDrawerLayout(drawer)
//                .build();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF7F50"));



        // Set BackgroundDrawable
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public String getMyData() {
        return email_data;
    }

    public void logout_process(MenuItem item)
    {
        SharedPreferences sp=getSharedPreferences("My Shared Preference",MODE_PRIVATE);
        if(sp.contains("email"))
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("email");
            editor.remove("password");
            editor.putString("msg","Logged out Successfully");
            editor.commit();

            startActivity(new Intent(Tenant_main_activity.this,SignINActivity.class));
        }

    }

    public void OpenSearchPage(MenuItem item) {

        Intent intent = new Intent(Tenant_main_activity.this, search.class);
        Tenant_main_activity.this.startActivity(intent);
        finish();

    }

    public void OpenProfile(MenuItem item) {

        Intent intent = new Intent(Tenant_main_activity.this, profile.class);
        Tenant_main_activity.this.startActivity(intent);
        finish();
    }


    public void OpenLogout(MenuItem item) {
        SharedPreferences sp=getSharedPreferences("My Shared Preference",MODE_PRIVATE);
        if(sp.contains("email"))
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("email");
            editor.remove("password");
            editor.putString("msg","Logged out Successfully");
            editor.commit();

        }
        Toast toast = Toast.makeText(Tenant_main_activity.this,
                "Logged out succesfully", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(Tenant_main_activity.this, SignINActivity.class);
        Tenant_main_activity.this.startActivity(intent);
        finish();
    }
}