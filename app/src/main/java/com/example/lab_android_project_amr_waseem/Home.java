package com.example.lab_android_project_amr_waseem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Button menu ;
    LinearLayout linearLayout;
    ArrayList<House>houses =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu =findViewById(R.id.menu);

        linearLayout = (LinearLayout) findViewById(R.id.layout);
            linearLayout.removeAllViews();
        String url = "http://127.0.0.1:8050/api/House/getAll";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("hereeee 0");
                        House house = new House();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject ;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = (JSONObject) jsonArray.get(i);
                                house.setGarden(jsonObject.getBoolean("garden"));
                                house.setBalcony(jsonObject.getBoolean("balcony"));
                                house.setCity(jsonObject.getString("City"));
                                house.setPrice(jsonObject.getDouble("price"));
                                house.setAge(jsonObject.getInt("Age"));
                                house.setNumberOfRooms(jsonObject.getInt("NumberOfRooms"));
                                house.setSize(jsonObject.getDouble("Size"));
                                house.setStatus(jsonObject.getString("status"));


                                houses.add(house);
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
if (houses.size()<5){
    for (int i = 0; i < houses.size(); i++) {
        TextView textView = new TextView(this);
        textView.setText(houses.get(i).toString());
        linearLayout.addView(textView);
    }
}else {
    for (int i = houses.size() - 5; i < houses.size(); i++) {
        TextView textView = new TextView(this);
        textView.setText(houses.get(i).toString());
        linearLayout.addView(textView);
    }
}
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent intent = new Intent(Home.this,Tenant_main_activity.class);
                Home.this.startActivity(intent);
                finish();
            }
        });
    }

}