package com.example.lab_android_project_amr_waseem;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lab_android_project_amr_waseem.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.common.sdkinternal.MlKitContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SignINActivity extends AppCompatActivity {
    int valid;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(SignINActivity.this, "Person", null, 1);
    EditText email, password;
    CheckBox remeber;
    public static String extra_email = "com.example.lab_android_project_amr_waseem";
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        email = findViewById(R.id.EmailBox);
        password = findViewById(R.id.PasswordBox);
        remeber = findViewById(R.id.login_rememberme);
        FloatingActionButton login = (FloatingActionButton) findViewById(R.id.loginButton);
        sharedPrefManager =SharedPrefManager.getInstance(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject postData = new JSONObject();
                try

                {
                    postData.put("Email", email.getText().toString());
                    postData.put("password", password.getText().toString());
                } catch(
                        JSONException e)

                {
                    e.printStackTrace();
                }

                String url = "http://10.0.2.2:8050/api/Tenant/login";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST,
                                url,
                                postData,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {

                                        if(response.toString().equalsIgnoreCase("valid"))
                                            valid=1;

                                        Toast toast = Toast.makeText(SignINActivity.this,
                                                response.toString(), Toast.LENGTH_SHORT);
                                        toast.show();


                                    }}
                                  ,new Response.ErrorListener()
                                    {@Override
                                        public void onErrorResponse (VolleyError error){
                                        Toast toast = Toast.makeText(SignINActivity.this,
                                                error.toString(), Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                    });
                Intent intent = new Intent(SignINActivity.this, typeSignUp.class);

                if(!sharedPrefManager.readString("email","noValue").

                        equals("noValue"))

                {
                     email.setText(sharedPrefManager.readString("email", "noValue"));
                     password.setText(sharedPrefManager.readString("password", "noValue"));
                     remeber.setChecked(true);
                }
                remeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

                {
                    @Override
                    public void onCheckedChanged (CompoundButton compoundButton,boolean b){
                        if (remeber.isChecked()) {
                            sharedPrefManager = SharedPrefManager.getInstance(SignINActivity.this);
                            sharedPrefManager.writeString("email", email.getText().toString());
                            sharedPrefManager.writeString("password", password.getText().toString());
                        } else if (!remeber.isChecked()) {
                            sharedPrefManager = SharedPrefManager.getInstance(SignINActivity.this);
                            sharedPrefManager.writeString("email", "noValue");
                            sharedPrefManager.writeString("password", "noValue");
                        }
                    }
                });

                if(valid==1)
                SignINActivity.this.startActivity(intent);
else{
                    Toast toast = Toast.makeText(SignINActivity.this,
                            "inccorect email or password", Toast.LENGTH_SHORT);
                    toast.show();
                }



            }
            });


        }


        public void OpenSignupPage (View view){
            Intent intent = new Intent(SignINActivity.this, typeSignUp.class);
            SignINActivity.this.startActivity(intent);
            finish();
        }


    }