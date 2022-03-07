package com.example.lab_android_project_amr_waseem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataBaseHelper extends android.database.sqlite.SQLiteOpenHelper {

ArrayList<Tenant> tenants =new ArrayList<>();
ArrayList<RentingAgency> rentingAgencies =new ArrayList<>();

    public DataBaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context) {
        super(context, "ProjectAndroid2021", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Tenat(Email TEXT PRIMARY KEY,First_Name TEXT,Last_Name TEXT, Phone TEXT,PASSWORD TEXT ,Gender TEXT,City TEXT,Country TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /********************************PERSON********************************/


    public void insertTenat(Tenant tenant) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", tenant.getFirst_name());
        contentValues.put("Second Name", tenant.getSecond_name());
        contentValues.put("Phone", tenant.getPhone());
        contentValues.put("Email", tenant.getEmail());
        contentValues.put("PASSWORD", tenant.getPassword());
        contentValues.put("Gender", tenant.getGender());
        contentValues.put("City", tenant.getCity());
        contentValues.put("Country", tenant.getCRC());
        contentValues.put("Nationality",tenant.getNationality());
        sqLiteDatabase.insert("Tenat", null, contentValues);
    }


    public void getAllPerson() {

    }

    public void searchBy_email_pass(String Email, String password) {


    }

    public int searchBy_email(String Email) {
        String url = "http://127.0.0.1:8050/api/Tenat/getAll";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("hereeee 0");
                        Tenant tenant = new Tenant();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject ;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = (JSONObject) jsonArray.get(i);
                               tenant.setSecond_name(jsonObject.getString("secondName"));
                               tenant.setFirst_name(jsonObject.getString("firstName"));
                               tenant.setPhone(jsonObject.getString("phone"));
                               tenant.setEmail(jsonObject.getString("Email"));

                                tenants.add(tenant);
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
        int count=0;
        for (int i=0;i<tenants.size();i++){
            if(tenants.get(i).getEmail().equalsIgnoreCase(Email)){
                count++;
            }
        }

        return count;

    }
    public int searchBy_emailR(String Email) {
        String url = "http://127.0.0.1:8050/api/Agency/getAll";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("hereeee 0");
                        RentingAgency rentingAgency = new RentingAgency();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject ;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = (JSONObject) jsonArray.get(i);

                                rentingAgency.setEmail(jsonObject.getString("Email"));

                                rentingAgencies.add(rentingAgency);
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
        int count=0;
        for (int i=0;i<tenants.size();i++){
            if(tenants.get(i).getEmail().equalsIgnoreCase(Email)){
                count++;
            }
        }

        return count;

    }

    public Cursor searchBy_email_(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String temp = "SELECT * FROM Tenat WHERE Email = " + '"' + Email + '"';
        return sqLiteDatabase.rawQuery("SELECT * FROM Tenant", null);





    }



    /********************************CAR********************************/



}
