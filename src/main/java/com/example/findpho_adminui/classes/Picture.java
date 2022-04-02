package com.example.findpho_adminui.classes;

public class Picture {
    private int id;
    private int user_id;
    private String image;
    private String title;
    private String description;
    private String category;

    public Picture(int id, int user_id, String title,String description, String image, String category) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
