package com.example.findpho_adminui.api;

import com.example.findpho_adminui.Controller;
import com.google.gson.Gson;

import java.io.IOException;

public class StatisticsApi extends Controller {

    private static final String BASE_URL = "http://localhost:8000";

    public static int allUsers() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/allusers");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int allPhotos() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/allphotos");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int allCategories() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/allcategories");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int janUpload() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/janupload");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int febUpload() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/febupload");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int marchUpload() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/marchupload");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int aprUpload() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/aprupload");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int mayUpload() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/mayupload");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }

    public static int junUpload() throws IOException {
        Response response = RequestHandler.get(BASE_URL+ "/api/junupload");
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return Integer.parseInt(json);
    }
}
