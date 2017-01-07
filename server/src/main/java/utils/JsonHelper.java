package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by venik on 07.01.17.
 */
public class JsonHelper {
    private static Gson gson = new GsonBuilder().create();

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> type){
        return gson.fromJson(json,type);
    }

}
