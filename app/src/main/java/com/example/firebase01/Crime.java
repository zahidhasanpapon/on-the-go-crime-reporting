package com.example.firebase01;

public class Crime {
    private String area, desc, image;

    public Crime(){

    }

    public Crime(String area, String desc, String image) {
        this.area = area;
        this.desc = desc;
        this.image = image;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
