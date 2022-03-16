package com.example.findpho_adminui.api;

import com.example.findpho_adminui.Controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.findpho_adminui.classes.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserApi extends Controller {

    private static final String BASE_URL = "http://localhost:8000";
    public static final String USER_API_URL = BASE_URL + "/api/users";

    public static List<User> getUser() throws IOException {
        Response response = RequestHandler.get(USER_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<User>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }

    public static User postUser(User newUser) throws IOException {
        Gson jsonConvert = new Gson();
        String userJson = jsonConvert.toJson(newUser);
        Response response = RequestHandler.post(USER_API_URL, userJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, User.class);
    }
    public static User putUser(User update) throws IOException {
        Gson jsonConvert = new Gson();
        String userJson = jsonConvert.toJson(update);
        Response response = RequestHandler.put(USER_API_URL + "/" + update.getId(), userJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, User.class);
    }

    public static boolean deleteUser(int id) throws IOException {
        Response response = RequestHandler.delete(USER_API_URL+ "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
