package com.example.findpho_adminui.api;

import com.example.findpho_adminui.Controller;
import com.example.findpho_adminui.classes.Category;
import com.example.findpho_adminui.classes.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CategoryApi extends Controller {

    private static final String BASE_URL = "http://localhost:8000";
    public static final String CATEGORY_API_URL = BASE_URL + "/api/category";

    public static List<Category> getCategory() throws IOException {
        Response response = RequestHandler.get(CATEGORY_API_URL);
        String json = response.getContent();
        Gson jsonConvert = new Gson();
        if (response.getResponseCode() >= 400) {
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        Type type = new TypeToken<List<Category>>(){}.getType();
        return jsonConvert.fromJson(json,type);
    }

    public static Category postCategory(Category newCategory) throws IOException {
        Gson jsonConvert = new Gson();
        String categoryJson = jsonConvert.toJson(newCategory);
        Response response = RequestHandler.post(CATEGORY_API_URL, categoryJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400){
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Category.class);
    }

    public static Category putCategory(Category update) throws IOException {
        Gson jsonConvert = new Gson();
        String categoryJson = jsonConvert.toJson(update);
        Response response = RequestHandler.put(CATEGORY_API_URL + "/" + update.getId(), categoryJson);

        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return jsonConvert.fromJson(json, Category.class);
    }

    public static boolean deleteCategory(int id) throws IOException {
        Response response = RequestHandler.delete(CATEGORY_API_URL + "/" + id);

        Gson jsonConvert = new Gson();
        String json = response.getContent();
        if (response.getResponseCode() >= 400) {
            String message = jsonConvert.fromJson(json, ApiError.class).getMessage();
            throw new IOException(message);
        }
        return response.getResponseCode() == 204;
    }
}
