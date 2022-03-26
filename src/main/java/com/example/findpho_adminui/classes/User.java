package com.example.findpho_adminui.classes;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private boolean admin;

    public User(int id, String name, String username, String email, boolean admin) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
