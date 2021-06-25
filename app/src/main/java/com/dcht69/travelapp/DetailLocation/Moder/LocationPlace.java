package com.dcht69.travelapp.DetailLocation.Moder;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationPlace {
    String Id,IdCity,namePlace,imgPlace,img1,textImg1,img2,textImg2,comment1, comment2;


    public LocationPlace(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("namePlace")) {
            namePlace = jsonObject.getString("namePlace");
        }
        if (jsonObject.has("img")) {
            imgPlace = jsonObject.getString("img");
        }
        if (jsonObject.has("id")) {
            Id = jsonObject.getString("id");
        }
        if (jsonObject.has("img1")) {
            img1 = jsonObject.getString("img1");
        }
        if (jsonObject.has("img2")) {
            img2 = jsonObject.getString("img2");
        }
    }

    public LocationPlace() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdCity() {
        return IdCity;
    }

    public void setIdCity(String idCity) {
        IdCity = idCity;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getImgPlace() {
        return imgPlace;
    }

    public void setImgPlace(String imgPlace) {
        this.imgPlace = imgPlace;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getTextImg1() {
        return textImg1;
    }

    public void setTextImg1(String textImg1) {
        this.textImg1 = textImg1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getTextImg2() {
        return textImg2;
    }

    public void setTextImg2(String textImg2) {
        this.textImg2 = textImg2;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }
}
