package com.example.findpho_adminui.classes;

public class Picture {
    private int id;
    private int userId;
    private String url;
    private String latitude;
    private String longitude;
    private String caption;
    private String image;

    public Picture(int id, int userId, String url, String latitude, String longitude, String caption, String image) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
        this.caption = caption;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
