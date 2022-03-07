package com.example.lab_android_project_amr_waseem;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainConnect extends AppCompatActivity {
    Button x;
    LinearLayout linearLayout;
    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
      ArrayList<House> houses;
     RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_main);
        System.out.println("hereeee 0-1");
        mQueue = Volley.newRequestQueue(this);


        houses =new ArrayList<>();
        x = (Button) findViewById(R.id.button);
        System.out.println("hereeee 0-2");

        x.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 int[] connectionFlag = {1};
setButtonText("connecting");

                String url = "http://10.0.2.2:8050/api/House/getAll";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                             System.out.println("hereeee 0");
                                House house = new House();
                                try {
                                    JSONArray jsonArray = response.getJSONArray("");
                                    JSONObject jsonObject ;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject=jsonArray.getJSONObject(i);
                                    house.setGarden(jsonObject.getBoolean("garden"));
                                    house.setBalcony(jsonObject.getBoolean("balcony"));
                                    house.setCity(jsonObject.getString("City"));
                                    house.setPrice(jsonObject.getDouble("price"));
                                    house.setAge(jsonObject.getInt("Age"));
                                    house.setNumberOfRooms(jsonObject.getInt("NumberOfRooms"));
                                    house.setSize(jsonObject.getDouble("Size"));
                                    house.setStatus(jsonObject.getString("status"));


                                    houses.add(house);
                                    connectionFlag[0] =1;
                                }} catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("hereeee 0 error");

                            }
                        });



                System.out.println("connection flag"+ connectionFlag[0]);

                if (connectionFlag[0] == 1) {
                    System.out.println("here 1");
                    fillHouses(houses);
                    Intent intent = new Intent(MainConnect.this, SignINActivity.class);
                    System.out.println("here 2");

                    MainConnect.this.startActivity(intent);
                    finish();

                } else {
                    Toast toast = Toast.makeText(MainConnect.this,
                            "No Internet Connection", Toast.LENGTH_SHORT);
                    toast.show();
                    setButtonText("connect ");

                }


            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.layout);

    }

    public void setButtonText(String text) {
        x.setText(text);
    }

    public void fillHouses(ArrayList<House> houses) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        linearLayout.removeAllViews();
        for (int i = 0; i < houses.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(houses.get(i).toString());
            linearLayout.addView(textView);
        }
    }

 }

