package com.example.lab_android_project_amr_waseem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class search extends AppCompatActivity {


    EditText miRooms;
    EditText maRooms;
    EditText miPrice;
    EditText maPrice;
    EditText miArea;
    EditText maArea;
    CheckBox balcony;
    CheckBox garden;

    Spinner city;
    Spinner country;
    Spinner status;
    Button search;


    String[] country_list = {"Palistine", "Egypt", "Jordan", "Sudia Arabi", "Syria", "lebanon"};
    String[] city_Palistine = {"Nables", "Ramallah", "Tulkarem"};
    String[] city_Egypt = {"Cairo", "Alexandria", "Gizeh"};
    String[] city_jordan = {"Amman", "Irbid", "Alzarqa"};
    String[] city_sudia = {"Dammam", "Dhahran", "Al Bahah"};
    String[] city_ٍSyria = {"hums", "aleppo", "damscus"};
    String[] city_lebanon = {"elhibah", "beirut", "balbk"};
    String[] nationality = {"Palestinien", "jordanian", "lebenese", "Saudian", "Egyptien"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        miRooms = findViewById(R.id.minimumNumberOfBedrooms);
        maRooms = findViewById(R.id.maximumNumberOfBedrooms);
        miPrice = findViewById(R.id.minimumPrice);
        maPrice = findViewById(R.id.MaximumPrice);
        miArea = findViewById(R.id.minimumArea);
        maArea = findViewById(R.id.MaximumArea);
        city = findViewById(R.id.spinnerCity);
        country = findViewById(R.id.spinnerCountry);
        status = findViewById(R.id.status);
        search = findViewById(R.id.button2);
        balcony = findViewById(R.id.balcony);
        garden = findViewById(R.id.garden);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, country_list);
        country.setAdapter(adapter3);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, city_Palistine);
        city.setAdapter(adapter2);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if (arg0.equals(country)) {

                    if (country.getSelectedItem().equals("Palistine")) {
                        ArrayAdapter<String> s1 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, city_Palistine);
                        s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s1);
                    } else if (country.getSelectedItem().equals("Egypt")) {
                        ArrayAdapter<String> s2 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, city_Egypt);
                        s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s2);

                    } else if (country.getSelectedItem().equals("Jordan")) {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, city_jordan);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);

                    } else if (country.getSelectedItem().equals("Syria")) {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, city_ٍSyria);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);


                    } else if (country.getSelectedItem().equals("lebanon")) {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(search.this, android.R.layout.simple_spinner_item, city_lebanon);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<House> houses = new ArrayList<>();
        ArrayList<House> filterd = new ArrayList<>();

        String url = "http://10.0.2.2:port/api/House/getAll";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                House house = new House();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject;
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
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean g = false;
                boolean b = false;

                boolean Test_empty_field = maArea.getText().toString().isEmpty() || miArea.getText().toString().isEmpty() || maRooms.getText().toString().isEmpty() || miRooms.getText().toString().isEmpty() || miPrice.getText().toString().isEmpty() || maPrice.getText().toString().isEmpty() || city.getSelectedItem().toString().isEmpty()
                        || status.getSelectedItem().toString().isEmpty();
                if (Test_empty_field == true) {
                    Toast toast = Toast.makeText(search.this,
                            "Please Fill required field", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                } else {
                    if (garden.isChecked())
                        g = true;
                    if (balcony.isChecked())
                        b = true;

                    for (int i = 0; i < houses.size(); i++) {
                        boolean bal = houses.get(i).isBalcony();
                        boolean gar = houses.get(i).isGarden();

                        if (
                                        (houses.get(i).getPrice() > Double.parseDouble(miPrice.getText().toString()) ||
                                        houses.get(i).getPrice() < Double.parseDouble(maPrice.getText().toString()))
                                        && (
                                        houses.get(i).getNumberOfRooms() > Integer.parseInt(miRooms.getText().toString()) ||
                                        houses.get(i).getNumberOfRooms() < Integer.parseInt(maRooms.getText().toString()))
                                        && (
                                        houses.get(i).getSize() > Double.parseDouble(miArea.getText().toString()) ||
                                        houses.get(i).getSize() < Double.parseDouble(maArea.getText().toString())) &&
                                        houses.get(i).getCity().equals(city.getSelectedItem().toString()) &&
                                        houses.get(i).getStatus().equals(status.getSelectedItem().toString()) &&
                                        (gar == g) &&
                                        (bal == b)
                        )
                            filterd.add(houses.get(i));
                    }

                }

            }
    });
}

    public List<House> getObjectFromJason(String jason) {
        try {
            JSONArray jsonArray = new JSONArray(jason);
            //   cars = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);


                //     cars.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void OpenHome(View view){
        Intent intent = new Intent(search.this, Tenant_main_activity.class);
        search.this.startActivity(intent);
        finish();
    }



}
