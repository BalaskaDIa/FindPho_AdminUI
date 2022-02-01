package com.example.findpho_adminui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
-firstname lastname -> fullname
-admin boolean
-createdAt dateTime
*/

public class FindPhoDB {
    Connection conn;

    public FindPhoDB() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/findpho","root", "");
    }

    public List<User> getUser() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM user";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()){
            int id = result.getInt("id");
            String username = result.getString("username");
            String email = result.getString("email");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            User user = new User(id, username, email, firstname, lastname);
            userList.add(user);
        }
        return userList;
    }
    public int addUser(String username, String email, String firstname, String lastname) throws SQLException {
        String sql = "INSERT INTO user(username, email, firstname, lastname) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,username);
        stmt.setString(2,email);
        stmt.setString(3,firstname);
        stmt.setString(4,lastname);
        return stmt.executeUpdate();
    }
}
