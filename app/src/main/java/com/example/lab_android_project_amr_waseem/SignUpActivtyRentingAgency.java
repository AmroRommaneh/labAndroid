package com.example.lab_android_project_amr_waseem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivtyRentingAgency extends AppCompatActivity {


    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(SignUpActivtyRentingAgency.this, "RentingAgency", null, 1);



    TextView Agency_name;
    TextView Email;
    TextView Phone;
    TextView Password;
    TextView Confirm_password;
    Spinner city;
    Spinner country;
    Button register;
    TextView zip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activty_renting_agency);

        String[] country_list = {"Palistine", "Egypt", "Jordan", "Sudia Arabi","Syria","lebanon"};
        String[] city_Palistine = {"Nables", "Ramallah", "Tulkarem"};
        String[] city_Egypt = {"Cairo", "Alexandria", "Gizeh"};
        String[] city_jordan = {"Amman", "Irbid", "Alzarqa"};
        String[] city_sudia = {"Dammam", "Dhahran", "Al Bahah"};
        String[] city_ٍSyria = {"hums", "aleppo", "damscus"};
        String[] city_lebanon = {"elhibah", "beirut", "balbk"};
        String[] nationality = {"Palestinien", "jordanian", "lebenese","Saudian","Egyptien"};

        Agency_name = findViewById(R.id.signUpFirstnameBox);
        Email = findViewById(R.id.signUpEmailBox);
        Phone = findViewById(R.id.MaximumArea);
        Confirm_password = findViewById(R.id.signUpPassBox);
        Password = findViewById(R.id.signUpPassBox2);
        zip = findViewById(R.id.Zipcode);
        city = findViewById(R.id.spinnerCity);
        country = findViewById(R.id.spinnerCountry);
        register = findViewById(R.id.button2);
        ArrayAdapter<String> adapter3 =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, country_list);
        country.setAdapter(adapter3);
        ArrayAdapter<String> adapter2 =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, city_Palistine);
        city.setAdapter(adapter2);


        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if (arg0.equals(country)) {

                    if (country.getSelectedItem().equals("Palistine")) {
                        ArrayAdapter<String> s1 = new ArrayAdapter<String>(SignUpActivtyRentingAgency.this, android.R.layout.simple_spinner_item, city_Palistine);
                        s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s1);
                        zip.setText("+970");
                    } else if (country.getSelectedItem().equals("Egypt")) {
                        ArrayAdapter<String> s2 = new ArrayAdapter<String>(SignUpActivtyRentingAgency.this, android.R.layout.simple_spinner_item, city_Egypt);
                        s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s2);
                        zip.setText("+20");

                    } else if (country.getSelectedItem().equals("Jordan")) {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUpActivtyRentingAgency.this, android.R.layout.simple_spinner_item, city_jordan);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+962");

                    } else if (country.getSelectedItem().equals("Syria")){
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUpActivtyRentingAgency.this, android.R.layout.simple_spinner_item, city_ٍSyria);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+956");


                    }else if (country.getSelectedItem().equals("lebanon")){
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUpActivtyRentingAgency.this, android.R.layout.simple_spinner_item, city_lebanon);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+946");


                    }else {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUpActivtyRentingAgency.this, android.R.layout.simple_spinner_item, city_sudia);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+926");
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentingAgency rentingAgency = new RentingAgency();


                boolean Test_empty_field = Password.getText().toString().isEmpty() || Confirm_password.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || Agency_name.getText().toString().isEmpty()  || Phone.getText().toString().isEmpty();
                if (Test_empty_field == true) {
                    Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                            "Please Fill require field", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (!Password.getText().toString().equals(Confirm_password.getText().toString())) {

                    Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                            "Please make sure that two password are matching ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                //Regular Expression
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                //Compile regular expression to get the pattern
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(Email.getText().toString());
                if (matcher.matches() == false) {
                    Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                            "Please make sure that Email is in proper formate ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int email_alrady = dataBaseHelper.searchBy_emailR(Email.getText().toString());
                if (email_alrady >= 1) {
                    Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                            "This email allrady sigin in  ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }

                String regex2 = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
                //Compile regular expression to get the pattern
                Pattern pattern2 = Pattern.compile(regex2);
                System.out.println("---------the pass is :   " + Password.getText().toString());
                Matcher matcher2 = pattern2.matcher(Password.getText().toString());
                if (matcher2.matches() == false) {
                    Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                            "Please make sure that Password match with specification", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (Agency_name.getText().toString().length() < 3) {
                    Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                            "Please make sure that your first and last name have three charcter at least ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                rentingAgency.setEmail(Email.getText().toString());
                rentingAgency.setPassword(Password.getText().toString());
                rentingAgency.setCity(city.getSelectedItem().toString());
                rentingAgency.setCountry(country.getSelectedItem().toString());
                rentingAgency.setPhone(Phone.getText().toString());
                JSONObject postData = new JSONObject();
                try {
                    postData.put("Agency_Name", rentingAgency.getAgency_name());
                    postData.put("Phone", rentingAgency.getPhone());
                    postData.put("Email", rentingAgency.getEmail());
                    postData.put("PASSWORD", rentingAgency.getPassword());
                    postData.put("City", rentingAgency.getCity());
                    postData.put("Country", rentingAgency.getCountry());

                    String url = "http://10.0.2.2:8085/api/Tenant/register";

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST,
                                    url,
                                    postData,
                                    new Response.Listener<JSONObject>() {

                                        @Override
                                        public void onResponse(JSONObject response) {
                                            Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                                                    response.toString(), Toast.LENGTH_SHORT);
                                            toast.show();


                                        }}
                                    ,new Response.ErrorListener()
                            {@Override
                            public void onErrorResponse (VolleyError error){
                                Toast toast = Toast.makeText(SignUpActivtyRentingAgency.this,
                                        error.toString(), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public void OpenSignINPageR(View view) {
        Intent intent = new Intent(SignUpActivtyRentingAgency.this, SignINActivity.class);

        SignUpActivtyRentingAgency.this.startActivity(intent);
        finish();

    }
}