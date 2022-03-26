package com.example.findpho_adminui.api;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.classes.Picture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PictureApi extends Controller {

    private static final String BASE_URL = "http://localhost:8000";
    public static final String PICTURE_API_URL = BASE_URL + "/api/picture";

    public static List<Picture> getPicture() throws IOException {
        Response response = RequestHandler.get(PICTURE_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400) {
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Picture>>() {
        }.getType();
        return jsonConvert.fromJson(json, type);
    }

    public static Picture putPicture(Picture update) throws IOException {
        Gson jsonConvert = new Gson();
        String pictureJson = jsonConvert.toJson(update);
        Response response = RequestHandler.put(PICTURE_API_URL + "/" + update.getId(), pictureJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Picture.class);
    }

    public static boolean deletePicture(int id) throws IOException {
        Response response = RequestHandler.delete(PICTURE_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
