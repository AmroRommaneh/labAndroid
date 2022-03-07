package com.example.lab_android_project_amr_waseem;

import com.example.lab_android_project_amr_waseem.House;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class houseJsonParser {
    public static  List<House> Houses;

    public static List<House> getObjectFromJason(String jason) {
        try {

            JSONArray jsonArray = new JSONArray(jason);
            Houses = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                House house = new House();
                house.setPrice(jsonObject.getDouble("Price"));
                house.setCity(jsonObject.getString("City"));
                house.setAge(jsonObject.getInt("Age"));
                house.setSize(jsonObject.getDouble("Size"));
                house.setNumberOfRooms(jsonObject.getInt("NumberOfRooms"));
                house.setBalcony(jsonObject.getBoolean("Balcony"));
                house.setGarden(jsonObject.getBoolean("Garden"));


                Houses.add(house);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return Houses;
    }
}
