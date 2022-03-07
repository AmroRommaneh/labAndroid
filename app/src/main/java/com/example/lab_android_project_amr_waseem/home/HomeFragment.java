package com.example.lab_android_project_amr_waseem.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lab_android_project_amr_waseem.House;
import com.example.lab_android_project_amr_waseem.R;
import com.example.lab_android_project_amr_waseem.SignINActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        String url = "http://127.0.0.1:port/api/House/gettop5";
        ArrayList<House>  houses = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = new JSONObject();

                                        jsonObject = (JSONObject) jsonArray.get(i);
                                        House house = new House();

                                        house.setStatus(jsonObject.getString("status"));
                                        house.setPrice(jsonObject.getDouble("Price"));
                                        house.setCity(jsonObject.getString("City"));
                                        house.setAge(jsonObject.getInt("Age"));
                                        house.setSize(jsonObject.getDouble("Size"));
                                        house.setNumberOfRooms(jsonObject.getInt("NumberOfRooms"));
                                        house.setBalcony(jsonObject.getBoolean("Balcony"));
                                        house.setGarden(jsonObject.getBoolean("Garden"));


                                        houses.add(house);
                                    } }catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        ,
                        new Response.ErrorListener()
                {@Override
                public void onErrorResponse (VolleyError error){

                }
                });
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout linearLayout = (LinearLayout) getActivity()
        .findViewById(R.id.layout);
        linearLayout.removeAllViews();
        for (int i = 0; i < houses.size(); i++) {
            TextView textView = new TextView(getActivity());
            textView.setText(houses.get(i).toString());
            linearLayout.addView(textView);
        }

        return root;
    }
}