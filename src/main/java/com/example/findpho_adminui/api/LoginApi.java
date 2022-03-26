package com.example.findpho_adminui.api;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.classes.Login;
import com.example.findpho_adminui.classes.Token;
import com.example.findpho_adminui.classes.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

public class LoginApi extends Controller {

    private static final String BASE_URL = "http://localhost:8000";
    public static final String LOGIN_API_URL = BASE_URL + "/api";

    public static String getLogin(String url, String token) throws IOException {
        Response response = RequestHandler.tokenAuthorization(url, token);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return json;
    }

    public static Token postLogin(Login login) throws IOException {
        Gson jsonConverter = new Gson();
        String loginJson = jsonConverter.toJson(login);
        Response response = RequestHandler.post(LOGIN_API_URL + "/login", loginJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConverter.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConverter.fromJson(json, Token.class);
    }

    public static User getLoginData(String token) throws IOException {
        String json = getLogin(LOGIN_API_URL + "/user", token);
        Type type = new TypeToken<User>() {
        }.getType();
        Gson jsonConverter = new Gson();
        return jsonConverter.fromJson(json, type);
    }
}
