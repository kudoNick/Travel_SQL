package com.dcht69.travelapp.thang;

import java.io.Serializable;

public class Country implements Serializable {


    private String name;
    private int img;

    public Country( String name, int img) {
        this.name = name;
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Country{" +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
