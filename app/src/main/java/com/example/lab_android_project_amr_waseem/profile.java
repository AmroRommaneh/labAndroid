package com.example.lab_android_project_amr_waseem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class profile extends AppCompatActivity {

    TextView head;
    TextView first_name;
    TextView last_name;
    TextView phone;
    TextView password;
    TextView check_password;
    Button confirm;
    ImageView profile_change;





    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPrefManager  sharedPrefManager =SharedPrefManager.getInstance(this);

        final String email = sharedPrefManager.readString("email","noValue");

        String url = "http://10.0.2.2:port/api/Tenant/getAll";

        ArrayList<Tenant> tenants =new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Tenant tenant=new Tenant();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject ;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = (JSONObject) jsonArray.get(i);
                                tenant.setNationality(jsonObject.getString("Nationality"));
                                tenant.setOccupation(jsonObject.getString("Occupation"));
                                tenant.setGMS(jsonObject.getDouble("GMS"));
                                tenant.setCRC(jsonObject.getString("City"));
                                tenant.setFamilySize(jsonObject.getInt("FamilySize"));
                                tenant.setEmail(jsonObject.getString("Email"));
                                tenant.setPassword(jsonObject.getString("passowrd"));
                                tenant.setPhone(jsonObject.getString("phone"));
                                tenant.setFirst_name(jsonObject.getString("firstName"));
                                tenant.setSecond_name(jsonObject.getString("secondName"));

                                tenants.add(tenant);
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



        head = (TextView) findViewById(R.id.editprofile_name_phone);
        profile_change = findViewById(R.id.profile_pic);
        first_name = (TextView) findViewById(R.id.editProfileFirstnameBox);
        last_name = (TextView) findViewById(R.id.editProfileLastnameBox);
        phone = (TextView) findViewById(R.id.editProfilePhoneBox);
        password = (TextView) findViewById(R.id.editProfilePassBox);
        check_password = (TextView) findViewById(R.id.editProfilePassBox2);
        confirm = findViewById(R.id.editprofile_savebtn);

        Tenant target = new Tenant();
        for (int i= 0; i< tenants.size();i++){
            if (tenants.get(i).getEmail().equals(email))
                target=tenants.get(i);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (first_name.getText().toString().length() < 3 || last_name.getText().toString().length() < 3) {
                    Toast toast = Toast.makeText(profile.this,
                            "Please make sure that your first and last name have three charcter at least ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if (!password.getText().toString().equals(check_password.getText().toString())) {

                    Toast toast = Toast.makeText(profile.this,
                            "Please make sure that two passwords are matching ", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                String regex2 = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
                //Compile regular expression to get the pattern
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher2 = pattern2.matcher(password.getText().toString());
                if (matcher2.matches() == false) {
                    Toast toast = Toast.makeText(profile.this,
                            "Please make sure that Password match with specification", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                JSONObject postData = new JSONObject();
                try {
                    postData.put("FirstName", first_name.getText().toString());
                    postData.put("SecondName", last_name.getText().toString());
                    postData.put("Phone", phone.getText().toString());
                    postData.put("Email", email);
                    postData.put("PASSWORD", password.getText().toString());

                    String url = "http://10.0.2.2:port/api/Tenant/update";

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.PUT,
                                    url,
                                    postData,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                        }}
                                    ,new Response.ErrorListener()
                            {@Override
                            public void onErrorResponse (VolleyError error){

                            }
                            });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast toast2 = Toast.makeText(profile.this,
                        "The change is saved", Toast.LENGTH_SHORT);
                toast2.show();

                Intent intent = new Intent(profile.this, Tenant_main_activity.class);
                startActivity(intent);


            }

        });

        profile_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallary = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallary, 1004);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1004) {
            if (resultCode == Activity.RESULT_OK) {
                Uri pic_uri = data.getData();
                profile_change.setImageURI(pic_uri);
            }
        }
    }
}