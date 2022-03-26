package com.example.findpho_adminui.classes;

public class Picture {
    private int id;
    private int user_id;
    private String url;
    private String caption;
    private String image;

    public Picture(int id, int user_id, String url, String caption, String image) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
        this.caption = caption;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
