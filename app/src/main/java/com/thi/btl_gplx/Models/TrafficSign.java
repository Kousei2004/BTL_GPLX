package com.thi.btl_gplx.Models;

import java.io.Serializable;

public class  TrafficSign implements Serializable {

    private String code;
    private String name;
    private String description;
    private int imageResId;

    public TrafficSign() {
    }

    public TrafficSign(String code,String name, String description, int imageResId) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
