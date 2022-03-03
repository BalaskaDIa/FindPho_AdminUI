package com.example.findpho_adminui;

import com.example.findpho_adminui.classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindPhoDB {
    Connection conn;

    public FindPhoDB() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/findpho","root", "");
    }

    public List<User> getUser() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM users";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            String username = result.getString("username");
            String email = result.getString("email");
            Boolean admin = result.getBoolean("admin");
            User user = new User(id, name, username, email, admin);
            userList.add(user);
        }
        return userList;
    }
    public int addUser(String name, String username, String email, Boolean admin) throws SQLException {
        String sql = "INSERT INTO user(name, username, email, admin) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(1,username);
        stmt.setString(2,email);
        stmt.setBoolean(3,admin);
        return stmt.executeUpdate();
    }
}
