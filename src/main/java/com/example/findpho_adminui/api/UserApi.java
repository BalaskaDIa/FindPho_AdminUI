package com.example.findpho_adminui.api;

import com.example.findpho_adminui.Controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.findpho_adminui.classes.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class UserApi extends Controller {

    private static final String API_URL = "http://127.0.0.1:8000/api";
    private static Gson jsonConverter = new Gson();

    public static List<User> getUser() throws IOException {
        String json = Api.get(API_URL + "/users");
        Type type = new TypeToken<List<User>>() {
        }.getType();
        return jsonConverter.fromJson(json, type);
    }

    public static User post(User uj) throws IOException {
        String ujJson = jsonConverter.toJson(uj);
        String json = Api.post(API_URL, ujJson);
        return jsonConverter.fromJson(json, User.class);
    }

    public static boolean delete(int id) throws IOException {
        return Api.delete(API_URL, id).getResponseCode() == 204;
    }

    public static User put(User modosit, int id) throws IOException {
        String modositandoJson = jsonConverter.toJson(modosit);
        String json = Api.put(API_URL, modositandoJson, id);
        return jsonConverter.fromJson(json, User.class);
    }
}
