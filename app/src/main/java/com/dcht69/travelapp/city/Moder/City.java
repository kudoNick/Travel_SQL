package com.dcht69.travelapp.city.Moder;

import org.json.JSONException;
import org.json.JSONObject;

public class City {
    String nameCity,imgCity,Id;


    public City(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("place")) {
            nameCity = jsonObject.getString("place");
        }
        if (jsonObject.has("id")) {
            Id = jsonObject.getString("id");
        }
        if (jsonObject.has("img")) {
            imgCity = jsonObject.getString("img");
        }
    }

    public City() {
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getImgCity() {
        return imgCity;
    }

    public void setImgCity(String imgCity) {
        this.imgCity = imgCity;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
