package com.example.lab_android_project_amr_waseem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class SignUPActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper = new
            DataBaseHelper(SignUPActivity.this, "Person", null, 1);
     RequestQueue mQueue;


    Spinner Nationality;
    TextView GMS;
    TextView Occupation;
    TextView familySize;

    TextView first_name;
    TextView second_name;
    TextView Email;
    TextView Phone;
    TextView Password;
    TextView Confirm_password;
    Spinner Gender;
    Spinner city;
    Spinner country;
    Button register;
    TextView zip;
    TextView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mQueue = Volley.newRequestQueue(this);

        String[] country_list = {"Palistine", "Egypt", "Jordan", "Sudia Arabi","Syria","lebanon"};
        String[] city_Palistine = {"Nables", "Ramallah", "Tulkarem"};
        String[] city_Egypt = {"Cairo", "Alexandria", "Gizeh"};
        String[] city_jordan = {"Amman", "Irbid", "Alzarqa"};
        String[] city_sudia = {"Dammam", "Dhahran", "Al Bahah"};
        String[] city_ٍSyria = {"hums", "aleppo", "damscus"};
        String[] city_lebanon = {"elhibah", "beirut", "balbk"};
        String[] nationality = {"Palestinien", "jordanian", "lebenese","Saudian","Egyptien","Syrian"};

        first_name = findViewById(R.id.signUpFirstnameBox);
        second_name = findViewById(R.id.signUpLastnameBox);
        Email = findViewById(R.id.signUpEmailBox);
        Phone = findViewById(R.id.phone);
        Confirm_password = findViewById(R.id.signUpPassBox);
        Password = findViewById(R.id.signUpPassBox2);
        zip = findViewById(R.id.Zipcode);
        Gender = findViewById(R.id.spinnerGender);
        city = findViewById(R.id.spinnerCity);
        country = findViewById(R.id.spinnerCountry);
        register = findViewById(R.id.button2);
        Nationality = findViewById(R.id.spinnerNationality);
        GMS =findViewById(R.id.GrossMonthlySalary);
        Occupation =findViewById(R.id.Occupation);
        familySize=findViewById(R.id.FamilySize);
        back=findViewById(R.id.backtoo);

        String[] options = { "Male", "Female" };


        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        Gender.setAdapter(objGenderArr);
        ArrayAdapter<String> objGenderArr2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, nationality);
        Nationality.setAdapter(objGenderArr2);


        ArrayAdapter<String> adapter3 =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, country_list);
        country.setAdapter(adapter3);
        ArrayAdapter<String> adapter2 =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, city_Palistine);
        city.setAdapter(adapter2);


        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                if (arg0.equals(country)) {

                    if (country.getSelectedItem().equals("Palistine")) {
                        ArrayAdapter<String> s1 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_Palistine);
                        s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s1);
                        zip.setText("+970");
                    } else if (country.getSelectedItem().equals("Egypt")) {
                        ArrayAdapter<String> s2 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_Egypt);
                        s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s2);
                        zip.setText("+20");

                    } else if (country.getSelectedItem().equals("Jordan")) {
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_jordan);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+962");

                    } else if (country.getSelectedItem().equals("Syria")){
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_ٍSyria);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+956");


                    }else if (country.getSelectedItem().equals("lebanon")){
                        ArrayAdapter<String> s3 = new ArrayAdapter<String>(SignUPActivity.this, android.R.layout.simple_spinner_item, city_lebanon);
                        s3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        city.setAdapter(s3);
                        zip.setText("+946");


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
                Tenant tenant = new Tenant();


                boolean Test_empty_field = Password.getText().toString().isEmpty() || Confirm_password.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || first_name.getText().toString().isEmpty() || second_name.getText().toString().isEmpty() || Phone.getText().toString().isEmpty();
                if (Test_empty_field == true) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please Fill require field", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (!Password.getText().toString().equals(Confirm_password.getText().toString())) {

                    Toast toast = Toast.makeText(SignUPActivity.this,
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
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that Email is in proper formate ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                int email_alrady = dataBaseHelper.searchBy_email(Email.getText().toString());
                if (email_alrady >= 1) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
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
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that Password match with specification", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (first_name.getText().toString().length() < 3 || second_name.getText().toString().length() < 3) {
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            "Please make sure that your first and last name have three charcter at least ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                tenant.setEmail(Email.getText().toString());
                tenant.setPassword(Password.getText().toString());
                tenant.setGender(Gender.getSelectedItem().toString());
                tenant.setCity(city.getSelectedItem().toString());
                tenant.setCRC(country.getSelectedItem().toString());
                tenant.setFirst_name(first_name.getText().toString());
                tenant.setSecond_name(second_name.getText().toString());
                tenant.setPhone(Phone.getText().toString());
                tenant.setFamilySize(Integer.parseInt(familySize.getText().toString()));
                tenant.setGMS(Double.parseDouble(GMS.getText().toString()));
                tenant.setOccupation(Occupation.getText().toString());
                tenant.setNationality(Nationality.getSelectedItem().toString());

                JSONObject postData = new JSONObject();
                try {
                    postData.put("FirstName", tenant.getFirst_name());
                    postData.put("SecondName", tenant.getSecond_name());
                    postData.put("Phone", tenant.getPhone());
                    postData.put("Email", tenant.getEmail());
                    postData.put("PASSWORD", tenant.getPassword());
                    postData.put("Gender", tenant.getGender());
                    postData.put("City", tenant.getCity());
                    postData.put("Country", tenant.getCRC());
                    postData.put("Nationality",tenant.getNationality());
                    postData.put("familySize",tenant.getFamilySize());
                    postData.put("GMS",tenant.getGMS());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                sendRequest(postData);


                Intent intent = new Intent(SignUPActivity.this, Tenant_main_activity.class);
                SignUPActivity.this.startActivity(intent);
                finish();


            }


        });




    }

    public void OpenSignINPage(View view) {
        Intent intent = new Intent(SignUPActivity.this, SignINActivity.class);
        SignUPActivity.this.startActivity(intent);
        finish();

    }


    public void sendRequest(JSONObject postData) {

        String url = "http://10.0.2.2:8085/api/Tenant/saveTenant";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,
                        url,
                        postData,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Toast toast = Toast.makeText(SignUPActivity.this,
                                        response.toString(), Toast.LENGTH_SHORT);
                                toast.show();


                            }}
                        ,new Response.ErrorListener()
                {@Override
                public void onErrorResponse (VolleyError error){
                    Toast toast = Toast.makeText(SignUPActivity.this,
                            error.toString(), Toast.LENGTH_SHORT);
                    toast.show();

                    System.out.println(error.toString());
                }
                });


        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        mQueue.add(jsonObjectRequest);
    }
}